package utils;

import java.io.File;
import java.io.FileOutputStream;

public class Log {

    public static void writeByteArray(byte[] input,String outFilePath) throws Exception {
        File pkcs7 = new File(outFilePath);
//        if (!pkcs7.exists())
//            pkcs7.createNewFile();
        FileOutputStream fos = new FileOutputStream(pkcs7);
        fos.write(input);
        fos.close();
    }

    public static void v(String[] array){
        for (String one :
                array) {
            System.out.println(one);
        }
    }

    public static void v(String msg){
        System.out.println(msg);
    }

    public static void v(Object obj){
        System.out.println(obj);
    }

    public static void v(Integer msg){
        System.out.println(msg);
    }

    public static void v(String msg,Throwable e){
        System.out.println(msg+"\nThrowable:\n"+e.getCause());
    }

    public static void v(Throwable e){
        System.out.println(e.getCause());
    }

}
