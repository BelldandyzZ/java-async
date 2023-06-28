join：等待某个线程运行结束然后获取它的结果，用于线程之间的通信



start：调用之后表示线程有争夺cpu时间片的权力了，此时线程处于NEW状态，start只能调用一次，否则抛出非法的线程状态异常



sleep：

- 让线程释放cpu时间片，从RUNNING进入一个有时限的TIMED WAITING状态
- 其它线程调用线程的interuput方法可以打断正在睡眠的线程，此时sleep方法抛出`InterruptedException`异常进入catch块
- 线程睡眠结束后不一定会立即往下执行，因为时间片已经释放了，需要再次争夺到时间片才能继续执行
- 使用TimeUnit的sleep来代替Thread的sleep提高可读性

```java
TimeUnit.SECONDS.sleep(5);
```



yield

- 让出cpu时间片的使用权，让线程从RUNNING进入RUNNABLE状态
- 具体实现依赖任务调度器，如果只有一个线程，即使yeild之后。任务调度器时间片还是分配给本线程

> TIEMD WATTING 和 RUNNABLE。任务调度器时间片不会把时间片分配给TIEMD WATTING 状态的线程



setPriority线程的优先级

- 线程优先级可以提示任务调度器有限调用该线程，但仅仅是一个提示，任务调度器可以忽略。默认的优先级是5
- 当cpu繁忙的时候，优先级高的线程会获得更多的时间片，cpu空闲时优先级几乎没用





带着这几个知识点去理解：

线程的切换：线程的调度会在程序计数器中记录当前执行到了哪一行代码，下次争夺到cpu时间片就继续接着上次的位置执行，比如执行了yield之后，下次有了时间片，接着yield的位置往下执行。