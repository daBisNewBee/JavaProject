package jvm.classLoader;

import utils.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 *
 * �����
 *
 *   SSClass init
     SuperClass init
     123
 *
 *  ע�⣺û��"SubClass init"
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

        // ��ʲôʱ��ű���ʼ����

        // 1. �������ʵ����Ҳ����newһ������
//        SubClass subClass = new SubClass();
        /*
        *   SSClass init
            SuperClass init
            SubClass init
            SSClass
            SuperClass
            SubClass
        * */

        // 2. ����ĳ�����ӿڵľ�̬���������߶Ըþ�̬������ֵ
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

        // 3. ������ľ�̬����
//        SubClass.myFun();
//        SubClass.fun();
        /*
        *   SSClass init
            SuperClass init
            SubClass init
        * */

        try {
            // 4. ���䣨ClassTest.forName("com.lyj.load")��
            Class<?> threadclazz1 = Class.forName(SubClass.class.getName());
            threadclazz1.getConstructors();// ������ public �Ĺ��캯��
            threadclazz1.getDeclaredConstructors(); // �������������Ĺ��캯��������public protected   private


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

        // 5. ��ʼ��һ��������ࣨ�����ȳ�ʼ������ĸ��ࣩ

        // 6. JVM����ʱ������������

    }

}
