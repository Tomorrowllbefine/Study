package com.kk.application;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 互斥-synchronized
 * @author: mmkk
 **/
@Slf4j(topic = "Case2")
public class Case2 {

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 5000; i++){
                synchronized (room) {
                    room.increment();
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 5000; i++){
                synchronized (room) {
                    room.decrement();
                }
            }
        }, "t2");
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    log.debug("{}", room.get());
    }
}

/**
 * @description: 共享变量独立封装成类
 * @author: mmkk
 **/
class Room{
    // 共享变量
    private int value = 0;

    public void increment(){
        synchronized (this) {
            value++;
        }
    }

    public void decrement(){
        synchronized (this){
            value--;
        }
    }

    public int get(){
        synchronized (this){
            return value;
        }
    }

}
