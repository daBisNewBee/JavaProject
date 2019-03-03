package shotEveryDay.lock;

public class DeadLock {

    private static Object lock_a = new byte[0];
    private static Object lock_b = new byte[0];

    /*
    *
    * 死锁：两者互为条件
    * 线程A要"lock_b"才愿意释放"lock_a"
    *
    * 线程B要"lock_a"才愿意释放"lock_b"
    *
     * */
    static class ThreadA extends Thread{

        public void run(){
            synchronized (lock_a){
                try {
                    System.out.println("ThreadA.run get lock_a...");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock_b){
                    System.out.println("ThreadA.run get lock_b...");
                }
                System.out.println("ThreadA.run release lock_b...");

            }
            System.out.println("ThreadA.run release lock_a...");
        }
    }

    static class ThreadB extends Thread{

        public void run(){
            synchronized (lock_b){
                System.out.println("ThreadB.run get lock_b...");
                synchronized (lock_a){
                    System.out.println("ThreadB.run get lock_a...");
                }
                System.out.println("ThreadB.run release lock_a...");
            }
            System.out.println("ThreadB.run release lock_b...");
        }
    }

    public static void main(String[] args) {
        Thread threada = new ThreadA();
        Thread threadb = new ThreadB();
        threada.start();
        threadb.start();
    }
}
