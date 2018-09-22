package jvm.classLoader;

import utils.Log;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

public class DiskClassLoader extends ClassLoader{

    private String rootPath;

    public DiskClassLoader(String path) {
        rootPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class cl = findLoadedClass(name);

        if (cl!=null)
            return cl;

        String clzzPath = rootPath + "/" + name.replace(".","/")
                + ".class";

        File file = new File(clzzPath);

        try {
            FileInputStream fis = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int b = 0;

            while ((b = fis.read())!=-1)
                bos.write(b);

            byte[] data = bos.toByteArray();

            return defineClass(name,data,0,data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {

        DiskClassLoader diskClassLoader
                = new DiskClassLoader("/Users/user/Documents/git/JavaProject/out/production/JavaProject");

        String className = "jvm.classLoader.MyBean";

        Class c = diskClassLoader.findClass(className);

        Log.v("pid:"+ ManagementFactory.getRuntimeMXBean().getName());
        Log.v("get Loader:"+c.getClassLoader());
        Log.v("default loader:"+MyBean.class.getClassLoader());

        if (c!=null){
            Object object = c.newInstance();
            /*
            *
            * 无法cast：默认的类加载器不同
            *
            * Exception in thread "main" java.lang.ClassCastException:
            * jvm.classLoader.MyBean cannot be cast to jvm.classLoader.MyBean
            * */
//            MyBean myBean = (MyBean)c.newInstance();
            Method method = c.getDeclaredMethod("say");
            method.invoke(object);
        }


    }
}
