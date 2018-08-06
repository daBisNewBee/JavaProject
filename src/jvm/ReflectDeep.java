package jvm;

import sun.reflect.MethodAccessor;
//import sun.reflect.MethodAccessorGenerator;
//import sun.reflect.MethodAccessorImpl;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class Bean {
    void run(){
        System.out.println("Proxy.run");
    }
}

public class ReflectDeep {

    static void InvokeCall(int i) throws Exception {
        long start = System.nanoTime();
//        long start = System.currentTimeMillis();
        Class<?> beanClz = Class.forName("jvm.Bean");
        Bean bean = (Bean)beanClz.newInstance();
        Method method = beanClz.getDeclaredMethod("run");
        method.invoke(bean);
        System.out.println("cost:"+(System.nanoTime()-start)+" i:"+i);
    }

    /**
     *
     * 耗时约为 InvokeCall 的三分之一
     * @param i
     */
    static void NormalCall(int i){
        long start = System.nanoTime();
        Bean bean = new Bean();
        bean.run();
        System.out.println("Normal cost:"+(System.nanoTime()-start)+" i:"+i);
    }

    /**
     *
     *  -Dsun.reflect.inflationThreshold=10
     *  -Dsun.reflect.noInflation=true
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String inflationThreshold = System.getProperty("sun.reflect.inflationThreshold");
        System.out.println("inflationThreshold = " + inflationThreshold);

        String noInflation = System.getProperty("sun.reflect.noInflation");
        System.out.println("noInflation = " + noInflation);

        for (int i = 1; i < 20; i++) {
            /*
            * 1: 1180661
            * 2: 60000
            * ...
            * 16:3628638
            * */
            InvokeCall(i);
            NormalCall(i);
        }

        Method method = Bean.class.getDeclaredMethod("run");

        /*
        * 反射调用 MethodAccessorGenerator 的 generateMethod 方法，
        * 实际想模拟的是： NativeMethodAccessorImpl 中的 invoke，即：
        *   MethodAccessorImpl var3 = (MethodAccessorImpl)(new MethodAccessorGenerator())
        *   .generateMethod(this.method.getDeclaringClass(), this.method.getName(),
        *   this.method.getParameterTypes(), this.method.getReturnType(),
        *   this.method.getExceptionTypes(), this.method.getModifiers());
        *
        * TODO:无法验证生成出的MethodAccessorImpl字节码为：

        public class GeneratedMethodAccessor1 extends MethodAccessorImpl {
        public Object invoke(Object obj, Object[] args)  throws Exception {
        try {
            MyClass target = (MyClass) obj;
            String arg0 = (String) args[0];
            target.myMethod(arg0);
        } catch (Throwable t) {
            throw new InvocationTargetException(t);
        }
    }
}
        * */
        Class<?> magClz = Class.forName("sun.reflect.MethodAccessorGenerator");
        Method generateMethod = magClz.getDeclaredMethod("generateMethod"
                , Class.class, java.lang.String.class, Class[].class
                , Class.class, Class[].class, int.class);
        Constructor constructor = magClz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        if (!generateMethod.isAccessible())
            generateMethod.setAccessible(true);
        Object mag = constructor.newInstance();
        MethodAccessor methodAccessor = (MethodAccessor)generateMethod.invoke(
                mag, method.getDeclaringClass(), method.getName(), method.getParameterTypes()
                , method.getReturnType(), method.getExceptionTypes(), method.getModifiers());
        System.out.println("methodAccessor = " + methodAccessor);
//        Thread.currentThread().getContextClassLoader().loadClass(methodAccessor.getClass().getName());

        if (true)
            return;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(methodAccessor);

        File file = new File(methodAccessor.getClass().getName()+".class");
        if (file.exists())
            file.delete();
        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(loadBytes(methodAccessor.getClass()));
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
    }

    private static byte[] loadBytes(Class<?> cls) throws IOException
    {
        if (cls == null) return null;
        String name = cls.getCanonicalName().replaceAll("/.", "/") + ".class";
        InputStream is = ClassLoader.getSystemResourceAsStream(name);
        BufferedInputStream bis = new BufferedInputStream(is);
        int length = is.available();
        byte[] bs = new byte[length];
        System.err.println("ddd:" + bs.length);
        bis.read(bs);
        is.close();
        return bs;
    }
}
