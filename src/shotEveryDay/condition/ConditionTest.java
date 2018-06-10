package shotEveryDay.condition;

/**
 *
 * �̼߳��Э��
 *
 */
public class ConditionTest {

    public static Object object = new Object();

    public static void main(String[] args) {

        Thread0 thread0 = new Thread0();
        Thread1 thread1 = new Thread1();

        thread0.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.start();
    }

    static class Thread0 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("Thread0.run wait ======");
                    object.wait();
                    System.out.println("Thread0.run end ======");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("�߳�"+Thread.currentThread().getName()+"��ȡ������");
            }
        }
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("�߳�"+Thread.currentThread().getName()+"������object.notify()");
                System.out.println("�߳�"+Thread.currentThread().getName()+"�ͷ�����");
            }
        }
    }

}
