package jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * 控制反转： Inversion of Control
 *
 * IOC介绍及其简单实现：
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
         * 普通实现。
         *
         * 缺点：
         *      如果子类太多，需要频繁修改工厂类
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
         * 利用反射的工厂实现
         *
         * 需要：传入完整的包和类名
         *
         * 缺点：
         *      用户也无法知道一个接口有多少个可以使用的子类
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
        // 1. 不带反射机制的工厂模式
        fruit = Factory.getInstance("Orange");
        fruit.eat();

        System.out.println("fruit = " + Apple.class.getName());
        // 2. 利用反射机制的工厂模式
        fruit = Factory.getInstanceByReflect("jvm.IoCTest$Apple");
        fruit.eat();

        // 3. 通过配置文件获取"全类名"，解决"子类数量未知"的问题
        fruit = Factory.getInstanceByReflect(getPro().getProperty("apple"));
        fruit.eat();

    }
}
