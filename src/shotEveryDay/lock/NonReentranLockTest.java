package shotEveryDay.lock;

/**
 *
 * 不可重入锁举例
 *
 * 也验证了 "锁的分配机制：基于线程的分配，而不是基于方法调用的分配"
 *
 * https://www.cnblogs.com/dj3839/p/6580765.html
 *
 */
public class NonReentranLockTest {

//    ReEntranLock lock = new ReEntranLock();
    NoReEntranLock lock = new NoReEntranLock();

    public void print() throws InterruptedException {
        lock.lock();
        System.out.println("NonReentranLock.print:"+Thread.currentThread().getName());
        doAdd();
        lock.unlock();
    }

    public void doAdd() throws InterruptedException {
        lock.lock();
        //do something
        System.out.println("NonReentranLock.doAdd:"+Thread.currentThread().getName());
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {

        NonReentranLockTest test = new NonReentranLockTest();

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                try {
                    test.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
//        test.print();

        // 发生了死锁！ 只打印了 NonReentranLock.print
    }

    public class ReEntranLock{

        boolean isLocked = false;

        Thread  lockedBy = null;

        int lockedCount = 0;

        public synchronized void lock()
                throws InterruptedException{

            Thread thread = Thread.currentThread();

            // 重要！ 线程的判断! 可重入锁的关键！线程不同时，才进入wait
            while(isLocked && lockedBy != thread){
                System.out.println("thread  goint to WAIT = " + thread.getName());
                wait();
            }

            isLocked = true;
            lockedCount++;
            lockedBy = thread;
        }

        public synchronized void unlock(){

            if(Thread.currentThread() == this.lockedBy){

                System.out.println("thread  goint to unlock = " + Thread.currentThread().getName());

                lockedCount--;

                if(lockedCount == 0){
                    isLocked = false;
                    notify();
                }
            }
        }
    }

    // 典型的不可重入锁！
    public static class NoReEntranLock{

        private boolean isLocked = false;

        public synchronized void lock() throws InterruptedException{
            while(isLocked){
                wait();
            }
            isLocked = true;
        }

        public synchronized void unlock(){
            isLocked = false;
            notify();
        }
    }
}
