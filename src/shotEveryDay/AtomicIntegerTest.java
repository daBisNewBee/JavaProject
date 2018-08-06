package shotEveryDay;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 使用场合：
 *      1. 非常简单的操作且又不想引入锁可以考虑使用CAS操作
 *      2. 非阻塞地完成某一操作也可以考虑CAS
 *
 * 隐患：
 *      1. ABA 问题（错误的认为当前线程是可以执行的，其实是发生了不一致现象）
 *
 * 解决：
 *      版本号递增
 *
 *
 * 为什么是非阻塞同步？
 *      基于冲突检测的乐观锁并发策略，简单的理解就是"我们先干了再说"。有冲突再来解决冲突
 *
 * 特点：
 * CAS是通过"硬件命令"保证了原子性，而i++没有，
 * 且硬件级别的原子性比i++这样高级语言的软件级别的运行速度要快地多
 *
 *
 * CAS指令在Intel CPU上称为CMPXCHG指令。
 *
 *
 * 其底层就是 volatile和CAS 共同作用的结果：

 1.首先使用了volatile 保证了内存可见性。

 2.然后使用了CAS（compare-and-swap）算法 保证了原子性。

 其中"CAS算法"的原理就是里面包含三个值：
    内存值A  预估值V  更新值B  当且仅当 V == A 时，V = B; 否则，不会执行任何操作。

 由硬件提供原子操作指令实现的高并发。
 *
 *
 */
public class AtomicIntegerTest {

    static AtomicInteger global = new AtomicInteger(0);

    static void inVoke() throws IllegalAccessException {
        Class<?> clazz = AtomicInteger.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :
                fields) {
            System.out.println("field = " + field);
            if (field.getName().equals("valueOffset")){
                field.setAccessible(true);
                Object object = field.get("valueOffset");
                System.out.println("object = " + object);
            }
            /*
            else if (field.getName().equals("value")){
                field.setAccessible(true);
                Object object = field.get("value");
                System.out.println("object = " + object);
            }
            */
        }
    }

    public static void main(String[] args) throws IllegalAccessException {

        AtomicInteger aInt1 = new AtomicInteger(1);

        AtomicInteger aInt2 = new AtomicInteger(1);

        System.out.println("aInt1.equals(aInt2):" + aInt2.equals(aInt1));

        System.out.println("get:"+(aInt1.get() == aInt2.get()));

        inVoke();

        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                global.getAndIncrement();
            }    
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                global.getAndIncrement();
            }
        }).start();

        while (Thread.activeCount() > 1){
            Thread.yield();
        }

        System.out.println("global = " + global.get());

    }
}
