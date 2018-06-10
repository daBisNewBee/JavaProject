package basic;

/**
 *
 * 包装类常量池技术：
 *
 * -128 ~ 127
 *
 * 取值在该范围内的包装类对象为同一个。
 *
 * 作用：对提高Java程序的性能
 *
 * 参考：Integer.IntegerCache
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
        * Java中八种基本类型的包装类的大部分都实现了常量池技术，
        * 它们是Byte、Short、Integer、Long、Character、Boolean，
        * 另外两种浮点数类型的包装类(Float、Double)则没有实现。
        * 另外Byte,Short,Integer,Long,Character这5种整型的包装类
        * 也只是在对应值在"-128到127"时才可使用对象池。
        * */
        Integer ii  = 127;
//        Integer ii  = new Integer(127); //false。IntegerCache不适用与此种声明方式
        Integer jj  = 127;
//        Integer jj  = new Integer(127);
        Integer mm  = 128;
        Integer nn  = 128;

        System.out.println("i == j:"+(i==j));
        // true. 栈内存数据共享
        System.out.println("m == n:"+(m==n));
        // true. 栈内存数据共享
        System.out.println("ii == jj:"+(ii==jj));
        // true. 取自JVM的常量池。除了flout、double，其他六种基本类型都实现了常量池
        System.out.println("mm == nn:"+(mm==nn));
        // false. 位于"堆内存"。常量池中对int的初始化范围 -128 ~ 127，128超过了常量池范围
        // true: 修改常量池范围。java -XX:AutoBoxCacheMax=128 -classpath . basic.data

        // 2. 验证：Float、Double不在常量池中
        Float f = new Float(100);
        Float ff = new Float(100);

        Double d = new Double(100);
        Double dd = new Double(100);

        System.out.println("f == ff:"+(f==ff));
        // false
        System.out.println("d == dd:"+(d==dd));
        // false

        // 3. 性能测试？？ TODO
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
