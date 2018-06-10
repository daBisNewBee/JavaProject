package shotEveryDay.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    private Lock lock = new ReentrantLock();    //ע������ط�

    public static void main(String[] args)  {

        final ReentrantLockTest test = new ReentrantLockTest();

        new Thread(){
            public void run() {
                test.insert_trylock(Thread.currentThread());
//                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert_trylock(Thread.currentThread());
//                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert_trylock(Thread thread) {
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"�õ�����");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"�ͷ�����");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"��ȡ��ʧ��");
        }
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"�õ�����");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"�ͷ�����");
            lock.unlock();
        }
    }
}
