package com.kk.thread;

public class Test4 {

    public static void main(String[] args) {
        new Thread(()->{while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        },"t4_1").start();

        new Thread(()->{while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        },"t4_2").start();

        Thread thread = new Thread();
    }

}
