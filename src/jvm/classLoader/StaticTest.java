package jvm.classLoader;

/**
 *
 * http://blog.csdn.net/u013256816/article/details/50837863
 *
 * ����ػ��Ʋ��ԣ���ʵ����ʼ����Ȼ�����ھ�̬��ʼ��֮ǰ����
 * �����
 *       2
         3
         a=110,b=0     // b��0 �������b��final������ô�죿
         1
         4
 *
 * ԭ��
 * 1. ִ�о�̬�����������ʼ����ִ���๹����<clinit>()
 * 2. clinit��ִ��˳�򣺾�̬�����������ĸ�ֵ�����Դ���е�˳��
 * �˴�Ϊ��st = new StaticTest() ->  System.out.println("1"); -> static int b =112;
 * 3. �ڵ�һ����̬�����ĸ�ֵ�����Ƕ�׶����ʼ����������Ϊ������"�����ʼ�������ʼ��֮ǰ"�ļ���
 * 4. �����ʼ���Ĳ���˳���ȳ�ʼ����Ա������ִ�й��췽��
 *
 */
public class StaticTest {

    public static void main(String[] args)
    {
        staticFunction();
    }

    // ��
    static StaticTest st = new StaticTest();

    static
    {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a=110;
    static int b =112;
//    final static int b =112;    //ע����������final������
}
