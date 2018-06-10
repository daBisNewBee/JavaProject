package shotEveryDay.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *  Java Lock ����صļ�������������
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
        * ��������������ڵȴ��Ļ�ȡlock���̣߳����䲻�ٵȴ���ȥ����
        *
        * ע�⣺�������е��̣߳��ж�ʧЧ������
        *
        * ֻ���������̣߳�����interrupt��
        *
        * ��Ϊ������ �ȴ�lock������sleep
        *
        * */
        thread0.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException{
//        Lock lock = new ReentrantLock();

        System.out.println("LockInterruptiblyTest.insert:"+thread.getName());
        lock.lockInterruptibly();   //ע�⣬�����Ҫ��ȷ�жϵȴ������̣߳����뽫��ȡ���������棬Ȼ��InterruptedException�׳�
        try {
            System.out.println(thread.getName()+"�õ�����");


            Thread.sleep(10000);

            /*
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= 5000)
                    break;
                //��������
            }
            */

        }
        finally {
            System.out.println(Thread.currentThread().getName()+"ִ��finally");
            lock.unlock();
            System.out.println(thread.getName()+"�ͷ�����");
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
            System.out.println(Thread.currentThread().getName()+"���ж�");
        }
    }
}
