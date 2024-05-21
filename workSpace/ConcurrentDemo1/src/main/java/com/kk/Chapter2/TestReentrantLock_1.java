package com.kk.Chapter2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class TestReentrantLock_1 {
    private static final ReentrantLock relock = new ReentrantLock();

    public static void main(String[] args) {
        relock.lock();
        try{
            m2();
        }finally {
            relock.unlock();
        }
    }

    public static void m2(){
        relock.lock();
        try{
            log.debug("method m2....");
            m3();
        }finally{
            relock.unlock();
        }
    }

    public static void m3(){
        relock.lock();
        try{
            log.debug("method m3....");
        }finally{
            relock.unlock();
        }
    }


}
