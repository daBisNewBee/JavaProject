package jvm;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * ��̬����.
 *
 * �����ؼ��֣�
 * 1. ����֧����
 *      InvocationHandler �����ýӿڷ���ʱ���ص��������"invoke"����
 *      Proxy.
 *
 * 2. ��̬�������
 *      ��ʲô��
 *          �Ƕ�̬���ɵ�class������Ϊ"RealSubjectProxy.class"
 *      ������ɣ�
 *          ProxyGenerator.generateProxyClass
 *
 * 3. ȱ��һ����̬����Ϊ��ֻ�ܴ���ӿڣ�
 *    �䶯̬��������Ѿ��̳���Proxy�࣬��Java�������̳�
 *    ȱ�����ִ���ٶȽ���
 *
 * 4. ��Ⱦ�̬����ĺô���
 *      ���ĺô��ǽӿ������������з�������ת�Ƶ����ô�����һ�����еķ����д���InvocationHandler.invoke
 *
 * 5. ���������Ŀ�ģ�
 *      ��ǿԭ�����ʵ�֣�
 *
 * 6. ʹ�ó�������Ƶ���������ش������������JDK��̬�����������ϸ�ռ�ţ�
 *      Spring��AOP���ƾ��ǲ��ö�̬����Ļ�����ʵ�������̡�
 *
 * ��أ�cglib����������ִ�п죡���ò���ҪƵ�������������ĳ��ϣ�
 *      �ŵ㣺
            1. �����Ƕ�̬�����ֽ���ʵ�ִ�����˴�������ִ���ٶȽϿ�, ԼΪJDK��̬�����1.5 ~ 2��
            2. ���Դ���û��ʵ�ֽӿڵĶ���
        ȱ�㣺
            1. ���ܴ���final��
            2. ��̬�����ֽ�����Ȼִ�нϿ죬���������ٶȺ�������������һЩ�˵Ĳ��Խ����cglib�������������ٶ�Ҫ��JDK��10 ~ 15����
        ���ó�����
            1. ����ҪƵ��������������Ӧ�ã���Spring��Ĭ�ϵĵ���bean��ֻ��Ҫ����������ʱ����һ�δ������
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
        * ��������������ʵ����ķ���ʱ������Զ�����ת��������������handler�����invoke���������е���
        *
        * Ϊ�λ�ص��˷�����
        *
        * �ο����ɵĶ�̬��������֪���ˣ�
        * ���磺"RealSubjectProxy.class"
        *
        * ������ɶ�̬�������
        * ����ʵ�֣�
        * ProxyGenerator.generateProxyClass
        *
        * */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("method = " + method);
            Object tmp = method.invoke(this.obj, args);
            // ��Ŀ�����ķ���ִ��֮ǰ��ִ��֮���������ǿ
            return tmp;
        }
    }

    public static void main(String[] args) throws Exception {

        // ���涯̬�������class�ļ���/jvm/$Proxy0.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //    ����Ҫ�������ʵ����
        RealSubject realSubject = new RealSubject();

        InvocationHandler handler = new DynamicProxy_(realSubject);

        System.out.println("realSubject = " + realSubject.getClass().getName());

        /*
        * ͨ��Proxy��newProxyInstance�������������ǵĴ��������������������������
        *
        * loader:���������ĸ�ClassLoader�����������ɵĴ��������м���
        *
        * interfaces:����һ��Interface��������飬��ʾ�����ҽ�Ҫ������Ҫ����Ķ����ṩ
        * һ��ʲô�ӿڣ�������ṩ��һ��ӿڸ�������ô���������������ʵ���˸ýӿ�(��̬)��
        * �����Ҿ��ܵ�������ӿ��еķ�����
        *
        * h:����һ��InvocationHandler���󣬱�ʾ���ǵ��������̬��������ڵ��÷�����ʱ��
        * ���������һ��InvocationHandler������
        *
        * */
        Subject sub = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);

        // ��jvm����ʱ��̬���ɵ�һ������,����ο�"Class<?> cl = getProxyClass0(loader, intfs);"
        Basic basic  = (Basic)Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);

        System.out.println("is Same:" + (sub.hashCode() == realSubject.hashCode()));
        // Ϊ���� true�� sub�ص���realSubject�ķ���
        System.out.println("object is same = " + (sub == realSubject));
        // false���������

        System.out.println("sub = " + sub.getClass().getName());
        System.out.println("basic = " + basic.getClass().getName());
        /*
        *
        * Ϊ���� $Proxy0 ��
        * ����Subject��Ҳ����InvocationHandler
        *
        * �ڶ������� "interfaces"������ ���������Ҫ���ɵĽӿڣ�
        * ��ˣ����صĴ���������ת��Ϊ�κ�һ�������interfaces�ӿ�
        * ���� Subject��Basic
        *
        * */

        String msg = sub.say(100, "alice");
        System.out.println("msg = " + msg);
        basic.loud();

        /*
        *
        * ������� RealSubjectProxy.class ��RealSubjectProxy
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
