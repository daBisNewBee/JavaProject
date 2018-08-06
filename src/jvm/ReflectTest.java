package jvm;

import java.lang.reflect.*;

class Proxy{
    void run(){
        System.out.println("Proxy.run");
    }
}

interface China{
    void sayChina();
    void sayHello(int age, String name);
}

class Person implements China{
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {

        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void sayChina() {
        System.out.println("Person.sayChina");
    }

    @Override
    public void sayHello(int age, String name) {
        System.out.println("Person.sayHello:"+age +" "+name);
    }
}

/**
 *
 * Java反射机制及IoC原理:
 *
 * https://www.cnblogs.com/Eason-S/p/5851078.html
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception{
        Proxy proxy = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(proxy);


        //1.通过一个对象获得完整的包名和类名
        System.out.println(proxy.getClass().getName());

        //2.实例化Class类对象，位于堆中
        Class<?> demo1 = Class.forName("jvm.Proxy");
        Class<?> demo2 = new Proxy().getClass();
        Class<?> demo3 = Proxy.class;

        System.out.println("demo1 = " + demo1.getName());
        System.out.println("demo2 = " + demo2.getSimpleName());
        System.out.println("demo3 = " + demo3.getName());

        // 3.通过Class实例化其他类的对象
        Class<?> personClz = Class.forName("jvm.Person");
        Person person = (Person)personClz.newInstance();
        person.setAge(100);
        person.setName("lwb");
        System.out.println(person.toString());

        // 4. 通过class创建"带参"的实例化对象
        Constructor<?> cons[] = personClz.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            // 7.获得其他类中的全部构造函数
            Class<?> p[]=cons[i].getParameterTypes();
            System.out.print("构造方法：  ");
            int mo=cons[i].getModifiers();
            System.out.print(Modifier.toString(mo)+" ");
            System.out.print(cons[i].getName());
            System.out.print("(");
            for(int j=0;j<p.length;++j){
                System.out.print(p[j].getName()+" arg"+i);
                if(j<p.length-1){
                    System.out.print(",");
                }
            }
            System.out.println("){}");

        }

        Person person1  = (Person)cons[0].newInstance(90);
        Person person2  = (Person)cons[1].newInstance("rolen");
        Person person3  = (Person)cons[2].newInstance();
        Person person4  = (Person)cons[3].newInstance(80,"bolb");

        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);
        System.out.println("person3 = " + person3);
        System.out.println("person4 = " + person4);

        // 5.返回一个类实现的接口
        Class<?> inters[] = personClz.getInterfaces();
        for (Class<?> inter : inters) {
            System.out.println("inter = " + inter.getName());
        }
        // jvm.China

        // 6.取得其他类中的父类
        Class<?> parentClz = personClz.getSuperclass();
        System.out.println("parentClz = " + parentClz.getName());
        // parentClz = java.lang.Object

        // 8. TODO 类的全部属性，将这些整理在一起，也就是通过class取得一个类的全部框架
        Field[] fields = parentClz.getFields();
        for (Field field : fields) {
            System.out.println(field.toString());
        }

        Method method1 = personClz.getMethod("sayChina");
        method1.invoke(personClz.newInstance());
        Method method2 = personClz.getMethod("sayHello",int.class, String.class);
        // 不是Integer.class ！会找不到方法
        method2.invoke(personClz.newInstance(),20, "CNM");

        Method method3 = person3.getClass().getMethod("sayChina");
        method3.invoke(person3);

        // 11.通过反射操作属性
        Field field = personClz.getDeclaredField("age");
        field.setAccessible(true);
        field.set(person3,90);
        System.out.println("person3.getAge() = " + field.get(person3));

        // 12.通过反射取得并修改数组的信息
        int[] tmp = new int[]{1,2,3,4,5};
        Class<?> tmpClz = tmp.getClass().getComponentType();
        System.out.println("数组类型：" + tmpClz.getName());
        System.out.println("数组长度：" + Array.getLength(tmp));
        System.out.println("数组的第一个元素:" +Array.get(tmp, 0));
        Array.set(tmp, 0, 100);
        System.out.println("修改之后数组第一个元素为:" +Array.get(tmp, 0));


        Class<?> c0 = int.class;
        Class<?> c1 = Integer.class;
        System.out.println("c0 = " + c0);
        System.out.println("c1 = " + c1);
        System.out.println(""+(c0 == Integer.TYPE));
        /*
        * c0 = int
          c1 = class java.lang.Integer
          true
        * */

    }


}
