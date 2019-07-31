package jvm.classLoader;

import utils.Log;

import java.lang.reflect.Constructor;

public class ClassTest {

    public static void main(String[] args) throws Exception {


            Class strClz = Class.forName(String.class.getName());
            String tmp = (String) strClz.newInstance();
            Log.v("newInstance:"+tmp.toString());

            Constructor c1 = strClz.getDeclaredConstructor();
            c1.setAccessible(true);
            String str1 = (String)c1.newInstance();
            Log.v("str1:"+str1.toString());

            Constructor c2 = strClz.getDeclaredConstructor(new Class[]{String.class});
            c2.setAccessible(true);
            String str2 = (String)c2.newInstance("hello");
            Log.v("str2:"+str2.toString());


            // 以String.class为例
            String str = new String();
            Class cl = str.getClass();
            /**
             * 获取包名+类名<br>
             * java.lang.String
             */
            cl.getName();
            /**
             * 只获取类名 - String
             */
            cl.getSimpleName();
            /**
             * 获取数组的Class对象<br>
             * 因为所有的Java类都继承自Object,数组也一样.所以数组本身也是个Class, 得到数组的Class自然也可以转回数组.
             */
            cl.getComponentType();
            /**
             * 返回构造器数组,包括超类的公有成员.
             */
            cl.getConstructors();
            /**
             * 返回方法数组,包括超类的公有成员.
             */
            cl.getMethods();
            /**
             * 返回域数组,包括超类的公有成员.
             */
            cl.getFields();
            /**
             * 返回全部构造器数组,无论是public/private还是protected,不包括超类的成员.
             */
            cl.getDeclaredConstructors();
            /**
             * 返回全部域数组,无论是public/private还是protected,不包括超类的成员.
             */
            cl.getDeclaredFields();
            /**
             * 返回全部方法数组,无论是public/private还是protected,不包括超类的成员.
             */
            cl.getDeclaredMethods();
            /**
             * 获取类前的修饰符
             */
            cl.getModifiers();
        }

}
