package jvm.concurency;

import java.util.concurrent.CountDownLatch;

/**
 *
 * C 等待 A、B结束后，C再执行。
 *
 * "CountDownLatch 一般用于某个线程 A 等待若干个其他线程执行完任务之后，它才执行。"
 *
 * 无法重用！
 *
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+" 正在执行。。。");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" 执行完毕。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            System.out.println("等待2个子线程执行完毕...");
            countDownLatch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
