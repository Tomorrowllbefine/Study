package com.kk.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Test2")
public class Test2 {
    public static void main(String[] args) {

        // 创建Runnable实例
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("hello");
            }
        };
        // 构造线程t2
        Thread t2 = new Thread(runnable,"t2");
        t2.start();

        log.debug("hello");

        new Test2().test1();
    }


    public  void test1(){

        Thread t2 = new Thread(
                ()->log.debug("fatfat"),"t2"
        );
        t2.start();
    }
}
