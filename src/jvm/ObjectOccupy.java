package jvm;

import org.apache.lucene.util.RamUsageEstimator;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Java����ռ���ڴ�ռ�ļ���˼����¼��
 *
 * 1. Java�����ڴ沼�֣�
 *  ����ͷ(ʵ��ռ�� 12/16 byte�����Ǳ��־�Ϊ16Byte����Ϊ����padding)
 *  +
 *  ����ʵ������(�����������ͣ�����������������)
 *  +
 *  �������(Ϊ�δﵽҪ��64λLinuxϵͳ�������ֽڶ��������8�ı���)
 *
 * 2. ����ͷ���ɡ�ռ�ô�С��
 * ��Java HotSpot(TM) 64-Bit Server VM�£���
 *  ʵ���Ķ���ͷ��СΪ 12bytes��8bytes makOop + 4 bytes klassOop����
 *  Java����ʵ���Ķ���ͷ��СΪ 16bytes��8bytes makOop + 4 bytes klassOop + 4 bytes length��
 *      ���ر�UseCompressedOops��klassOop ���ӵ� 8bytes��
 *
 * �ο���
 * Java����ռ���ڴ�ռ������ʵս��
 * https://www.jianshu.com/p/40faea07d4d2
 *
 * newһ��Object����ռ�ö����ڴ棿����"Java�����ڴ沼��"�Ľ��ͱȽϺã�
 * https://my.oschina.net/apdplat/blog/208456
 */
public class ObjectOccupy {

    /*
    * �Զ������ռ�ÿռ䣺
    *
    * sizeOf(MyBean) = 24 bytes
    *   = 12(Header) + 8(long) + 4(int) = 24 bytes
    * ע�⣺
    *   1. static����num��ռ��ʵ���ռ䣡�����ȫ�����ݶ�
    *   2. "��������"ռ��4byte��(32λOS����64λOS����"UseCompressedOops(Ĭ�Ͽ���)"ʱ)
    *       Integer ii��jj��mmʱ��shallowSize Ϊ 24����12 + 3*4��
    *       Integer ii,jj,mm,nnʱ��shallowSize Ϊ 32��(12 + 4*4 + 4padding)
    *
    * ������
    *  1. Vmtions�Ŀ��ط�������"UseCompressedOops"Ϊ����
    *  ������
    *   -XX:+UseCompressedOops
    *  �رգ�
    *   -XX:-UseCompressedOops
    *
        2. UseCompressedOops��/�صĽ���Ƚϣ�
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
        // ���ַ�ʽ��Ҫjar��ʹ��
//        Instrumentation instr = new Instrumentation();
//        RamUsageEstimator.sizeOf((int)1);
        System.out.println("long:");
        System.out.println(RamUsageEstimator.sizeOf(new Long(128)));
        // ��ϵ synchronizeʱ��ʵ������ķ�ʽ��Ϊ��lock = new byte[0]?
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
        * shallowSize �� retainSize������
        * 1. Shallow size���Ƕ�����ռ���ڴ�Ĵ�С�������������õĶ���
        * 2. Retained size�Ǹö����Լ���shallow size�����ϴӸö�����ֱ�ӻ��ӷ��ʵ������shallow size֮�͡�
        *   ���仰˵��retained size�Ǹö���GC֮�����ܻ��յ��ڴ���ܺ͡�
        *
        * �ο���
        * https://blog.csdn.net/smithdoudou88/article/details/42869529
        * */

        // ����һ�½���� �����˽��������ռ���ڴ��С��ԭ��"����ʵ������ + padding"�ı仯��ϵ
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
        // ��ӡԭ������(��������)ռ�ÿռ䣺
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
