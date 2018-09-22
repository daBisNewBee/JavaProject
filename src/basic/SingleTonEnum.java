package basic;

import java.io.*;

/**
 *
 * 从枚举类反编译后的源码来了解为什么 枚举类可以用做实现单例？
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
 * 枚举类实现单例的优势：
 *  1. 编码简单
 *
 *  2. 线程安全
 *      联系："虚拟机会保证一个类的<clinit>()?方法在多线程环境中被正确的加锁、同步"
 *
 *  3. 自带序列化
 *      联系：Enum的声明：
 *      public abstract class Enum<E extends Enum<E>>
 *          implements Comparable<E>, Serializable {
 *      }
 *
 */
public enum SingleTonEnum {
    INSTANCE,
    INSTANCEEX;

    SingleTonEnum() {
        System.out.println(this.name() + " SingleTonEnum.SingleTonEnum 构造");
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

        // 验证 enum 天生具有序列化能力
        File file = new File("enumObject");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(one);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        SingleTonEnum recvObj = (SingleTonEnum)ois.readObject();
        System.out.println("recvObj = " + recvObj);
        System.out.println("是否相同：" + one.equals(recvObj));

    }
}
