package tools;

import java.util.concurrent.TimeUnit;

/**
 *
 * 简单方法：
 *
 * 使用interrupt方法来终端线程可分为两种情况：

 （1）线程处于阻塞状态，如使用了sleep方法。

 （2）使用while（！isInterrupted（））{……}来判断线程是否被中断。
 *
 * 复杂方法：
 *
 * 其他 flag的停止线程方式，这里不再说明
 *
 *  使用interrupt() 停止线程的几个步骤
 *
 *  参考sleepThread，不是busyThread！
 *
 *  原理：
 *      从Java的API中可以看到，许多声明抛出InterruptedException的方法，比如Sleep
 *
 *  过程：
 *      1. sleepThread.interrupt();
 *      2. JVM 将中断标志位清除；
 *      3. Java API 抛出 "InterruptedException"
 *      4. 复位标志位：Thread.currentThread().interrupt();
 *      5. 判断isInterrupted()，结束线程
 *
 *
 *  https://blog.csdn.net/zbw18297786698/article/details/53432879
 *
 */
public class ThreadStopTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());
        System.out.println("ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());
//        Thread.currentThread().interrupt();
        // 注意： "interrupted()" 会清除标记
        System.out.println("AFTER ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());
        System.out.println("AFTER ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());


        Thread sleepThread = new Thread(()->{
            while (true){
                try {

                    // 4. 判断中断标志位，发生中断，退出当前线程
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("isInterrupted. true. break.");
                        break;
                    }

                    // 2. 响应中断：sleepThread.interrupt()
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("e = " + e.getMessage());
                    // Thread.sleep()方法由于中断抛出异常。
                    // Java虚拟机会先将该线程的中断标识位清除，然后抛出InterruptedException，
                    // 因为在发生InterruptedException异常的时候，会清除中断标记
                    // 如果不加处理，那么下一次循环开始的时候，就无法捕获这个异常。
                    // 故在异常处理中，再次设置中断标记位
                    // 3. 对中断标志位进行复位。不然始终是false
                    break;
//                    Thread.currentThread().interrupt();

                    // 注意：这里可以直接break，复位的方法复杂了！
                }
            }
        });
        Thread busyThread = new Thread(()->{
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("busyThread isInterrupted true.");
                    break;
                }else {
//                    System.out.println("========");
                }
            }
        });
        sleepThread.start();
        busyThread.start();
        TimeUnit.SECONDS.sleep(2);
//        System.out.println("sleepThread = " + sleepThread.isInterrupted());
//        System.out.println("busyThread = " + busyThread.isInterrupted());

        /*
        * sleepThread 能够响应 interrupt的中断
        * busyThread 不能！因为无sleep，无法抛出"InterruptedException"
        *
        * */
        // 1. 触发中断
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread = " + sleepThread.isInterrupted());
        System.out.println("busyThread = " + busyThread.isInterrupted());
        TimeUnit.SECONDS.sleep(2);

    }
}
