package jvm;

import utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 元空间测试（JDK8之前是永久代）。
 *
 * userdeMacBook-Pro:JavaProject user$ java -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=1m -classpath ./:CLASSPATH jvm.StringOomMock
 Error occurred during initialization of VM
 OutOfMemoryError: Metaspace
 *
 * index:24 len:255
 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 at java.util.Arrays.copyOfRange(Arrays.java:3664)

 不是在"PermGen Space"，说明大致验证 JDK 1.7 和 1.8
 将字符串常量由永久代转移到"堆"中，并且 JDK 1.8 中已经不存在永久代的结论
 *
 *
 *
 */
public class StringOomMock {

    static String base = "base";


    public static void main(String[] args) throws InterruptedException {
        int len = 0;

        List<String> list = new ArrayList<>();
        for (int i = 0 ; i<Integer.MAX_VALUE ; i++){
            String str = base + base;
            base = str;
            len += str.length();
            list.add(str.intern());
            Thread.sleep(5000);
            Log.v("index:"+i +" len:"+len/(1024*1024));
        }
    }
}
