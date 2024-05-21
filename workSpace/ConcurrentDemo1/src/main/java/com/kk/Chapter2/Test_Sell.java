package com.kk.Chapter2;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @description: 买票问题
 * @author: mmkk
 **/
@Slf4j
public class Test_Sell {


    public static void main(String[] args) {
        TicketWindow window = new TicketWindow(2000);
        List<Thread> threadList = new ArrayList<>();
        // 卖多少张票
        List<Integer> sellCount = new Vector<>();
        for(int i = 0; i < 2000; i++){
            Thread t = new Thread( () -> {
                // 分析竞态条件
                int count = window.sell(randomAmount());
                sellCount.add(count);
            });
            threadList.add(t);
            t.start();
        }

        // 等待所有线程执行结束
        for(int i = 0; i < 2000; i ++){
            Thread thread = threadList.get(i);
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 统计
        log.debug("销售: {}", sellCount.stream().mapToInt(c -> c).sum());
        // 剩余票数
        log.debug("销售: {}", window.getCount());
    }

    static Random random = new Random();
    public static int randomAmount(){
        return random.nextInt(5) + 1;
    }
}

class TicketWindow{
    private int count;

    public TicketWindow(int count){
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public int sell(int quantity){
        if(this.count > quantity){
            this.count -= quantity;
            return count;
        }else{
            return 0;
        }
    }
}
