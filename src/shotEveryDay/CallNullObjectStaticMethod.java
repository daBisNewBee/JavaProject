package shotEveryDay;

import java.util.HashMap;

/**
 *
 * null 对象访问 static 属性或方法
 *
 * 学会使用"javap -v shotEveryDay/CallNullObjectStaticMethod.class"查看反编译信息。
 *
 * 相关：
 *  Java字节码指令集
 *  局部变量表、操作数栈
 *
 *  https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484598&idx=1&sn=9cf92ebc227c3cfaf6f1a12ed9f67697&chksm=eb509f54dc271642a4332f36e8f781052ed8c7c9fb48e60f7aed67dd051e8c67f65ce4fac8ad&scene=38#wechat_redirect
 *
 *
 */
public class CallNullObjectStaticMethod {

    public static int NUM = 100;

    public static void funcDo() {
        System.out.println("Num = "+ NUM);
    }

    public static void main(String[] args) {
        CallNullObjectStaticMethod test = null;
        // 7: getstatic     #7                  // Field NUM:I
        System.out.println(test.NUM);
        // 15: invokestatic  #12                 // Method funcDo:()V
        test.funcDo();

        /*
        *
        * 100
          Num = 100
        * */

        new Thread();

        /*
        *
        * 匿名内部类，实际为Thread的子类。
        *
        * 巧用：为了方便调用父类的"protected"方法。
        *
        * 常用于不想继承子类，又想调用父类的"protected"方法。
        *
        * */
        Thread t = new Thread(){
            // call protected method
        };
        System.out.println("t.getClass() = " + t.getClass());
        // t.getClass() = class shotEveryDay.CallNullObjectStaticMethod$1
    }

}
