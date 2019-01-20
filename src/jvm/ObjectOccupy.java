package jvm;

import org.apache.lucene.util.RamUsageEstimator;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Java对象占用内存空间的几个思考记录：
 *
 * 1. Java对象内存布局：
 *  对象头(实际占用 12/16 byte，但是表现均为16Byte，因为包括padding)
 *  +
 *  对象实际数据(基本数据类型，或者其他对象引用)
 *  +
 *  对齐填充(为何达到要求：64位Linux系统，所以字节对齐必须是8的倍数)
 *
 * 2. 对象头构成、占用大小：
 * （Java HotSpot(TM) 64-Bit Server VM下，）
 *  实例的对象头大小为 12bytes（8bytes makOop + 4 bytes klassOop），
 *  Java数组实例的对象头大小为 16bytes（8bytes makOop + 4 bytes klassOop + 4 bytes length）
 *      （关闭UseCompressedOops后，klassOop 增加到 8bytes）
 *
 * 参考：
 * Java对象占用内存空间分析及实战：
 * https://www.jianshu.com/p/40faea07d4d2
 *
 * new一个Object对象占用多少内存？（对"Java对象内存布局"的解释比较好）
 * https://my.oschina.net/apdplat/blog/208456
 */
public class ObjectOccupy {

    /*
    * 自定义对象占用空间：
    *
    * sizeOf(MyBean) = 24 bytes
    *   = 12(Header) + 8(long) + 4(int) = 24 bytes
    * 注意：
    *   1. static对象num不占用实例空间！存放在全局数据段
    *   2. "对象引用"占据4byte：(32位OS，及64位OS开启"UseCompressedOops(默认开启)"时)
    *       Integer ii、jj、mm时，shallowSize 为 24；（12 + 3*4）
    *       Integer ii,jj,mm,nn时，shallowSize 为 32。(12 + 4*4 + 4padding)
    *
    * 其他：
    *  1. Vmtions的开关方法，以"UseCompressedOops"为例：
    *  开启：
    *   -XX:+UseCompressedOops
    *  关闭：
    *   -XX:-UseCompressedOops
    *
        2. UseCompressedOops开/关的结果比较：
        long:
        24/24
        new byte[0]
        16/24
        new byte[1]
        24/32
        new Object:
        16/16
        Object null:
        0/0
        Object MyBean:
        24/32
        sizeOf(array0) = 16/24 bytes
        length(array0) = 0/0 bytes
        sizeOf(array1) = 24/32 bytes
        sizeOf(array2) = 24/32 bytes
        sizeOf(array3) = 32/40 bytes
        sizeOf(array8) = 48/56 bytes
        sizeOf(array9) = 56/64 bytes
        sizeOf(array10) = 56/64 bytes
    * */
    static class MyBean{
        int i;
        long j;
//        Integer ii,jj,mm;
//        Integer ii,jj,mm, nn;
        private static int num;
    }

    public static void main(String[] args) throws Exception{
        // 此种方式需要jar包使用
//        Instrumentation instr = new Instrumentation();
//        RamUsageEstimator.sizeOf((int)1);
        System.out.println("long:");
        System.out.println(RamUsageEstimator.sizeOf(new Long(128)));
        // 联系 synchronize时，实例对象的方式，为何lock = new byte[0]?
        System.out.println("new byte[0]");
        System.out.println(RamUsageEstimator.sizeOf(new byte[0]));
        System.out.println("new byte[1]");
        System.out.println(RamUsageEstimator.sizeOf(new byte[1]));
        System.out.println("new Object:");
        System.out.println(RamUsageEstimator.shallowSizeOf(new Object()));
        System.out.println("Object null:");
        System.out.println(RamUsageEstimator.shallowSizeOf((Object) null));
        System.out.println("Object MyBean:");
        System.out.println(RamUsageEstimator.shallowSizeOf(new MyBean()));
        /*
        *
        * shallowSize 与 retainSize的区别：
        * 1. Shallow size就是对象本身占用内存的大小，不包含其引用的对象。
        * 2. Retained size是该对象自己的shallow size，加上从该对象能直接或间接访问到对象的shallow size之和。
        *   换句话说，retained size是该对象被GC之后所能回收到内存的总和。
        *
        * 参考：
        * https://blog.csdn.net/smithdoudou88/article/details/42869529
        * */

        // 分析一下结果？ 可以了解对象数组占用内存大小的原理："对象实际数据 + padding"的变化关系
        int[] array0 = new int[0];
        int[] array1 = new int[1];
        int[] array2 = new int[2];
        int[] array3 = new int[3];
        int[] array8 = new int[8];
        int[] array9 = new int[9];
        int[] array10 = new int[10];
        System.out.printf("sizeOf(array0) = %s bytes\n", RamUsageEstimator.sizeOf(array0));
        System.out.printf("length(array0) = %s bytes\n", array0.length);
        System.out.printf("sizeOf(array1) = %s bytes\n", RamUsageEstimator.sizeOf(array1));
        System.out.printf("sizeOf(array2) = %s bytes\n", RamUsageEstimator.sizeOf(array2));
        System.out.printf("sizeOf(array3) = %s bytes\n", RamUsageEstimator.sizeOf(array3));
        System.out.printf("sizeOf(array8) = %s bytes\n", RamUsageEstimator.sizeOf(array8));
        System.out.printf("sizeOf(array9) = %s bytes\n", RamUsageEstimator.sizeOf(array9));
        System.out.printf("sizeOf(array10) = %s bytes\n", RamUsageEstimator.sizeOf(array10));

        Class clz = Class.forName("org.apache.lucene.util.RamUsageEstimator");
        // 打印原生类型(基本类型)占用空间：
        Field field = clz.getDeclaredField("primitiveSizes");
        field.setAccessible(true);
        System.out.println("field = " + field);
        Object primitiveSizesMap = field.get(null);
        Set<Map.Entry> set = ((Map)primitiveSizesMap).entrySet();
        for (Map.Entry one:
            set) {
//            System.out.println("one = " + one);
        }

    }
}
