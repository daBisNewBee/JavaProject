package dataStruct;

import org.bouncycastle.util.encoders.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 *
 * 4�����ԣ�capacity��limit��position��mark
 *
 * ��ѭ��mark <= position <= limit <= capacity
 *
 * mark:
 *  ��ǡ�����mark()������mark=position���ٵ���reset()������position�ָ�����ǵ�λ��
 *
 * position:
 *  λ�á���һ��Ҫ������д��Ԫ��������"��Զ���д"��ı��ֵ��"���Զ���д"����ı��ֵ��
 *
 * limit��
 *  ��ǰ���������յ㡣���ܶԻ������������޵�λ�ý��ж�д�������Ҽ����ǿ����޸ĵģ�
 *
 * capacity��
 *  ���������������ɵ�������������ڻ���������ʱ���趨���Ҳ��ܸı�
 *
 * �ο���
 * ByteBuffer���÷�����⣺
 * https://blog.csdn.net/z69183787/article/details/77102198
 *
 * ByteBuffer���Ĵ����������:
 * https://www.aliyun.com/jiaocheng/770119.html
 *
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        // ʵ����
//        initialize();
        // �����÷�
//        basic();
        byteOrder();
    }

    static void initialize(){
        System.out.println("before allocate:");
        System.out.println(Runtime.getRuntime().freeMemory());

        // ��"��"�з����ڴ棬���ص���"HeapByteBuffer"
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 100 * 100);
        System.out.println("byteBuffer = " + byteBuffer);
        System.out.println("after allocate:���˴�Ӧ�ü��� 1024 * 100 * 100��");
        System.out.println(Runtime.getRuntime().freeMemory());

        // ʹ��"ϵͳ�ڴ�"����Ӱ��"��"�Ĵ�С�����ص���"DirectByteBuffer"
        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(1024 * 1000 * 100);
        System.out.println("byteBuffer1 = " + byteBuffer1);
        System.out.println("after allocateDirect:(�˴�Ӧ�ò���)");
        System.out.println(Runtime.getRuntime().freeMemory());

        byte[] buffer = new byte[]{0x12, 0x34, 0x56, 0x78};
        // ע�⣺���ַ����������������ã����������ռ�
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(buffer);
        System.out.println(Hex.toHexString(byteBuffer2.array()));

        // ��ԭ�����޸Ļ�ı�ByteBuffer����!
        buffer[2] = (byte) 0xFF;
        System.out.println("�����޸ģ�");
        System.out.println(Hex.toHexString(byteBuffer2.array()));
        /*
        * 12345678
          1234ff78
        * */
        System.out.println(Hex.toHexString(new byte[]{byteBuffer2.get()}));

        ByteBuffer byteBuffer3 = ByteBuffer.wrap(buffer, 2, 2);
        System.out.println("�Ƚ�wrap offsetΪ2��get����λ��ͬ��");
        System.out.println(Hex.toHexString(new byte[]{byteBuffer3.get()}));
    }

    static void basic(){
        byte[] buffer = new byte[32];
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        System.out.println("byteBuffer = " + byteBuffer);

        /*
        * reset:
        * ��position���ó�mark��ֵ���൱��֮ǰ����һ����ǣ�����Ҫ�˻ص�֮ǰ��ǵĵط�
        * */
        byteBuffer.clear();
        byteBuffer.position(5);
        byteBuffer.mark(); // ��¼ ֮ǰ��position=5��"mark"

        byteBuffer.position(10);
        System.out.println("reset ֮ǰ = " + byteBuffer);
        byteBuffer.reset();
        // ע�⣺reset���λ����Ϊ5
        System.out.println("reset ֮�� = " + byteBuffer);

        /*
        * rewind:
        * ���㡣position=0, һ������д������֮�������ʼλ�ÿ�ʼ�����ݵ�����
        * */
        byteBuffer.clear();
        byteBuffer.position(10);
        byteBuffer.limit(15);
        System.out.println("rewind ֮ǰ = " + byteBuffer);
        byteBuffer.rewind();
        System.out.println("rewind ֮�� = " + byteBuffer);

        byteBuffer.clear();
        byteBuffer.put("abcd".getBytes());
        System.out.println("compact ֮ǰ = " + byteBuffer);
        /*
        * flip:
        * ��ת����position��limit�����������0��position��顣
        * �ô���"������״̬�Ļ�����"���һ������"׼��ȡ���ݵ�״̬"
        * */
        byteBuffer.flip();
        System.out.println("flip ֮�� = " + byteBuffer);
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println("������get()֮�� = " + byteBuffer);
        System.out.println("\t" + new String(byteBuffer.array()));
        /*
        * compact��
        * ѹ����
        * �Ѵ�position��limit�е������Ƶ�0��limit-position�������ڣ�
        * position��limit��ȡֵҲ�ֱ���limit-position��capacity
        * */
        byteBuffer.compact();
        System.out.println("compact ֮�� = " + byteBuffer);
        System.out.println("\t" + new String(byteBuffer.array()));

        byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put((byte)'a').put((byte)'b').put((byte)'c')
                .put((byte)'d').put((byte)'e').put((byte)'f');
        System.out.println("flip ֮ǰ = " + byteBuffer);
        byteBuffer.flip(); // תΪ��ȡģʽ
        System.out.println("get() ֮ǰ = " + byteBuffer);
        /*
        * ��Զ�д�����ı�position��
        *     get()��put()
        * ���Զ�д�������ı�position��
        *     get(index)��put(index, xxx)
        * */
        System.out.println((char) byteBuffer.get());
        System.out.println("get() ֮�� = " + byteBuffer);
        System.out.println((char) byteBuffer.get(3));
        // get(index)��Ӱ��position��ֵ
        System.out.println("get(index) ֮�� = " + byteBuffer);

        byte[] dst = new byte[10];
        byteBuffer.get(dst, 0, 2);
        System.out.println("get(dst, 0, 2) ֮�� = " + byteBuffer);
        System.out.println("dst = " + new String(dst));
        System.out.println("byteBuffer array = " + new String(byteBuffer.array()));

        ByteBuffer bb = ByteBuffer.allocate(32);
        System.out.println("bb = " + bb);
        bb.put((byte)'z');
        System.out.println("put((byte)'z') ֮�� = " + bb);
        // ָ��index��put�����ı�position
        bb.put(2, (byte) 'c');
        System.out.println("put(2, (byte) 'c') = " + bb);
        System.out.println("bb = " + new String(bb.array()));
        // �� byteBuffer ��ǰposition��������䵽bb�ĵ�ǰposition
        bb.put(byteBuffer);
        System.out.println("bb put(byteBuffer) ֮�� = " + bb);
        System.out.println("bb array = " + new String(bb.array()));
    }

    /**
     * ByteBuffer ��order(ByteOrder.LITTLE_ENDIAN);�������ֻ��"���ֽ���������"�ı�����Ч!!
     *
     * ��Ϊ����ֱ�Ӳ����ֽ������ʱ������CPU��δ�ţ������Ѿ��ֶ��涨�����ֽڵĴ��˳��
     *
     * ������һ������Ӱ�졣
     */
    static void byteOrder(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.asCharBuffer().put("abcde");
        System.out.println("Ĭ�ϵ��ֽ��� = " + Arrays.toString(buffer.array()));

        buffer.rewind();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.asCharBuffer().put("abcde");
        System.out.println("���� BIG_ENDIAN �� = " + Arrays.toString(buffer.array()));

        buffer.rewind();
        buffer.order(ByteOrder.LITTLE_ENDIAN); // 1. ��
        buffer.asCharBuffer().put("abcde"); // 2. ��
        System.out.println("���� LITTLE_ENDIAN �� = " + Arrays.toString(buffer.array()));

        /*
        * �������ϵĽ��ۣ�
        * 1. order���������ֽڵ��ŷŴ���
        *
        * 2. ע��˳����order����put���ߵ�ʧЧ��
        * */
        long2Byte(8L);
    }

    static void long2Byte(long num){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.asLongBuffer().put(num);
        System.out.println("Ĭ�ϵ��ֽ�˳�� = " + Arrays.toString(buffer.array()));
        System.out.println("�ֽ��� = " + buffer.order());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asLongBuffer().put(num);
        System.out.println("���� LITTLE_ENDIAN �� = " + Arrays.toString(buffer.array()));
        System.out.println("�ֽ��� = " + buffer.order());
    }
}
