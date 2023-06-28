package chapter02Java线程的基本使用;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.ThreadDemo")
public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //m1();
        //m2();
        //m3();
        m4();

    }

    /**
     * 相关命令测试 jconsole，jps，jstack
     */
    private static void m4(){
        Runnable runnable = ()->{
            while (true){
                try {
                    Thread.sleep(2000);
                    log.debug("命令测试线程...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(runnable,"t线程");
        t.start();
    }

    //FutureTask
    private static void m3() throws ExecutionException, InterruptedException {

        FutureTask<?> task = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2000);
                log.debug("这是task线程");
                return "result";
            }
        });
        Thread t = new Thread(task,"task");
        t.start();
        log.debug("{}",task.get());//此时主线程会阻塞，等待task线程执行完毕才能得到get()的返回值
    }

    //把线程与任务分开
    private static void m2() {
        log.debug("main线程正在运行");
        //创建一个线程的任务
        Runnable runnable = () -> log.debug("runnable......");
        //创建一个线程
        Thread t = new Thread(runnable,"tttName11");
        t.start();
    }

    //创建线程
    private static void m1() {
        log.debug("run.....");
        Thread t = new Thread(){
            public void run() {
                log.debug("run.....");
            }
        };
        t.start();
    }




}
