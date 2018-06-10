package shotEveryDay;

import java.io.*;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484460&idx=1&sn=75b79ee49bfdaab55b0d6c4fd5fa7a78&chksm=eb509fcedc2716d836f2b5f5e5f3cd31a4925204bea1682d570eea8b03518cd04460fbf4aa12&scene=21#wechat_redirect
 *
 * ��"������"�ֽڷ�ʽ��д������
 *
 * InputStream��OutputStream:
 *
 * �����ã��� �����Ʒ�ʽ��Ч�ʸߣ�  ȱ�����޻��棡����
 *      FileInputStream��FileOutputStream: �������Ŀ�����ļ��������޻��棬flush��Ч
 *
 *      ByteArrayInputStream��ByteArrayOutputStream: �������Ŀ�����ֽ�����������޻��棬flush��Ч
 *
 * FilterInputStream��FilterOutputStream��
 * |
 *      DataInputStream��DataOutputStream:���������ͺ��ַ�������ֻ���ֽڶ�д��װ���ࡣ
 *       ת��Ϊ���Ӧ�Ķ������ֽ��ٽ��ж�д��Ч�ʽϵ�
 *
 *      BufferedInputStream��BufferedOutputStream:������������ṩ���幦�ܵ�װ���ࡣ
 *          Ĭ�ϵ�new byte[]�Ļ�������СΪ8192��flush�ύbuf���ݵ�����
 *
 *      PrintStream
 *
 * PipedInputStream��PipedOutputStream���ֱ��ǹܵ������������
 *  �������ö��߳̿���ͨ���ܵ������̼߳��ͨѶ��
 *  ��ʹ�ùܵ�ͨ��ʱ�����뽫 PipedOutputStream �� PipedInputStream ����ʹ�á�
 *
 *
 *
 * ���ı�"�ַ�"��ʽ��д�������ı��ļ��б���ǳ���Ҫ����
 *
 * Reader��Writer���ַ����ĳ�����ࡣ
 *
 *      InputStreamReader��OutputStreamWriter:���ֽ���ת��Ϊ�ַ�����
 *          һ����Ҫ�Ĳ����Ǳ������ͣ����û�д���ΪϵͳĬ�ϱ��롣new char[]{(char)var1}
 *
 *          FileReader��FileWriter���������Ŀ�����ļ����ַ���.����ָ���������͡��޻��壡
 *
 *      CharArrayReader��CharArrayWriter: �������Ŀ���� char ��̬����������ַ�����
 *          ���� ByteArrayInputStream��ByteArrayOutputStream��
 *
 *      StringReader��StringWriter���������Ŀ���� String ���ַ�����
 *          �� CharArrayReader��CharArrayWriter ���ơ�
 *
 * (���ã� �ַ����� �л��棡��)
 *      BufferedReader��BufferedWriter��װ���࣬������������ṩ�����Լ����ж�д���ܣ�
 *          FileReader��FileWriter ��û�л���ġ�Ҳ���ܰ��ж�д������һ��Ӧ�������ǵ�������϶�Ӧ�Ļ����ࡣ
 *
 *      PrintWriter��װ���࣬��ֱ��ָ���ļ�����Ϊ����������ָ���������͡������Զ����塢
 *          �����Զ�����������ת��Ϊ�ַ�������������ļ�ʱ��������ѡ����ࡣ
 *
 *      PipedReader��PipedWriter���ֱ����ַ��ܵ����������
 *
 */
public class IOStream {

    public static void main(String[] args) throws Exception {

        String filePath = "";
        File file = new File(filePath);

        /*
        * ���ӣ�Chaining������:
        *
        * ���Խ�һ��������������һ������������β��ӣ�
        * ������֮һ�������Ϊ��һ����������γ�һ�����ܵ�����
        *
        * */
        new DataInputStream(new FileInputStream(file));

        /*
        * �Գ��Ե���Ʋ���:
        *
        * InputStream �� OutputStream ��"�ֽ�"�����������
        *
        * Reader �� Writer ��"�ַ�"�����������
        *
        * */

        /*
        *
        * װ����ģʽ�����豻װ�ζ���һ���µ����������绺��Buffered
        *
        * ���Ǹ�һ����������һЩ�µĹ��ܣ������Ƕ�̬�ģ�Ҫ��װ�ζ���ͱ�װ�ζ���
        * ʵ��ͬһ���ӿ�(����"Reader")��װ�ζ�����б�װ�ζ����ʵ���������ַ�����װ�Σ������ֽ�����װ�Σ���
        *
        * */
        //��InputStreamReaderװ�γ�BufferedReader����Ϊ�߱�����������Reader��
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(isr);

        /*
        *
        * ������ģʽ: ���磬���ֽ��� ����� �ַ���
        *
        * ���ǽ�ĳ����Ľӿ�ת����������������һ���ӿڱ�ʾ��
        *
        * Ŀ�����������ڽӿڲ�ƥ������ɵ���ļ��������⣨�ַ������ֽ����以�����䣩��
        *
        * */
        //��FileInputStream�ļ��ֽ��������InputStreamReader�ַ����������ļ��ַ�����
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fis);

    }
}
