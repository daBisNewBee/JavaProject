package jvm.concurency;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 *
 * ������
 * �ڿ�MVP-Clean�е�"UseCase"ִ�еķ�ʽʱ���ᵽ��Promise��
 * "We decided to use a command pattern and execute each
 * use case with a thread pool, but we can implement the
 * same with RxJava or Promises."
 *
 * Future �� Promise����������
 * Future �ɶ��� Promise ��д
 *
 * ��������⣺
 * //280ms
 * String result1 = getRelatedRoles();
 * //250ms
 * String result2 = getRelatedNews();
 *
 * �����ص㣺��Promise/Futureģʽ�������Ż����Ż�Ч�����Ǻ������ģ�
 * 1. �²��߼����������ϲ��߼�
 * 2. ��Щ�߼�ͨ���Ǵ���ִ��
 *
 * �ο���
 * һ��Netty��װ��Promise�����ӣ�
 * https://www.cnblogs.com/wade-luffy/p/6229410.html
 *
 * һ���ٵ�Promise������Future����
 * https://my.oschina.net/andylucc/blog/608499
 *
 * JAVA ʰ��--Future ģʽ�� Promise ģʽ��
 * https://cloud.tencent.com/developer/article/1110576
 *
 */
public class FutureTest {

    static void costOpera() throws InterruptedException {
            Thread.sleep(3000);
    }

    public static void main(String[] args) throws Exception{
//        case1();
        case2();
    }

    /**
     * Future �ص�ʽ
     * jdk1.8 ��ʼ�ṩ"CompletableFuture"�������ܷ�װ������Netty��Guava������������
     *
     * @throws Exception
     */
    static void case2() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("ִ�к�ʱ����...");
                try {
                    costOpera();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 100;
            }
        });
        /*
        * ���"�ص�����"����:
        *  asyncFunc1(opt, (...args1) => {
            asyncFunc2(opt, (...args2) => {
               asyncFunc3(opt, (...args3) => {
                    asyncFunc4(opt, (...args4) => {
                        // some operation
                    });
                });
             });
            });
        * */
        completableFuture = completableFuture.thenCompose(i -> {
            return CompletableFuture.supplyAsync( () -> {
                System.out.println("�ص��еĻص�...");
                try {
                    costOpera();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i+100;
            });
        });

        completableFuture.whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                System.out.println("��� integer = " + integer);
                latch.countDown();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("���̺߳�ʱ��" + (end-start) + "ms");
        latch.await();
    }

    /**
     * Future ����ʽ
     * get�������������̣߳����ö����ʱ��������ִ�еĳ���
     *
     * @throws Exception
     */
    static void case1() throws Exception {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("ִ�к�ʱ����...");
                costOpera();
                return 100;
            }
        });// future �������Ϊ "һ����������ռλ��"
        while (!future.isDone()){ } // �Դ�����ѯ
        int ret = future.get();
        System.out.println("������� ret = " + ret);
        long end = System.currentTimeMillis();
        System.out.println("���̺߳�ʱ��" + (end-start) + "ms");
    }
    /*
    *
    *
    *
    * */
}
