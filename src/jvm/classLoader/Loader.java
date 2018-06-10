package jvm.classLoader;

import javafx.print.Printer;
import utils.Log;

import javax.tools.ForwardingFileObject;
import java.security.cert.X509Certificate;

/**
 *
 * 参考：Launcher.class
 *
 */
public class Loader {

    public static void main(String[] args) {

        ClassLoader loader = Loader.class.getClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent()); //

        Log.v(Printer.class.getClassLoader());

        // (最低级)AppClassLoader
        final String var1 = System.getProperty("java.class.path");
        String[] varArray = var1.split(":");
        Log.v("========= java.class.path:");
        Log.v(varArray);
        Log.v("=======================");

        // (中间级)ExtClassLoader
        final String var2 = System.getProperty("java.ext.dirs");
        String[] var2Array = var2.split(":");
        Log.v("========= java.ext.dirs:");
        Log.v(var2Array);
        Log.v("=======================");

        // (最高级)bootClassPath
        final String var3 = System.getProperty("sun.boot.class.path");
        Log.v("========= sun.boot.class.path:");
        String[] var3Array = var3.split(":");
        Log.v(var3Array);
    }
}
