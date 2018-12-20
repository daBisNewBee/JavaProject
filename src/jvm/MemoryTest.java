package jvm;

import sun.misc.VM;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *  "�����ڴ�"��ֱ���ڴ棬ʵ����native�е�Cʹ��malloc������ڴ棩
 *
 *  �ŵ㣺���� IO ������������ƣ�
 *      Java��������ͨѶ����Java ���е����ݴ��䵽��磬����Ҫ��Java�Ѹ��Ƶ���Java�ѣ�
 *      ���ʹ��native�ѣ��������������Java�Ѻͷ�Java��֮���copy.
 *  ȱ�㣺
 *      1. ��Ҫ�ֶ����ա�������java yong gc��Ӱ�죩
 *      2. ��ʧJVM���Զ����������ص㣨�������ʹ�ö����ڴ�Ļ���
 *
 *  �����ٶ��ϣ����ڴ���죡
 *
 *  �����ٶ��ϣ�ֱ���ڴ���죡
 *
 * ���ۣ�
 *  ���ֱ���ڴ�ʹ������Ҫ���ڴ�ռ���Ƶ�����ʵĳ��ϣ���������Ƶ�������ͷ��ڴ�ĳ��ϡ�
 *
 *  �ο���
 *  Javaֱ���ڴ�����ڴ�
 *  https://www.cnblogs.com/z-sm/p/6235157.html?utm_source=itdadao&utm_medium=referral
 *
 *  Netty֮Java�����ڴ�ɨä����
 *  http://calvin1978.blogcn.com/articles/directbytebuffer.html
 *
 *  ��0��1��-���ҽ�������ڴ���������磺
 *  https://www.jianshu.com/p/50be08b54bee
 *
 *  Ϊʲô�ѻ���IO����ʱ����Ҫһ���û�̬���ں�̬�Ŀ���������
 *  https://blog.csdn.net/qiuwenjie123/article/details/79795699
 *
 */
public class MemoryTest {

    /**
     * ������ڴ棬��ʱ��48
     * ����ֱ���ڴ棬��ʱ��136
     */
    static void allocateMemoryTimeCost(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100 * 1000; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("������ڴ棬��ʱ��" + (end-start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100 * 1000; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
        }
        end = System.currentTimeMillis();
        System.out.println("����ֱ���ڴ棬��ʱ��" + (end-start));
    }

    /**
     * ���ڴ���ʣ���ʱ��304
     * ֱ���ڴ���ʣ���ʱ��149
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
        System.out.println("���ڴ���ʣ���ʱ��" + (end-start));

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
        System.out.println("ֱ���ڴ���ʣ���ʱ��" + (end-start));
    }

    static void getDirectMemorySize() throws Exception {
        /*
        * ͨ���������ö��ڴ��С��
        *   heap ByteBuffer -> -XX:Xmx

          ͨ����������ֱ���ڴ��С��
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
        * ���䳬����С��ֱ���ڴ棬�������
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
