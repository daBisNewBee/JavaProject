package tools;

import utils.Log;

import java.util.Random;

public class ThreadLocalTest {

    private static ThreadLocal<Integer> x = ThreadLocal.withInitial( () -> {
        Log.v(Thread.currentThread().getName()+" Supplier called.");
        return 0;
    });

    // 支持多个ThreadLocal的索引
    private static ThreadLocal<Integer> y = ThreadLocal.withInitial( ()-> {
        Log.v(Thread.currentThread().getName()+" yy Supplier called.");
        return 0;
    });

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {

            new Thread( () -> {


                int data = new Random().nextInt();
                int data2 = new Random().nextInt();
                Log.v("first Thread "+Thread.currentThread().getName()+ " has get "+x.get());
                x.set(data);
                y.set(data2);

                Log.v("Thread "+Thread.currentThread().getName()+ " has set "+data);
                Log.v("Thread "+Thread.currentThread().getName()+ " has set "+data2);

                Log.v("Thread "+Thread.currentThread().getName()+ " has get "+x.get());
                Log.v("Thread "+Thread.currentThread().getName()+ " has get "+y.get());
                x.remove();
                Log.v("after remove. Thread "+Thread.currentThread().getName()+ " has get "+x.get());
            }).start();
        }
    }

}
