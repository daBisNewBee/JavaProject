package shotEveryDay;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 *
 *  һ����������Java�ֽ���
 *
 * http://www.importnew.com/24088.html
 */
public class Char {

    /**
     *
     * ��ʱ�޷���javap�� ��û�����������ռ�ó��ȡ�
     *
     * �ֽ�������ص���Ϣֻ�У�
     *  �����򡢱��������������ͣ�ǩ�����ȡ�
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

        String str = "��";

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

        // �Ϸ���һ�������ַ�ռ�����ֽ�
        char a = '��';

        System.out.println("a = " + a);

        char n = 'b';

        /*
        *
        * byte      1
        *
        * boolean   1 (��һ����ʵ����JVM�в����ڸ����Ͷ���)
        *
        * char      2 ( C ��������1���ֽ� )
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
        * ���ã�
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
        * Сת���Զ�����ʽת����
        *
        * char -> short -> int -> long -> float -> double
        *
        *   ���ͣ��ַ��ͣ������͵������ڻ���������໥ת����ת��ʱ��ѭ����ԭ��

            ����С�����Ϳ��Զ�ת��Ϊ��������������ͣ�

            byte,short,char �� int �� long �� float �� double

            byte��short��char֮�䲻���໥ת���������ڼ���ʱ���Ȼ�ת��Ϊint���͡�

            boolean �����ǲ�����ת��Ϊ���������������͡�
        *
        * */
        System.out.println(n+1);

        /*
        *
        * ��תС��ǿ��ת����
        * */

        long l = 123L;

        int i = (int) l;//����ǿת

        double d = 3.14;

        float f = (float) d;

    }
}
