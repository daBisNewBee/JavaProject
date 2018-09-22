package basic;

import java.io.*;

/**
 *
 * ��ö���෴������Դ�����˽�Ϊʲô ö�����������ʵ�ֵ�����
 *
 * userdeMacBook-Pro:JavaProject user$ javap -p out/production/JavaProject/basic/SingleTonEnum.class
     Compiled from "SingleTonEnum.java"
     public final class basic.SingleTonEnum extends java.lang.Enum<basic.SingleTonEnum> {
     public static final basic.SingleTonEnum INSTANCE;
//     public static final basic.SingleTonEnum INSTANCEEX;
     private static final basic.SingleTonEnum[] $VALUES;
     public static basic.SingleTonEnum[] values();
     public static basic.SingleTonEnum valueOf(java.lang.String);
     private basic.SingleTonEnum();
     public void func();
     static {};
 }
 *
 * ö����ʵ�ֵ��������ƣ�
 *  1. �����
 *
 *  2. �̰߳�ȫ
 *      ��ϵ��"������ᱣ֤һ�����<clinit>()?�����ڶ��̻߳����б���ȷ�ļ�����ͬ��"
 *
 *  3. �Դ����л�
 *      ��ϵ��Enum��������
 *      public abstract class Enum<E extends Enum<E>>
 *          implements Comparable<E>, Serializable {
 *      }
 *
 */
public enum SingleTonEnum {
    INSTANCE,
    INSTANCEEX;

    SingleTonEnum() {
        System.out.println(this.name() + " SingleTonEnum.SingleTonEnum ����");
    }

    public static SingleTonEnum getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception{
        SingleTonEnum one = SingleTonEnum.INSTANCE;
        SingleTonEnum two = SingleTonEnum.INSTANCE;
        System.out.println("is equal:");
        System.out.println(one == two);

        for (SingleTonEnum tmp :SingleTonEnum.values())
        {
//            System.out.println("getDeclaringClass:" + tmp.getDeclaringClass());
            System.out.println(tmp.name());
            System.out.println(tmp.ordinal());
        }

        // ��֤ enum �����������л�����
        File file = new File("enumObject");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(one);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        SingleTonEnum recvObj = (SingleTonEnum)ois.readObject();
        System.out.println("recvObj = " + recvObj);
        System.out.println("�Ƿ���ͬ��" + one.equals(recvObj));

    }
}
