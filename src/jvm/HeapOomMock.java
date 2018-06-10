package jvm;

import utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * ¶ÑÒç³ö²âÊÔ¡£
 *
 * userdeMacBook-Pro:JavaProject user$ java -Xms16m -Xmn8m -Xmx16m -classpath ./:CLASSPATH jvm.HeapOomMock
 index:13
 Throwble:
 null
 java.lang.OutOfMemoryError: Java heap space

 userdeMacBook-Pro:JavaProject user$ java -Xms160m -Xmn8m -Xmx160m -classpath ./:CLASSPATH jvm.HeapOomMock
 index:158
 Throwble:
 null
 java.lang.OutOfMemoryError: Java heap space
 *
 */
public class HeapOomMock {

    public static void main(String[] args){

        List<byte[]> list = new ArrayList<>();

        boolean flag = true;
        int index = 1;

        while (flag){
            try {
                list.add(new byte[1024*1024]);
                index++;
                Thread.sleep(500);
            }catch (Throwable t){
                /*
                *
                *   index:1659
                    java.lang.OutOfMemoryError: Java heap space
                    Throwble:
                        at jvm.HeapOomMock.main(HeapOomMock.java:19)
                    null
                * */
                Log.v("index:"+index+"\nThrowble:\n"+t.getCause());
                t.printStackTrace();
                flag = false;
            }

        }

    }
}
