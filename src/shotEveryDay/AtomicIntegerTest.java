package shotEveryDay;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * ʹ�ó��ϣ�
 *      1. �ǳ��򵥵Ĳ������ֲ������������Կ���ʹ��CAS����
 *      2. �����������ĳһ����Ҳ���Կ���CAS
 *
 * ������
 *      1. ABA ���⣨�������Ϊ��ǰ�߳��ǿ���ִ�еģ���ʵ�Ƿ����˲�һ������
 *
 * �����
 *      �汾�ŵ���
 *
 *
 * Ϊʲô�Ƿ�����ͬ����
 *      ���ڳ�ͻ�����ֹ����������ԣ��򵥵�������"�����ȸ�����˵"���г�ͻ���������ͻ
 *
 * �ص㣺
 * CAS��ͨ��"Ӳ������"��֤��ԭ���ԣ���i++û�У�
 * ��Ӳ�������ԭ���Ա�i++�����߼����Ե��������������ٶ�Ҫ��ض�
 *
 *
 * CASָ����Intel CPU�ϳ�ΪCMPXCHGָ�
 *
 *
 * ��ײ���� volatile��CAS ��ͬ���õĽ����

 1.����ʹ����volatile ��֤���ڴ�ɼ��ԡ�

 2.Ȼ��ʹ����CAS��compare-and-swap���㷨 ��֤��ԭ���ԡ�

 ����"CAS�㷨"��ԭ����������������ֵ��
    �ڴ�ֵA  Ԥ��ֵV  ����ֵB  ���ҽ��� V == A ʱ��V = B; ���򣬲���ִ���κβ�����

 ��Ӳ���ṩԭ�Ӳ���ָ��ʵ�ֵĸ߲�����
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
