package shotEveryDay;

/**
 *
 * Ϊ�λ��в��룿
 *
 *  "����λ"��Ȼ���ü�����Ļ�����·��Ʊ��ʮ�ָ���!
 *
 *  �������ǿ�ʼ̽�� ��"����λ��������", ����"ֻ�����ӷ�"�ķ���.
 *
 *  ������ʽ������ ����ʵ�����㡣
 *
 *  ������ʵ�ʱ���Ķ������ֵĲ��룡
 *
 * ԭ��, ����, ���� ��⣺
 *  http://www.cnblogs.com/zhangziqiu/archive/2011/03/30/ComputerCode.html#!comments
 *
 */
public class ByteTest {

    public static void main(String[] args) {
//        byte n1 = 10 + 117;
//        byte n2 = 10 + 118;
//        System.out.println(n1);
//        System.out.println(n2);

         byte n1 = 10; //��final�����±������������ᱨ��
//        byte n2 = n1 + 117;
//        System.out.println(n2);

        byte n3 = (byte) 128;
        System.out.println(n3);// -128.����������ݶ���ͨ������洢��

        byte n4 = (byte) (n1 + 117);
        System.out.println(n4);// 127

    }

}
