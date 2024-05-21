package com.kk.Chapter1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "parkThread")
public class Test_Interrupt_2 {

    public static void main(String[] args) throws InterruptedException {
        testInterrupt_2();
    }

    public static void testInterrupt_2() throws InterruptedException {

        Thread t1 = new Thread(()->{
            log.debug("打断状态:{}", Thread.currentThread().isInterrupted());

            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态:{}", Thread.currentThread().isInterrupted());

            log.debug("打断状态:{}", Thread.interrupted());

            LockSupport.park();
            log.debug("unpark...");

        }, "t1");
        t1.start();

        Thread.sleep(1000);
        t1.interrupt();
    }
}
