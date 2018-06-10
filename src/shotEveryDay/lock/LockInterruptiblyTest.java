package shotEveryDay.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *  Java Lock 锁相关的技术基础面试题
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484358&idx=1&sn=da0c7b7d10cab7d344d3d4f69a5cc2c8&chksm=eb509824dc2711325a9ab0994d13e1bcd6dd6be94c177d3531204e3c1102a2f29890ce11d4a3&scene=21#wechat_redirect
 *
 */
public class LockInterruptiblyTest {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args)  {
        LockInterruptiblyTest test = new LockInterruptiblyTest();
        MyThread thread0 = new MyThread(test);
        MyThread thread1 = new MyThread(test);
        thread0.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
        *
        * 可以主动打断正在等待的获取lock的线程，让其不再等待下去！！
        *
        * 注意：在运行中的线程，中断失效！！！
        *
        * 只有阻塞的线程，才能interrupt！
        *
        * 何为阻塞？ 等待lock；正在sleep
        *
        * */
        thread0.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException{
//        Lock lock = new ReentrantLock();

        System.out.println("LockInterruptiblyTest.insert:"+thread.getName());
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName()+"得到了锁");


            Thread.sleep(10000);

            /*
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= 5000)
                    break;
                //插入数据
            }
            */

        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

class MyThread extends Thread {
    private LockInterruptiblyTest test = null;
    public MyThread(LockInterruptiblyTest test) {
        this.test = test;
    }
    @Override
    public void run() {

        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}
