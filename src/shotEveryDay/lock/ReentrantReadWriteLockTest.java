package shotEveryDay.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 读写锁
 *
 * 读锁：rwl.readLock
 *
 * 写锁：rwl.writeLock
 *
 * 比较与"synchronized"的区别：
 *
 * 使用 readLock 时，多个读操作可并行，效率高于synchronized；
 *
 * 写锁与 （写操作、读操作）互斥，均需要等待
 *
 * 使用场合：
 *
 * 竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized
 *
 * 区别：
 *
 *           Lock         synchronized
 *
 * 性质：     接口           Java关键字
 *
 * 安全：     不会主动释放，    自动释放
 *（发生异常时） 可能死锁        不会死锁
 *
 * 响应中断    会            不会
 *
 * 成功获取lock  可以          不可以
 * 的结果
 *
 *            提高多个线程进行读操作的效率
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();


    public static void main(String[] args)  {

        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread(){
            public void run() {
                test.get_byReadLock(Thread.currentThread());
//                test.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.get_byReadLock(Thread.currentThread());
//                test.get(Thread.currentThread());
            };
        }.start();

    }

    public void get_byReadLock(Thread thread) {

        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

}
