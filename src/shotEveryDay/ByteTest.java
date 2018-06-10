package shotEveryDay;

/**
 *
 * 为何会有补码？
 *
 *  "符号位"显然会让计算机的基础电路设计变得十分复杂!
 *
 *  于是人们开始探索 将"符号位参与运算", 并且"只保留加法"的方法.
 *
 *  补码形式的数字 参与实际运算。
 *
 *  计算中实际保存的都是数字的补码！
 *
 * 原码, 反码, 补码 详解：
 *  http://www.cnblogs.com/zhangziqiu/archive/2011/03/30/ComputerCode.html#!comments
 *
 */
public class ByteTest {

    public static void main(String[] args) {
//        byte n1 = 10 + 117;
//        byte n2 = 10 + 118;
//        System.out.println(n1);
//        System.out.println(n2);

         byte n1 = 10; //加final，以下编译正常，不会报错
//        byte n2 = n1 + 117;
//        System.out.println(n2);

        byte n3 = (byte) 128;
        System.out.println(n3);// -128.计算机中数据都是通过补码存储的

        byte n4 = (byte) (n1 + 117);
        System.out.println(n4);// 127

    }

}
