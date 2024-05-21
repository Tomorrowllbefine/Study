package com.kk.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Test1")
public class Test1 {

    public static void main(String[] args) {
        // 创建线程
        Thread t = new Thread("t1"){
            @Override
            public void run() {
                // t1线程打印日志
                log.debug("running");
            }
        };
        // 启动线程（接受进程调度器调度）
        t.start();

        // 主线程的打印日志
        log.debug("running");
    }
}
