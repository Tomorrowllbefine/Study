package thread_method;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

@Slf4j(topic = "Test1")
public class test1 {

    /**
     * @description: 测试run与start区别
     * @param: []
     * @return: void
     * @author: mmkk
     **/
    @Test
    public void testRunAndStart(){
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug(Thread.currentThread().getName());
                log.debug("running...");
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        //t1.run();
        t1.start();
        log.debug("do other things...");
    }


    /**
     * @description: 测试sleep与interrupt
     * @param: []
     * @return: void
     * @author: mmkk
     **/
    @Test
    public void testSleep() throws InterruptedException {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("fall asleep...");
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    log.debug("be interrupted...");
                    throw new RuntimeException(e);
                }
            }
        };

        t1.start();

        sleep(2000);
        log.debug("start interrupting...");
        t1.interrupt();
    }

    /**
     * @description: 打断正在运行的线程
     * @param: []
     * @return: void
     * @author: mmkk
     **/
    @Test
    public void testInterrupt_1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
           while(true){

               boolean interrupted = Thread.currentThread().isInterrupted();
               if(interrupted){
                   log.debug("被打断，退出循环");
                   break;
               }
           }
        }, "t1");
        t1.start();
        log.debug("before interrupt");
        sleep(2000);
        log.debug("interrupt");
        t1.interrupt();
    }


    @Test
    public void testInterrupt_2() throws InterruptedException {

        Thread t1 = new Thread(()->{
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态:{}", Thread.currentThread().isInterrupted());

            LockSupport.park();
            log.debug("unpark...");

        }, "t1");

        t1.start();

//        Thread.sleep(1000);
//        t1.interrupt();
    }





}
