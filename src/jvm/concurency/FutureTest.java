package jvm.concurency;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 *
 * 背景：
 * 在看MVP-Clean中的"UseCase"执行的方式时，提到了Promise：
 * "We decided to use a command pattern and execute each
 * use case with a thread pool, but we can implement the
 * same with RxJava or Promises."
 *
 * Future 和 Promise的真正区别：
 * Future 可读， Promise 可写
 *
 * 解决的问题：
 * //280ms
 * String result1 = getRelatedRoles();
 * //250ms
 * String result2 = getRelatedNews();
 *
 * 问题特点：（Promise/Future模式来进行优化，优化效果还是很显著的）
 * 1. 下层逻辑不依赖于上层逻辑
 * 2. 这些逻辑通常是串行执行
 *
 * 参考：
 * 一个Netty封装的Promise的例子：
 * https://www.cnblogs.com/wade-luffy/p/6229410.html
 *
 * 一个假的Promise（类似Future）：
 * https://my.oschina.net/andylucc/blog/608499
 *
 * JAVA 拾遗--Future 模式与 Promise 模式：
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
     * Future 回调式
     * jdk1.8 开始提供"CompletableFuture"，代替框架封装的能力Netty、Guava，无需三方库
     *
     * @throws Exception
     */
    static void case2() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("执行耗时操作...");
                try {
                    costOpera();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 100;
            }
        });
        /*
        * 解决"回调地狱"问题:
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
                System.out.println("回调中的回调...");
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
                System.out.println("结果 integer = " + integer);
                latch.countDown();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("主线程耗时：" + (end-start) + "ms");
        latch.await();
    }

    /**
     * Future 将来式
     * get方法会阻塞主线程，适用多个耗时操作并发执行的场景
     *
     * @throws Exception
     */
    static void case1() throws Exception {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("执行耗时操作...");
                costOpera();
                return 100;
            }
        });// future 可以理解为 "一个运算结果的占位符"
        while (!future.isDone()){ } // 以此来轮询
        int ret = future.get();
        System.out.println("操作结果 ret = " + ret);
        long end = System.currentTimeMillis();
        System.out.println("主线程耗时：" + (end-start) + "ms");
    }
    /*
    *
    *
    *
    * */
}
