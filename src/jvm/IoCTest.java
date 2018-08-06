package jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * ���Ʒ�ת�� Inversion of Control
 *
 * IOC���ܼ����ʵ�֣�
 * https://www.cnblogs.com/ningvsban/p/3757890.html
 *
 */
public class IoCTest {

    interface fruit{
        public abstract void eat();
    }

    static class Apple implements fruit{

        @Override
        public void eat() {
            System.out.println("Apple.eat");
        }
    }

    static class Orange implements fruit{

        @Override
        public void eat() {
            System.out.println("Orange.eat");
        }
    }

    static class Factory{
        /**
         *
         * ��ͨʵ�֡�
         *
         * ȱ�㣺
         *      �������̫�࣬��ҪƵ���޸Ĺ�����
         *
         * @param name
         * @return
         */
        public static fruit getInstance(String name){
            fruit f = null;
            if (name.equals("Apple"))
                f = new Apple();
            if (name.equals("Orange"))
                f = new Orange();
            return f;
        }

        /**
         *
         * ���÷���Ĺ���ʵ��
         *
         * ��Ҫ�����������İ�������
         *
         * ȱ�㣺
         *      �û�Ҳ�޷�֪��һ���ӿ��ж��ٸ�����ʹ�õ�����
         *
         * @param className
         * @return
         * @throws Exception
         */
        public static fruit getInstanceByReflect(String className) throws Exception {
            return (fruit)Class.forName(className).newInstance();
        }
    }

    static Properties getPro() throws Exception {
        Properties properties = new Properties();
        File f = new File("fruit.properties");
        if (f.exists()){
            properties.load(new FileInputStream(f));
        }else {
            properties.setProperty("apple","jvm.IoCTest$Apple");
            properties.setProperty("orange","jvm.IoCTest$Orange");
            properties.store(new FileOutputStream(f), "this is comment");
        }
        return properties;
    }

    public static void main(String[] args) throws Exception {
        fruit fruit = null;
        // 1. ����������ƵĹ���ģʽ
        fruit = Factory.getInstance("Orange");
        fruit.eat();

        System.out.println("fruit = " + Apple.class.getName());
        // 2. ���÷�����ƵĹ���ģʽ
        fruit = Factory.getInstanceByReflect("jvm.IoCTest$Apple");
        fruit.eat();

        // 3. ͨ�������ļ���ȡ"ȫ����"�����"��������δ֪"������
        fruit = Factory.getInstanceByReflect(getPro().getProperty("apple"));
        fruit.eat();

    }
}
