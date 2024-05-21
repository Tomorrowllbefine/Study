package com.hmdp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.entity.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.CACHE_NULL_TTL;

@Slf4j
@Component
public class CacheClient {

    private final StringRedisTemplate stringRedisTemplate;
    @Autowired
    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 线程池
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public void set(String key, Object value, Long time, TimeUnit unit){
        String value2Json = JSONUtil.toJsonStr(value);
        stringRedisTemplate.opsForValue().set(key, value2Json, time, unit);
    }

    /**
     * 预热商品写入缓存
     * @param expireTime 逻辑过期时间
     * @param unit 时间单位
     */
    public void setWithLogicalExpire(String key, Object value, Long expireTime, TimeUnit unit){
        // 封装为RedisData对象
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(expireTime))); // 以秒为单位
        String redisData2Json = JSONUtil.toJsonStr(redisData);
        log.info("更新缓存");
        stringRedisTemplate.opsForValue().set(key, redisData2Json);
    }

    // 封装解决缓存穿透方法
    public <R,ID> R queryWithPassThrough(String prefix, ID id, Class<R> type, Function<ID,R> dbFallback,
                                          Long time, TimeUnit unit){
        String key = prefix + id;
        // 从Redis查询缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        // 判断缓存是否命中
        if(StrUtil.isNotBlank(json)) {
            // 命中，返回商铺信息
            log.info("cache hit!");
            return JSONUtil.toBean(json, type);
        }
        // 避免缓存穿透 识别为空对象时直接返回null
        if("".equals(json)){
            // stringRedisTemplate.expire(key,RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES); // 刷新ttl
            return null;
        }
        // 未命中，根据id查询数据库
        R res = dbFallback.apply(id);
        //  判断商铺是否存在
        if(res == null) {
            // 不存在于数据库，往缓存写入空对象 TTL=2min
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            // 返回404
            return null;
        }
        // 存在，将商铺数据写入redis
        this.set(key, res, time, unit);
        // 返回商铺信息
        return res;
    }


    // 封装解决缓存击穿的方法
    public <R,ID> R queryWithLogicalExpire(String prefix, ID id, Class<R> type, Function<ID,R> dbFallback,
                                           Long time, TimeUnit unit) {
        // 查询商铺缓存
        String key = prefix + id;
        String redisDataJson = stringRedisTemplate.opsForValue().get(key);
        // 判断缓存是否命中
        if(redisDataJson == null){
            return null; // 未命中，说明当前商品非热点商品，直接返回空
        }
        // 命中，提取expire字段，查看是否过期
        RedisData redisData = JSONUtil.toBean(redisDataJson, RedisData.class);
        JSONObject data = (JSONObject) redisData.getData();
        R res = JSONUtil.toBean(data, type);
        LocalDateTime expireTime = redisData.getExpireTime();

        if(expireTime.isAfter(LocalDateTime.now())){
            log.info("未过期");
            return res; // 未过期，直接返回
        }
        // 过期，尝试获取互斥锁
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        boolean isLock = getLock(lockKey);
        // 判断是否获得锁
        if(isLock){
            log.info("拿到锁!");
            String doubleCheck = stringRedisTemplate.opsForValue().get(key);
            // 二次判断，可能这把锁是释放的锁
            RedisData redisData1 = JSONUtil.toBean(doubleCheck, RedisData.class);
            LocalDateTime expireTime1 = redisData1.getExpireTime();
            // 未过期，返回二次缓存命中的信息
            if(expireTime1.isAfter(LocalDateTime.now())){
                return JSONUtil.toBean((JSONObject) redisData1.getData(), type);
            }
            // 更新商铺缓存 另起线程
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    //this.saveShop2Redis(prefix, id, dbFallback, time, unit);
                    R res1 = dbFallback.apply(id);
                    this.setWithLogicalExpire(key, res1, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally{
                    // 释放锁
                    unLock(lockKey);
                }
            });
        }
        return res; // 未获得，返回旧数据
    }


    /**
     * 获取互斥锁
     */
    private boolean getLock(String key){
        // 这里设置的时间要是正常的业务时间10倍-20倍
        Boolean res = stringRedisTemplate.opsForValue().setIfAbsent(key, "{\"1\":\"1\"}",10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(res);
    }

    /**
     * 释放互斥锁
     */
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }
}
