package jvm;

import sun.misc.VM;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *  分配速度上，堆内存更快！
 *
 *  访问速度上，直接内存更快！
 *
 * 结论：
 *  因此直接内存使用于需要大内存空间且频繁访问的场合，不适用于频繁申请释放内存的场合。
 *
 *  参考：
 *  Java直接内存与堆内存
 *  https://www.cnblogs.com/z-sm/p/6235157.html?utm_source=itdadao&utm_medium=referral
 *
 */
public class MemoryTest {

    /**
     * 分配堆内存，耗时：48
     * 分配直接内存，耗时：136
     */
    static void allocateMemoryTimeCost(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100 * 1000; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("分配堆内存，耗时：" + (end-start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100 * 1000; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
        }
        end = System.currentTimeMillis();
        System.out.println("分配直接内存，耗时：" + (end-start));
    }

    /**
     * 堆内存访问，耗时：304
     * 直接内存访问，耗时：149
     */
    static void accessTimeCost(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000 * 1000; i++) {
            for (int j = 0; j < 100; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (int j = 0; j < 100; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println("堆内存访问，耗时：" + (end-start));

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000 * 1000; i++) {
            for (int j = 0; j < 100; j++) {
                directBuffer.putInt(j);
            }
            directBuffer.flip();
            for (int j = 0; j < 100; j++) {
                directBuffer.getInt();
            }
            directBuffer.clear();
        }
        end = System.currentTimeMillis();
        System.out.println("直接内存访问，耗时：" + (end-start));
    }

    static void getDirectMemorySize() throws Exception {
        /*
        * 通过参数设置堆内存大小：
        *   heap ByteBuffer -> -XX:Xmx

          通过参数设置直接内存大小：
            direct ByteBuffer -> -XX:MaxDirectMemorySize=1M
        * */
        System.out.println("maxDirectMemory:" + VM.maxDirectMemory());

        Class<?> c = Class.forName("java.nio.Bits");
        Field maxMemory = c.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        synchronized (c) {
            Long maxMemoryValue = (Long)maxMemory.get(null);
            System.out.println("maxMemoryValue:"+maxMemoryValue);
        }

        /*
        * 分配超过大小的直接内存，会溢出：
        *
        * maxMemoryValue:1024
        Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
	    at java.nio.Bits.reserveMemory(Bits.java:658)
        * */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1025);
    }

    public static void main(String[] args) throws Exception{
//        allocateMemoryTimeCost();
//        accessTimeCost();
        getDirectMemorySize();
    }
}
