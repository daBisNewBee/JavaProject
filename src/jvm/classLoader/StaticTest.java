package jvm.classLoader;

/**
 *
 * http://blog.csdn.net/u013256816/article/details/50837863
 *
 * 类加载机制测试：“实例初始化竟然出现在静态初始化之前”。
 * 结果：
 *       2
         3
         a=110,b=0     // b是0 ？？如果b是final，会怎么办？
         1
         4
 *
 * 原因：
 * 1. 执行静态方法触发类初始化，执行类构造器<clinit>()
 * 2. clinit的执行顺序：静态语句块和类变量的赋值语句在源码中的顺序。
 * 此处为：st = new StaticTest() ->  System.out.println("1"); -> static int b =112;
 * 3. 在第一条静态变量的赋值语句中嵌套对象初始化操作。因为看到了"对象初始化在类初始化之前"的假象。
 * 4. 对象初始化的操作顺序：先初始化成员变量再执行构造方法
 *
 */
public class StaticTest {

    public static void main(String[] args)
    {
        staticFunction();
    }

    // 在
    static StaticTest st = new StaticTest();

    static
    {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a=110;
    static int b =112;
//    final static int b =112;    //注意与上述有final的区别！
}
