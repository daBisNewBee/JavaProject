package shotEveryDay.condition;

/**
 *
 * 线程间的协作
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
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
                System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
            }
        }
    }

}
