package com.kk.application;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @description: 统筹-烧水泡茶
 * @author: mmkk
 **/
@Slf4j
public class Case1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("洗水壶");
                sleep(1);
                log.debug("烧开水");
                sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "老王");
        Thread t2 = new Thread(() -> {
            try {
                log.debug("洗茶壶");
                sleep(1);
                log.debug("洗茶杯");
                sleep(2);
                log.debug("拿茶叶");
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("泡茶");
        }, "小王");
        t1.start();
        t2.start();
    }
}
