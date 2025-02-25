package com.kk.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class Test3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("Running....");
                Thread.sleep(3000);
                return 100;
            }
        });

        Thread t3 = new Thread(task,"t3");
        t3.start();
        log.debug("return: {}",task.get());
    }
}
