package com.kk.Chapter6;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j(topic = "Test_invokeAll")
public class Test_invokeAll_invokeAny {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        test_invokeAll();
        test_invokeAny();
    }

    /**
     * @description: 测试invokeAll()
     * @param: []
     * @return: void
     * @author: mmkk
     **/
    public static void test_invokeAll() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        List<Future<Object>> futureList = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin:{}", 1);
                    Thread.sleep(1000);
                    log.debug("end:{}",1);
                    return "1";
                },
                () -> {
                    log.debug("begin:{}", 2);
                    Thread.sleep(500);
                    log.debug("end:{}",2);
                    return "2";
                },
                () -> {
                    log.debug("begin:{}", 3);
                    Thread.sleep(2000);
                    log.debug("end:{}",3);
                    return "3";
                }
        ));

        futureList.forEach(f -> {
            try {
                log.debug("{}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * @description: 测试invokeAny()
     * @param: []
     * @return: void
     * @author: mmkk
     **/
    public static void test_invokeAny() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Object result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin:{}", 1);
                    Thread.sleep(1000);
                    log.debug("end:{}", 1);
                    return "1";
                },
                () -> {
                    log.debug("begin:{}", 2);
                    Thread.sleep(500);
                    log.debug("end:{}", 2);
                    return "2";
                },
                () -> {
                    log.debug("begin:{}", 3);
                    Thread.sleep(2000);
                    log.debug("end:{}", 3);
                    return "3";
                }
        ));

        log.debug("result: {}", result);
    }

}
