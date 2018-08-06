package jvm;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 动态代理.
 *
 * 几个关键字：
 * 1. 两大支柱：
 *      InvocationHandler 。调用接口方法时，回调该子类的"invoke"方法
 *      Proxy.
 *
 * 2. 动态代理对象。
 *      是什么？
 *          是动态生成的class，具体为"RealSubjectProxy.class"
 *      如何生成？
 *          ProxyGenerator.generateProxyClass
 *
 * 3. 缺点一：动态代理为何只能代理接口？
 *    其动态代理对象已经继承了Proxy类，而Java不允许多继承
 *    缺点二：执行速度较慢
 *
 * 4. 相比静态代理的好处：
 *      最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理（InvocationHandler.invoke
 *
 * 5. 代理的最终目的：
 *      增强原对象的实现！
 *
 * 6. 使用场景：（频繁、反复地创建代理对象，则JDK动态代理在性能上更占优）
 *      Spring的AOP机制就是采用动态代理的机制来实现切面编程。
 *
 * 相关：cglib（生成慢，执行快！适用不需要频繁创建代理对象的场合）
 *      优点：
            1. 由于是动态生成字节码实现代理，因此代理对象的执行速度较快, 约为JDK动态代理的1.5 ~ 2倍
            2. 可以代理没有实现接口的对象
        缺点：
            1. 不能代理final类
            2. 动态生成字节码虽然执行较快，但是生成速度很慢，根据网上一些人的测试结果，cglib创建代理对象的速度要比JDK慢10 ~ 15倍。
        适用场景：
            1. 不需要频繁创建代理对象的应用，如Spring中默认的单例bean，只需要在容器启动时生成一次代理对象。
 */
public class DynamicProxy {

    interface Subject{
        String say(int age, String name);
    }

    interface Basic{
        void loud();
    }

    static class RealSubject implements Subject,Basic{

        @Override
        public String say(int age, String name) {
            return name +" " +age;
        }

        @Override
        public void loud() {
            System.out.println("RealSubject.loud");
        }
    }

    static class DynamicProxy_ implements InvocationHandler{

        private Object obj = null;

        public DynamicProxy_(Object _obj) {
            this.obj = _obj;
        }

        /*
        * 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        *
        * 为何会回调此方法？
        *
        * 参考生成的动态代理对象就知道了！
        * 比如："RealSubjectProxy.class"
        *
        * 如何生成动态代理对象？
        * 核心实现：
        * ProxyGenerator.generateProxyClass
        *
        * */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("method = " + method);
            Object tmp = method.invoke(this.obj, args);
            // 在目标对象的方法执行之前和执行之后进行了增强
            return tmp;
        }
    }

    public static void main(String[] args) throws Exception {

        // 保存动态代理对象到class文件：/jvm/$Proxy0.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //    我们要代理的真实对象
        RealSubject realSubject = new RealSubject();

        InvocationHandler handler = new DynamicProxy_(realSubject);

        System.out.println("realSubject = " + realSubject.getClass().getName());

        /*
        * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
        *
        * loader:定义了由哪个ClassLoader对象来对生成的代理对象进行加载
        *
        * interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供
        * 一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，
        * 这样我就能调用这组接口中的方法了
        *
        * h:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，
        * 会关联到哪一个InvocationHandler对象上
        *
        * */
        Subject sub = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);

        // 在jvm运行时动态生成的一个对象,具体参考"Class<?> cl = getProxyClass0(loader, intfs);"
        Basic basic  = (Basic)Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);

        System.out.println("is Same:" + (sub.hashCode() == realSubject.hashCode()));
        // 为何是 true？ sub回调了realSubject的方法
        System.out.println("object is same = " + (sub == realSubject));
        // false，正常结果

        System.out.println("sub = " + sub.getClass().getName());
        System.out.println("basic = " + basic.getClass().getName());
        /*
        *
        * 为何是 $Proxy0 ？
        * 不是Subject，也不是InvocationHandler
        *
        * 第二个参数 "interfaces"告诉了 代理对象需要生成的接口，
        * 因此，返回的代理对象可以转换为任何一个传入的interfaces接口
        * 比如 Subject、Basic
        *
        * */

        String msg = sub.say(100, "alice");
        System.out.println("msg = " + msg);
        basic.loud();

        /*
        *
        * 如何生成 RealSubjectProxy.class ？RealSubjectProxy
        * */
        String proxyName = "RealSubjectProxy";
        Class<?>[] interfaces = realSubject.getClass().getInterfaces();
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces);

        System.out.println(Thread.currentThread().getContextClassLoader());
        File f = new File("RealSubjectProxy.class");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(proxyClassFile);
        fos.flush();
        fos.close();
    }
}
