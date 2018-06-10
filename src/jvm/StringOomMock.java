package jvm;

import utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Ԫ�ռ���ԣ�JDK8֮ǰ�����ô�����
 *
 * userdeMacBook-Pro:JavaProject user$ java -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=1m -classpath ./:CLASSPATH jvm.StringOomMock
 Error occurred during initialization of VM
 OutOfMemoryError: Metaspace
 *
 * index:24 len:255
 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 at java.util.Arrays.copyOfRange(Arrays.java:3664)

 ������"PermGen Space"��˵��������֤ JDK 1.7 �� 1.8
 ���ַ������������ô�ת�Ƶ�"��"�У����� JDK 1.8 ���Ѿ����������ô��Ľ���
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
