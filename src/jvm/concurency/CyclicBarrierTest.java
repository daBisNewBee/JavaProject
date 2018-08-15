package jvm.concurency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * 回环栅栏：
 *
 * "回环" 是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用；
 * "栅栏"就是等待的"条件"，等await后，线程就处于barrier了。
 *
 * A、B、C "一组线程"互相等待至某一条件，然后再统一执行。
 *
 * 区别于 "CountDownLatch"的 C 等待 A、B。
 *
 * CountDownLatch 是不能够重用的，而 CyclicBarrier 是可以重用的。
 *
 * 用于：
 *      用于多线程计算数据，最后合并计算结果的应用场景
 *
 * 参考：
 *  https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484869&idx=1&sn=d8b4816e7bc6d4438f56ee8127e717ea&chksm=eb509e27dc27173128c749ad24fe9d5eaf496a5e0c236594426340789e76ad166fa4320f7538&scene=38#wechat_redirect
 *
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        int N = 4;

        // 此处的 Runnable 预留所有线程到达barrier之后，进行的额外操作！
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程："+Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier).start();
        }

        Thread.sleep(5000);

        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier).start();
        }

    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyc) {
            cyclicBarrier = cyc;
        }

        @Override
        public void run() {
            super.run();
            try {
                int wait = 2;
//                int wait = new Random().nextInt(5);
                System.out.println(Thread.currentThread().getName() + " 正在写入数据....等待："+wait );
                Thread.sleep(wait * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "写入数据完毕....");

            try {
                // 超时后，先到达barrier状态的线程先执行后续任务
//                cyclicBarrier.await(2, TimeUnit.SECONDS);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" 所有线程写入完毕，继续处理其他任务...");

        }
    }

}
