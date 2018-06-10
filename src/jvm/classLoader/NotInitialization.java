package jvm.classLoader;

import utils.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 *
 * 结果：
 *
 *   SSClass init
     SuperClass init
     123
 *
 *  注意：没有"SubClass init"
 *
 */
public class NotInitialization {

    public static class SSClass{
        static {
            Log.v("SSClass init");
        }

        public SSClass(){
            Log.v("SSClass");
        }
    }

    public static class SuperClass extends SSClass{
        static {
            Log.v("SuperClass init");
        }
        public static int value = 123;

        public SuperClass(){
            Log.v("SuperClass");
        }

        public static void myFun(){}
    }

    public static class SubClass extends SuperClass{
        static {
            Log.v("SubClass init");
        }

        static int a ;

        public SubClass(){
            Log.v("SubClass");
        }

        protected SubClass(String hello){
            
        }

        private SubClass(String world,String hello){
            
        }

        public static void fun(){}
    }

    public static void main(String[] args){

        // 类什么时候才被初始化：

        // 1. 创建类的实例，也就是new一个对象
//        SubClass subClass = new SubClass();
        /*
        *   SSClass init
            SuperClass init
            SubClass init
            SSClass
            SuperClass
            SubClass
        * */

        // 2. 访问某个类或接口的静态变量，或者对该静态变量赋值
//        Log.v(""+SubClass.value);
        /*
        *   SSClass init
            SuperClass init
            123
        *
        * */

        //        SubClass.a = 1;
        /*
        *   SSClass init
            SuperClass init
            SubClass init
        * */

        // 3. 调用类的静态方法
//        SubClass.myFun();
//        SubClass.fun();
        /*
        *   SSClass init
            SuperClass init
            SubClass init
        * */

        try {
            // 4. 反射（ClassTest.forName("com.lyj.load")）
            Class<?> threadclazz1 = Class.forName(SubClass.class.getName());
            threadclazz1.getConstructors();// 仅返回 public 的构造函数
            threadclazz1.getDeclaredConstructors(); // 返回所有声明的构造函数，包括public protected   private


            Class<?> threadclazz = Class.forName("jvm.classLoader.NotInitialization$SubClass");
            Method method = threadclazz.getMethod("fun",null);
            method.invoke(null,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /*
        *   SSClass init
            SuperClass init
            SubClass init
        *
        * */

        // 5. 初始化一个类的子类（会首先初始化子类的父类）

        // 6. JVM启动时标明的启动类

    }

}
