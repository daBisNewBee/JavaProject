package jvm.concurency;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    /*
    *
    * 同时只允许5个线程访问，达到"控制某个方法被并发访问的个数"的目的！
    *
    * 比如解决：
    *   8个工人，只有5台机器问题
    *
    * */
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                test();
            }).start();
        }
    }

    private static void test() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" enter ..... "+ Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" exit ..... "+ Thread.currentThread().getName());
        semaphore.release();
    }
}
