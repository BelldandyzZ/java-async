# 一、线程的创建

## ①Thread

```java
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
```



## ②Runnable

```java
//把线程与任务分开
private static void m2() {
    log.debug("main线程正在运行");
    //创建一个线程的任务
    Runnable runnable = () -> log.debug("runnable......");
    //创建一个线程
    Thread t = new Thread(runnable,"tttName11");
    t.start();
}
```



## ③FutureTask

> FutureTaskRunnable的扩展,可以接收Callable类型的参数,用来处理有返回结果的情况

```java
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
```

> get方法所在的线程会阻塞，知道子线程执行完毕



## ④继承关系

![](img/主要的类创建线程中的继承关系.jpg)



# 二、基础命令

- jps ：查看所有java进程

- jstack <PID>： 查看某个java进程的所有线程状态

- jcosole：图形化界面查看某个Java进程中线程的运行情况







