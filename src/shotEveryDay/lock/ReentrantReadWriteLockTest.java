package shotEveryDay.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * ��д��
 *
 * ������rwl.readLock
 *
 * д����rwl.writeLock
 *
 * �Ƚ���"synchronized"������
 *
 * ʹ�� readLock ʱ������������ɲ��У�Ч�ʸ���synchronized��
 *
 * д���� ��д�����������������⣬����Ҫ�ȴ�
 *
 * ʹ�ó��ϣ�
 *
 * ������Դ�ǳ�����ʱ�����д����߳�ͬʱ����������ʱLock������ҪԶԶ����synchronized
 *
 * ����
 *
 *           Lock         synchronized
 *
 * ���ʣ�     �ӿ�           Java�ؼ���
 *
 * ��ȫ��     ���������ͷţ�    �Զ��ͷ�
 *�������쳣ʱ�� ��������        ��������
 *
 * ��Ӧ�ж�    ��            ����
 *
 * �ɹ���ȡlock  ����          ������
 * �Ľ��
 *
 *            ��߶���߳̽��ж�������Ч��
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
                System.out.println(thread.getName()+"���ڽ��ж�����");
            }
            System.out.println(thread.getName()+"���������");
        } finally {
            rwl.readLock().unlock();
        }
    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"���ڽ��ж�����");
        }
        System.out.println(thread.getName()+"���������");
    }

}
