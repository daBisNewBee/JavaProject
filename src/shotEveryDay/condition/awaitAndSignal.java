package shotEveryDay.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484371&idx=1&sn=96e342cc909dc8d6b1303e3cbb6812e4&chksm=eb509831dc271127f6d20c47ee463e7ce6e3e5ae742e20b9acf751588688969dd23ff2d17f14&scene=38#wechat_redirect
 *
 *  Java 并发显式协作 Condition
 *              await、signal、signalAll 需要与显式锁 Lock 配合使用（Lock.newCondition()）
 *
 *       普通并发协作
 *              wait、notify、notifyAll 需要与 synchronized 配合使用
 *
 *   await 提供了比 wait 更加强大的机制，譬如提供了可中断或者不可中断的 await 机制等
 *
 *   使用多个条件变量，避免大量唤醒导致竞争从而减低效率
 *
 * Conditon中的await()对应Object的wait()；
 Condition中的signal()对应Object的notify()；
 Condition中的signalAll()对应Object的notifyAll()。
 *
 *
 */
public class awaitAndSignal {

    private int queueSize = 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args)  {

        awaitAndSignal test = new awaitAndSignal();

        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == 0){
                        try {
                            System.out.println("队列空，等待数据");
                            /*
                            * 关键：
                            *
                            * 和 wait 一样，await 在进入等待队列后会释放锁和 cpu，
                            * 当被其他线程唤醒或者超时或中断后都需要重新获取锁，
                            * 获取锁后才会从 await 方法中退出
                            * */
                            notEmpty.await();
                            System.out.println("Consumer.await end ===== ");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();                //每次移走队首元素
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                } finally{
                    System.out.println("Consumer.unlock");
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == queueSize){
                        try {
                            System.out.println("队列满，等待有空余空间");
                            notFull.await();
                            System.out.println("Producer.await end =====");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);        //每次插入一个元素
                    notEmpty.signal();
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                } finally{
                    System.out.println("Producer.unlock");
                    lock.unlock();
                }
            }
        }
    }

}
