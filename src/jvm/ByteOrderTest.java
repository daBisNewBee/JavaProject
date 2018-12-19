package jvm;

import org.bouncycastle.util.encoders.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 *
 * �ֽ���Ĵ�������һ�仰��
 * "ֻ�ж�ȡ��ʱ�򣬲ű��������ֽ���������������ÿ��ǡ�"
 *
 * �ֽ���
 * �ֽ�˳��ָռ���ڴ�"����һ���ֽ�"��ע�⣡�������͵��������ڴ��еĴ��˳��
 *
 * ��ʲô��
 * raw��     0x12345678
 * big:      0x12��34��56��78(������ɣ�Ŀ��˳��)
 * little:   0x78��56��34��12
 *           �͵�ַ ---->  �ߵ�ַ
 *
 * ���������ֽ���
 * ��ˣ�
 *      1. Java������ΪJVM�Ĵ��ڣ�
 *      2. �����ֽ���
 *      3. Motorola 6800��PowerPC 970��SPARC����V9�⣩�ȴ�����
 *
 * С�ˣ�
 *      1. (IA�ܹ���CPU)x86ϵ�У�VAX��PDP-11�ȴ�����
 *      2. �����ֽ����������ڴ��д洢��˳������ Little Endian �Ƚ��ձ顣����ͬ�� CPU �в�ͬ���ֽ��򣩣�
 *
 * ��������ARM, DEC Alpha���ֽ�����"������"��
 *
 * ����CPU��ϵ��
 *      1. Motorola��PowerPCϵ�� (��)
 *      2. Intel��x86ϵ�� ��С��
 *
 * ����Ϊ�λ��д�ˡ�С���ֽ���
 * ��ˣ�
 *      ���໹��ϰ�߶�д����ֽ���
 *      ���ԣ����˼�������ڲ����������ĳ��ϼ������Ǵ���ֽ��򣬱������紫����ļ����档
 * С�ˣ�
 *      �������·�ȴ����λ�ֽڣ�Ч�ʱȽϸߣ���Ϊ���㶼�Ǵӵ�λ��ʼ�ġ�
 *      ���ԣ���������ڲ�������С���ֽ���
 *
 * �ο���
 * ����ֽ��򣺣���һ�壩
 * http://www.ruanyifeng.com/blog/2016/11/byte-order.html
 *
 */
public class ByteOrderTest {

    private static final int raw = 0x12345678;

    public static void main(String[] args) {
        /*
        * ��Ҫ��"VM options"������ "-ea"��assert�Ż���Ч
        * */
        assert true;

        // ��ȷ����֤�ֽ��򷽷�
        checkOrder();

        // TODO: �������������ʾ����ԭ��δ֪
        byte by = (byte)raw;
        System.out.println(Hex.toHexString(new byte[]{by})); // ��˲�Ӧ����12 ��

        char ch = (char)raw;
        if (0x12 == ch)
            System.out.println("���Ǵ��");
        else if (0x78 == ch)
            System.out.println("����С��");
        else
            System.out.println("�쳣");
    }

    static void checkOrder(){
        int x = 0x01020304;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.asIntBuffer().put(x);
        System.out.println("Ĭ�ϵ��ֽ���" + Arrays.toString(buffer.array()) + " order:" + buffer.order().toString() );

        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asIntBuffer().put(x);
        System.out.println("��ǰ���ֽ���" + Arrays.toString(buffer.array()) + " order:" + buffer.order().toString() );

    }
}
