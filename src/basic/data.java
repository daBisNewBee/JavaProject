package basic;

/**
 *
 * ��װ�ೣ���ؼ�����
 *
 * -128 ~ 127
 *
 * ȡֵ�ڸ÷�Χ�ڵİ�װ�����Ϊͬһ����
 *
 * ���ã������Java���������
 *
 * �ο���Integer.IntegerCache
 *
 */
public class data {

    public static void main(String[] args) {
        int i = 127;
        int j = 127;
        int m = 128;
        int n = 128;

        /*
        *
        * Java�а��ֻ������͵İ�װ��Ĵ󲿷ֶ�ʵ���˳����ؼ�����
        * ������Byte��Short��Integer��Long��Character��Boolean��
        * �������ָ��������͵İ�װ��(Float��Double)��û��ʵ�֡�
        * ����Byte,Short,Integer,Long,Character��5�����͵İ�װ��
        * Ҳֻ���ڶ�Ӧֵ��"-128��127"ʱ�ſ�ʹ�ö���ء�
        * */
        Integer ii  = 127;
//        Integer ii  = new Integer(127); //false��IntegerCache�����������������ʽ
        Integer jj  = 127;
//        Integer jj  = new Integer(127);
        Integer mm  = 128;
        Integer nn  = 128;

        System.out.println("i == j:"+(i==j));
        // true. ջ�ڴ����ݹ���
        System.out.println("m == n:"+(m==n));
        // true. ջ�ڴ����ݹ���
        System.out.println("ii == jj:"+(ii==jj));
        // true. ȡ��JVM�ĳ����ء�����flout��double���������ֻ������Ͷ�ʵ���˳�����
        System.out.println("mm == nn:"+(mm==nn));
        // false. λ��"���ڴ�"���������ж�int�ĳ�ʼ����Χ -128 ~ 127��128�����˳����ط�Χ
        // true: �޸ĳ����ط�Χ��java -XX:AutoBoxCacheMax=128 -classpath . basic.data

        // 2. ��֤��Float��Double���ڳ�������
        Float f = new Float(100);
        Float ff = new Float(100);

        Double d = new Double(100);
        Double dd = new Double(100);

        System.out.println("f == ff:"+(f==ff));
        // false
        System.out.println("d == dd:"+(d==dd));
        // false

        // 3. ���ܲ��ԣ��� TODO
        long start = System.currentTimeMillis();
        for (int k = 0; k < 1000 * 1000 * 1000 * 100000; k++) {
            Integer.valueOf(127);
        }
        long end = System.currentTimeMillis();
        System.out.println("cached used:"+(end-start)+"ms");

        start = System.currentTimeMillis();
        for (int k = 0; k < 1000 * 1000 * 1000 * 100000; k++) {
            Integer.valueOf(1280);
        }
        end = System.currentTimeMillis();
        System.out.println("no cached used:"+(end-start)+"ms");

    }
}
