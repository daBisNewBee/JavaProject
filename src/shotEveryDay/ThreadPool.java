package shotEveryDay;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadPool {

    static void newCachedThreadPool(){

        // 1. �ɻ����̳߳�
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 2. ָ�������߳��������̳߳�.һ��������������̳߳أ�
        // �������̳߳���߳���Ч�ʺͽ�ʡ�����߳�ʱ���ĵĿ������ŵ㡣
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 3. ����һ�����̻߳���Executor�������ص��ǿɱ�֤˳���ִ�и�������
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 4. ����һ���������̳߳أ�����֧�ֶ�ʱ���Լ������Ե�����ִ�У�֧�ֶ�ʱ������������ִ�С�
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

        // ��ǰCPU���֧�ֹ����߳���
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors = " + processors);

        newCachedThreadPool();

        CallableAndFuture();
    }

}
