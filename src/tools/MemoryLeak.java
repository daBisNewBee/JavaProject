package tools;

import jvm.classLoader.MyBean;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * java -classpath ./ -Xms100m -Xmx100m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintClassHistogram -XX:+HeapDumpOnOutOfMemoryError tools.MemoryLeak
 *
 * https://www.ezlippi.com/blog/2017/12/java-memory-leak-example.html
 * <p>
 * Java内存泄露的例子:
 * ThreadLocal和ClassLoader导致内存泄露最终OutOfMemory的场景。
 */
public class MemoryLeak {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                CustomClassLoader classLoader = new CustomClassLoader
                        ("load1"
                                , MemoryLeak.class.getClassLoader()
                                , "tools.MemoryLeak$Inner"
                                , "tools.MemoryLeak$Inner$1");
                try {
                    Class<?> innerClass = classLoader.loadClass("tools.MemoryLeak$Inner");
                    innerClass.newInstance();
                    innerClass = null;
                    classLoader = null;
                    Thread.sleep(10);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }


    public static class Inner {

        private static byte[] MB = new byte[1024 * 1024];
//        private static byte[] MB = new byte[1024 * 1024 * 1024];

        // MemoryLeak$Inner$1.class 匿名类
        static ThreadLocal<Inner> threadLocal = new ThreadLocal<Inner>() {
            @Override
            protected Inner initialValue() {
                return new Inner();
            }
        };

        static {
            System.out.println("Inner static ==========");
            threadLocal.get();
        }

        public Inner() {
        }

    }

    private static class CustomClassLoader extends ClassLoader {
        private Set<String> urls;
        private String label;

        /**
         * 自定义类加载器,加载指定包名的类,其他的委托父类加载器加载
         *
         * @param name   类加载器名称
         * @param parent 父类加载器
         * @param url    要加载的包名
         */
        public CustomClassLoader(String name, ClassLoader parent, String... url) {
            super(parent);
            this.label = name;
            this.urls = new HashSet<String>(Arrays.asList(url));
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            InputStream inputStream = null;
            ByteArrayOutputStream outputStream = null;
            if (urls.contains(name)) {
                try {
                    String location = name.replace('.', '/') + ".class";
                    inputStream = MemoryLeak.class.getClassLoader().getResourceAsStream(location);
                    byte[] buf = new byte[2000];
                    outputStream = new ByteArrayOutputStream();
                    int length;
                    while ((length = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, length);
                    }
                    System.out.println(label + ": Loading " + name + " in " + label + " classloader");
                    byte[] data = outputStream.toByteArray();
                    return defineClass(name, data, 0, data.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name, e);
                } finally {
                    closeStream(inputStream);
                    closeStream(outputStream);
                }
            }

            throw new ClassNotFoundException(name);
        }

        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            //查看是否已经加载过
            if (findLoadedClass(name) != null) {
                System.out.println(label + ": already loaded(" + name + ")");
                return findLoadedClass(name);
            }

            if (urls.contains(name)) {
                return findClass(name);
            } else {
                System.out.println(label + ": super.loadclass(" + name + ")");
                //委托给父类加载器加载
                return super.loadClass(name, resolve);
            }
        }

        private void closeStream(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public String toString() {
            return label;
        }
    }

}