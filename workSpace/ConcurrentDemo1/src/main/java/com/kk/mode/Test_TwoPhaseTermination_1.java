package com.kk.mode;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 测试两阶段终止模式
 * @author: mmkk
 **/
public class Test_TwoPhaseTermination_1 {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500); // 执行一段时间
        tpt.stop();
    }
}

@Slf4j(topic = "Test_TwoPhaseTermination")
class TwoPhaseTermination{
    private Thread monitor;

    public void start() {
        monitor = new Thread(()->{
            while(true){
                Thread currentThread = Thread.currentThread();
                if(currentThread.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000); // 情况1-睡眠有异常
                    log.debug("执行监控记录"); // 情况2-睡眠无异常
                } catch (InterruptedException e) {
                    log.error("睡眠异常:{}", e);
                    // 重新设置打断标记
                    currentThread.interrupt();
                }
            }
        }, "monitor");

        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }
}