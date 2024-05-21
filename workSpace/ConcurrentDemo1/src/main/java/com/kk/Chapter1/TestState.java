package com.kk.Chapter1;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TestState")
public class TestState {

    public static void main(String[] args) {

        // NEW
        Thread t1 = new Thread(() -> {
            log.debug("running...");
        },"t1");

        // RUNNABLE
        Thread t2 = new Thread(() -> {
            while(true){

            }
        }, "t2");
        t2.start();

        // TERMINATED
        Thread t3 = new Thread(() -> {
            log.debug("running...");
        }, "t3");
        t3.start();

        // TIMED_WAITING
        Thread t4 = new Thread(() -> {
            synchronized (TestState.class){
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t4");
        t4.start();

        // WAITING
        Thread t5 = new Thread(() -> {
            try {
                t2.join(); // 没有时限
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t5");
        t5.start();

        // BLOCK
        Thread t6 = new Thread(() -> {
            synchronized (TestState.class){
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t6");
        t6.start();


        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1 state {}", t1.getState());
        log.debug("t2 state {}", t2.getState());
        log.debug("t3 state {}", t3.getState());
        log.debug("t4 state {}", t4.getState());
        log.debug("t5 state {}", t5.getState());
        log.debug("t6 state {}", t6.getState());
    }
}
