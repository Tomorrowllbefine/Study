package com.kk.Chapter2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 成员变量 与 局部变量的线程安全问题测试
 * @author: mmkk
 **/

public class TestThreadSafe {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        // ThreadUnsafe test = new ThreadUnsafe();
        // ThreadSafe test = new ThreadSafe();
        ThreadSafeSubClass test = new ThreadSafeSubClass();
        for(int i = 0; i < THREAD_NUMBER; i ++){
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + i).start();
        }
    }
}

@Slf4j
class ThreadUnsafe{
    List<Integer> list = new ArrayList<>();
    public void method1(int loopNumber){
        for(int i = 0; i < loopNumber; i++){ // 临界区
            method2();
            method3();
        }
    }

    private void method2(){
        //log.debug("add...");
        list.add(1);
    }

    private void method3(){
        //log.debug("remove...");
        list.remove(0);
    }
}


@Slf4j
class ThreadSafe{
    public void method1(int loopNumber){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < loopNumber; i++){ // 临界区
            method2(list);
            method3(list);
        }
    }

    public void method2(List<Integer> list){
        //log.debug("add...");
        list.add(1);
    }

    public void method3(List<Integer> list){
        //log.debug("remove...");
        list.remove(0);
    }
}



class ThreadSafeSubClass extends ThreadSafe{
    @Override
    public void method3(List<Integer> list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}