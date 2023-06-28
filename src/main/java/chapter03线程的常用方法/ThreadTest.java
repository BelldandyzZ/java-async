package chapter03线程的常用方法;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.ThreadTest")
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        //sleepTest();
        yieldTest();
    }

    private static void yieldTest() {
        log.debug("1");
        Thread.yield();
        log.debug("1");
        Thread.yield();
        log.debug("1");
        Thread.yield();
        log.debug("1");
        Thread.yield();
        log.debug("1");
    }

    private static void sleepTest() throws InterruptedException {
        Thread t = new Thread(()->{
            log.debug("{} 线程正在运行",Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.debug("wake up.....");
               e.printStackTrace();
            }

            log.debug("{} 线程睡眠完成，继续执行",Thread.currentThread().getName());

        },"sleep测试线程");
        t.start();

        log.debug("{} 是 {} 状态",t.getName(),t.getState());

        //Thread.sleep(5000);
        TimeUnit.SECONDS.sleep(5);

        log.debug("{} 是 {} 状态",t.getName(),t.getState());

        t.interrupt();



    }




}
