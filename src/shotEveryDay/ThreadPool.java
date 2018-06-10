package shotEveryDay;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadPool {

    static void newCachedThreadPool(){

        // 1. 可缓存线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 2. 指定工作线程数量的线程池.一个典型且优秀的线程池，
        // 它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 3. 创建一个单线程化的Executor。最大的特点是可保证顺序地执行各个任务
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 4. 创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        scheduledThreadPool.schedule(()->{
            System.out.println("delay = 3s");
        },3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(()->{
            System.out.println("delay 1s = 3s update once" + scheduledThreadPool);
        },1,3,TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {

            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            threadPool.execute( ()->{
                    System.out.println("index = " + index);
                    long tid = Thread.currentThread().getId();
//                    System.out.println("tid = " + tid);
                System.out.println("threadPool = " + threadPool);
                }
            );
        }
    }

    static void CallableAndFuture() throws ExecutionException, InterruptedException {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(10000);
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        thread.join();

        System.out.println("futureTask = " + futureTask.get());


        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        Future<Integer> future = threadPool.submit(callable);

        int ret = future.get();
        System.out.println("ret = " + ret);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 当前CPU最大支持工作线程数
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors = " + processors);

        newCachedThreadPool();

        CallableAndFuture();
    }

}
