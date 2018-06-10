package shotEveryDay;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 *
 *  一文让你明白Java字节码
 *
 * http://www.importnew.com/24088.html
 */
public class Char {

    /**
     *
     * 暂时无法从javap中 获得基本数据类型占用长度。
     *
     * 字节码中相关的信息只有：
     *  作用域、变量名、变量类型（签名）等。
     *
     LocalVariableTable:
     Start  Length  Slot  Name   Signature
     2      20     0  inta   I
     4      18     1 boolb   Z
     6      16     2 shortc   S
     8      14     3 longd   J
     11      11     5 bytee   B
     15       7     6 charf   C
     18       4     7 floatg   F
     21       1     8 doubleh   D
     *
     */
    static void basic(){

        int inta = 0;
        boolean boolb = false;
        short shortc = 0;
        long longd = 1L;
        byte bytee = 1;
        char charf = 'a';
        float floatg = 1.0F;
        double doubleh = 1.0D;

    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        basic();

        String str = "我";

        byte[] bytesGBK = str.getBytes("GBK");
        byte[] bytesUTF = str.getBytes("UTF-8");
        byte[] bytesUNICODE = str.getBytes("unicode");

        /*
        Map<String,Charset> map = Charset.availableCharsets();
        System.out.println("map = " + map);
        for (Map.Entry<String,Charset> entry :
                map.entrySet()) {
            Charset charset = entry.getValue();
            byte[] array = str.getBytes(charset);
            System.out.println("name = " + entry.getKey()+"    len:"+array.length);
        }
        */
        String csn = Charset.defaultCharset().name();
        System.out.println("csn = " + csn);
        System.out.println("bytesGBK = " + bytesGBK.length); // 2
        System.out.println("bytesUTF = " + bytesUTF.length); // 3
        System.out.println("bytesUNICODE = " + bytesUNICODE.length); // 4

        // 合法，一个中文字符占两个字节
        char a = '我';

        System.out.println("a = " + a);

        char n = 'b';

        /*
        *
        * byte      1
        *
        * boolean   1 (不一定。实际在JVM中不存在该类型定义)
        *
        * char      2 ( C 语言中是1个字节 )
        *
        * short     2
        *
        * int       4
        *
        * long      8
        *
        * float     4
        *
        * double    8
        *
        * 引用：
        * reference 4
        *
        * */

        System.out.println("byte:"+ Byte.SIZE/8 );
        System.out.println("char:"+Character.SIZE/8 );
        System.out.println("short:"+Short.SIZE/8 );
        System.out.println("int:"+Integer.SIZE/8 );
        System.out.println("long:"+Long.SIZE/8 );
        System.out.println("float:"+Float.SIZE/8 );
        System.out.println("double:"+Double.SIZE/8 );
        System.out.println("reference:"+new Object());

        System.out.println(++n);

        /*
        *
        * 小转大：自动，隐式转换！
        *
        * char -> short -> int -> long -> float -> double
        *
        *   整型，字符型，浮点型的数据在混合运算中相互转换，转换时遵循以下原则：

            容量小的类型可自动转换为容量大的数据类型；

            byte,short,char → int → long → float → double

            byte，short，char之间不会相互转换，他们在计算时首先会转换为int类型。

            boolean 类型是不可以转换为其他基本数据类型。
        *
        * */
        System.out.println(n+1);

        /*
        *
        * 大转小：强制转换！
        * */

        long l = 123L;

        int i = (int) l;//必须强转

        double d = 3.14;

        float f = (float) d;

    }
}
