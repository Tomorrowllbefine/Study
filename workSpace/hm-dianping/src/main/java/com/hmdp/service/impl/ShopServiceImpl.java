package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.CacheClient;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RedisData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;
import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TTL;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j(topic = "shopServiceImpl: ")
@AllArgsConstructor
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    private final StringRedisTemplate stringRedisTemplate;
    private final CacheClient cacheClient;



    /**
     * 根据id查询商铺
     * @param id
     * @return
     */
    @Override
    public Result queryShopById(Long id) {

        // 解决缓存穿透
        /*Shop shop = cacheClient.queryWithPassThrough(RedisConstants.CACHE_SHOP_KEY, id, Shop.class,
                this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);*/

        // 互斥锁解决缓存击穿
        // Shop shop = this.queryWithMutex(id);

        // 逻辑过期解决缓存击穿
        Shop shop = cacheClient.queryWithLogicalExpire(CACHE_SHOP_KEY, id, Shop.class, this::getById,
                20L, TimeUnit.SECONDS); // 测试用

        if(shop == null){
            return Result.fail("商铺不存在");
        }
        // 返回商铺信息
        return Result.ok(shop);
    }

    /**
     * 逻辑过期解决缓存击穿
     * @param id
     * @return
     */
    /*private Shop queryWithLogicalExpire(Long id) {
        // 查询商铺缓存
        String shopKey = RedisConstants.CACHE_SHOP_KEY + id;
        String redisDataJson = stringRedisTemplate.opsForValue().get(shopKey);
        // 判断缓存是否命中
        if(redisDataJson == null){
            return null; // 未命中，说明当前商品非热点商品，直接返回空
        }
        // 命中，提取expire字段，查看是否过期
        RedisData redisData = JSONUtil.toBean(redisDataJson, RedisData.class);
        JSONObject data = (JSONObject) redisData.getData();
        Shop shop = JSONUtil.toBean(data, Shop.class);
        LocalDateTime expireTime = redisData.getExpireTime();

        if(expireTime.isAfter(LocalDateTime.now())){
            log.info("未过期");
            return shop; // 未过期，直接返回
        }
        // 过期，尝试获取互斥锁
        boolean isLock = getLock(RedisConstants.LOCK_SHOP_KEY + id);
        // 判断是否获得锁
        if(isLock){
            log.info("拿到锁!");
            String doubleCheck = stringRedisTemplate.opsForValue().get(shopKey);
            // 二次判断，可能这把锁是释放的锁
            RedisData redisData1 = JSONUtil.toBean(doubleCheck, RedisData.class);
            LocalDateTime expireTime1 = redisData1.getExpireTime();
            // 未过期，返回二次缓存命中的信息
            if(expireTime1.isAfter(LocalDateTime.now())){
                return JSONUtil.toBean((JSONObject) redisData1.getData(), Shop.class);
            }
            // 更新商铺缓存 另起线程
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    this.saveShop2Redis(id, 10L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally{
                    // 释放锁
                    unLock(RedisConstants.LOCK_SHOP_KEY + id);
                }
            });
        }
        return shop; // 未获得，返回旧数据
    }*/

    /**
     * 获取互斥锁
     * @param key
     * @return
     */
    private boolean getLock(String key){
        // 这里设置的时间要是正常的业务时间10倍-20倍
        Boolean res = stringRedisTemplate.opsForValue().setIfAbsent(key, "{\"1\":\"1\"}",10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(res);
    }

    /**
     * 释放互斥锁
     * @param key
     */
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 互斥锁解决缓存击穿
     * @param id
     * @return
     */
    private Shop queryWithMutex(Long id){
        String key = CACHE_SHOP_KEY + id;
        // 从Redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        // 判断缓存是否命中
        if(StrUtil.isNotBlank(shopJson)) {
            // 命中，返回商铺信息
            log.info("cache hit!");
            return JSONUtil.toBean(shopJson, Shop.class);
        }
        // 避免缓存穿透
        if("".equals(shopJson)){
            // stringRedisTemplate.expire(key,RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES); // 刷新ttl
            return null;
        }
        Shop shop = null;
        try {
            // 未命中，获取互斥锁
            boolean isLock = this.getLock(RedisConstants.LOCK_SHOP_KEY + id);
            if(!isLock) { // 未获得锁，等待并再次查询缓存
                Thread.sleep(30);
                return this.queryWithMutex(id);
            }
            log.info("得到锁！");
            // 获取到锁，查询数据库
            shop = getById(id);
            //Thread.sleep(200);
            //  判断商铺是否存在
            if(shop == null) {
                // 不存在于数据库，往缓存写入空对象 TTL=2min
                stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
                // 返回404
                return null;
            }
            // 存在，将商铺数据写入redis
            shopJson = JSONUtil.toJsonStr(shop);
            stringRedisTemplate.opsForValue().set(key, shopJson, CACHE_SHOP_TTL, TimeUnit.MINUTES);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.unLock(RedisConstants.LOCK_SHOP_KEY + id);
        }
        // 返回商铺信息
        return shop;
    }


    /**
     * 解决缓存穿透
     * @param id
     * @return
     */
    /*private Shop queryWithPassThrough(Long id){
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        // 从Redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        // 判断缓存是否命中
        if(StrUtil.isNotBlank(shopJson)) {
            // 命中，返回商铺信息
            log.info("cache hit!");
            return JSONUtil.toBean(shopJson, Shop.class);
        }
        // 避免缓存穿透
        if("".equals(shopJson)){
//            stringRedisTemplate.expire(key,RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES); // 刷新ttl
            return null;
        }
        // 未命中，根据id查询数据库
        Shop shop = getById(id);
        //  判断商铺是否存在
        if(shop == null) {
            // 不存在于数据库，往缓存写入空对象 TTL=2min
            stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            // 返回404
            return null;
        }
        // 存在，将商铺数据写入redis
        shopJson = JSONUtil.toJsonStr(shop);
        stringRedisTemplate.opsForValue().set(key, shopJson, RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        // 返回商铺信息
        return shop;
    }*/

    /**
     * 预热商品写入缓存
     * @param id 商品id
     * @param expireSecond 过期时间
     */
    public void saveShop2Redis(Long id, Long expireSecond) throws InterruptedException {
        // 查询店铺数据
        Shop shop = getById(id);
        Thread.sleep(200L);
        // 封装成RedisData
        RedisData redisData = new RedisData();
        redisData.setData(shop);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSecond));
        // 写入Redis
        log.info("更新缓存");
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 更新商铺信息
     * @param shop
     * @return
     */
    @Override
    @Transactional
    public Result updateShop(Shop shop) {
        // 判断店铺id是否存在
        if(shop.getId() == null){
            return Result.fail("店铺id不存在");
        }
        // 更新数据库
        updateById(shop);
        // 删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + shop.getId());
        return Result.ok();
    }
}
