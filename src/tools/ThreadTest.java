package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 总结：尽量使用Interrupt来结束线程
 *
 * Interrupt方法仅仅是对当前线程做了中断‘标记’，但是否真的结束线程运行，
 * 并不是由Java来完成的，需要开发者自己判断此标记，适当位置时机结束线程运行。
 *
 *
 */
public class ThreadTest {

    /**
     *
     * "interrupt 只会改变isInterrupted 的状态!"
     *
     * false
     * RUNNABLE
     *
     * 变为：
     *
     * true
     * RUNNABLE
     *
     */
    static void test() {
        Thread thread = new Thread(() -> {
            while (true){
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().getState());
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    /**
     * 处于sleep时， sleep会抛出异常，线程会响应"interrupt"
     * @throws InterruptedException
     */
    static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("running......");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 为什么是 false？因为发生异常后，中断状态被jvm清除了
                    System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
                    System.out.println("getState = " + Thread.currentThread().getState());
                    break; // 需要在这里退出，否则会一直执行
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    // 正确的线程中止方式
    static void test3() throws InterruptedException{
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()) {
                    System.out.println("ThreadTest.running.....");
                    Thread.currentThread().interrupt();
                }
            }
        };
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    private static Object mLock = new Object();

    // 不推荐这种方式终止线程，过于暴力，会造成资源泄漏。
    // 可能造成风险，比如线程A享有ReentenLock时，被stop后，并不会执行unlock放弃当前锁，其他线程也就无法获得；
    // 如果是synchronized实现的原子操作，是会主动释放锁的
    static void stopTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("1111");
//                Thread.sleep(5000);
                synchronized (mLock) {
                    mLock.wait(10000);
                }
                System.out.println("2222");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("e = " + e.getMessage());
            }
        });
        thread.start();
        Thread.sleep(1000);

        thread.stop();
    }

    /**
     *
     * join 的作用到底是什么？
     *
     * 原理：其实就是 wait
     *
     * TODO： 线程的几个运行状态
     *
     * @throws InterruptedException
     */
    static void joinTest() throws InterruptedException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Thread thread = new Thread(() -> {
            try {
                System.out.println("start      " + new Date());
                Thread.sleep(5000);
                System.out.println("end        " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("start join " + new Date());
        thread.join(2000);
        System.out.println("end   join " + new Date());
    }

    /**
     * 线程 是可以暂停、恢复的
     *
     * 不推荐使用！因为期间，无法释放锁，容易引起死锁！
     *
     * @throws InterruptedException
     */
    static void pauseOrResumeTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("into sleep " + new Date());
                try {
                    Thread.sleep(500);
                    System.out.println("after sleep " + new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("main thread wait 2s = " + new Date());
        Thread.sleep(2000);
        System.out.println("goto suspend");
        thread.suspend();

        System.out.println("main thread wait 2s = " + new Date());
        Thread.sleep(2000);
        System.out.println("goto resume");
        thread.resume();
    }

    public static void main(String[] args) {
//        test();
        try {
//            test2();
//            test3();
//            stopTest();
//            joinTest();
            pauseOrResumeTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
