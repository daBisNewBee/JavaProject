package jvm;

import org.bouncycastle.util.encoders.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 *
 * 字节序的处理，就是一句话：
 * "只有读取的时候，才必须区分字节序，其他情况都不用考虑。"
 *
 * 字节序：
 * 字节顺是指占用内存"多于一个字节"（注意！！）类型的数据在内存中的存放顺序
 *
 * 是什么？
 * raw：     0x12345678
 * big:      0x12、34、56、78(记忆规律：目测顺序)
 * little:   0x78、56、34、12
 *           低地址 ---->  高地址
 *
 * 几个常用字节序？
 * 大端：
 *      1. Java。（因为JVM的存在）
 *      2. 网络字节序
 *      3. Motorola 6800，PowerPC 970，SPARC（除V9外）等处理器
 *
 * 小端：
 *      1. (IA架构的CPU)x86系列，VAX，PDP-11等处理器
 *      2. 主机字节序（整数在内存中存储的顺序，现在 Little Endian 比较普遍。（不同的 CPU 有不同的字节序））
 *
 * 处理器像ARM, DEC Alpha的字节序是"可配置"的
 *
 * 两大CPU派系：
 *      1. Motorola的PowerPC系列 (大)
 *      2. Intel的x86系列 （小）
 *
 * 起因？为何会有大端、小端字节序？
 * 大端：
 *      人类还是习惯读写大端字节序。
 *      所以，除了计算机的内部处理，其他的场合几乎都是大端字节序，比如网络传输和文件储存。
 * 小端：
 *      计算机电路先处理低位字节，效率比较高，因为计算都是从低位开始的。
 *      所以，计算机的内部处理都是小端字节序。
 *
 * 参考：
 * 理解字节序：（阮一峰）
 * http://www.ruanyifeng.com/blog/2016/11/byte-order.html
 *
 */
public class ByteOrderTest {

    private static final int raw = 0x12345678;

    public static void main(String[] args) {
        /*
        * 需要在"VM options"中设置 "-ea"，assert才会生效
        * */
        assert true;

        // 正确的验证字节序方法
        checkOrder();

        // TODO: 以下两个错误的示范，原因未知
        byte by = (byte)raw;
        System.out.println(Hex.toHexString(new byte[]{by})); // 大端不应该是12 ？

        char ch = (char)raw;
        if (0x12 == ch)
            System.out.println("这是大端");
        else if (0x78 == ch)
            System.out.println("这是小端");
        else
            System.out.println("异常");
    }

    static void checkOrder(){
        int x = 0x01020304;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.asIntBuffer().put(x);
        System.out.println("默认的字节序：" + Arrays.toString(buffer.array()) + " order:" + buffer.order().toString() );

        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asIntBuffer().put(x);
        System.out.println("当前的字节序：" + Arrays.toString(buffer.array()) + " order:" + buffer.order().toString() );

    }
}
