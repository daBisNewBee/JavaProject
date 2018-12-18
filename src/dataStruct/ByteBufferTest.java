package dataStruct;

import org.bouncycastle.util.encoders.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 *
 * 4个属性：capacity、limit、position、mark
 *
 * 遵循：mark <= position <= limit <= capacity
 *
 * mark:
 *  标记。调用mark()来设置mark=position，再调用reset()可以让position恢复到标记的位置
 *
 * position:
 *  位置。下一个要被读、写的元素索引。"相对读、写"会改变该值，"绝对读、写"不会改变该值。
 *
 * limit：
 *  当前缓冲区的终点。不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的；
 *
 * capacity：
 *  容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变
 *
 * 参考：
 * ByteBuffer常用方法详解：
 * https://blog.csdn.net/z69183787/article/details/77102198
 *
 * ByteBuffer的四大类操作方法:
 * https://www.aliyun.com/jiaocheng/770119.html
 *
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        // 实例化
//        initialize();
        // 基本用法
//        basic();
        byteOrder();
    }

    static void initialize(){
        System.out.println("before allocate:");
        System.out.println(Runtime.getRuntime().freeMemory());

        // 在"堆"中分配内存，返回的是"HeapByteBuffer"
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 100 * 100);
        System.out.println("byteBuffer = " + byteBuffer);
        System.out.println("after allocate:（此处应该减少 1024 * 100 * 100）");
        System.out.println(Runtime.getRuntime().freeMemory());

        // 使用"系统内存"，不影响"堆"的大小，返回的是"DirectByteBuffer"
        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(1024 * 1000 * 100);
        System.out.println("byteBuffer1 = " + byteBuffer1);
        System.out.println("after allocateDirect:(此处应该不变)");
        System.out.println(Runtime.getRuntime().freeMemory());

        byte[] buffer = new byte[]{0x12, 0x34, 0x56, 0x78};
        // 注意：该种方法传的是数组引用！不另外分配空间
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(buffer);
        System.out.println(Hex.toHexString(byteBuffer2.array()));

        // 对原数组修改会改变ByteBuffer内容!
        buffer[2] = (byte) 0xFF;
        System.out.println("经过修改：");
        System.out.println(Hex.toHexString(byteBuffer2.array()));
        /*
        * 12345678
          1234ff78
        * */
        System.out.println(Hex.toHexString(new byte[]{byteBuffer2.get()}));

        ByteBuffer byteBuffer3 = ByteBuffer.wrap(buffer, 2, 2);
        System.out.println("比较wrap offset为2后，get的首位不同：");
        System.out.println(Hex.toHexString(new byte[]{byteBuffer3.get()}));
    }

    static void basic(){
        byte[] buffer = new byte[32];
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        System.out.println("byteBuffer = " + byteBuffer);

        /*
        * reset:
        * 把position设置成mark的值，相当于之前做过一个标记，现在要退回到之前标记的地方
        * */
        byteBuffer.clear();
        byteBuffer.position(5);
        byteBuffer.mark(); // 记录 之前的position=5到"mark"

        byteBuffer.position(10);
        System.out.println("reset 之前 = " + byteBuffer);
        byteBuffer.reset();
        // 注意：reset后的位置仍为5
        System.out.println("reset 之后 = " + byteBuffer);

        /*
        * rewind:
        * 清零。position=0, 一般用于写完数据之后，想从起始位置开始读数据的情形
        * */
        byteBuffer.clear();
        byteBuffer.position(10);
        byteBuffer.limit(15);
        System.out.println("rewind 之前 = " + byteBuffer);
        byteBuffer.rewind();
        System.out.println("rewind 之后 = " + byteBuffer);

        byteBuffer.clear();
        byteBuffer.put("abcd".getBytes());
        System.out.println("compact 之前 = " + byteBuffer);
        /*
        * flip:
        * 翻转：让position到limit的这块区域变成0到position这块。
        * 让处于"存数据状态的缓冲区"变成一个处于"准备取数据的状态"
        * */
        byteBuffer.flip();
        System.out.println("flip 之后 = " + byteBuffer);
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println("在三次get()之后 = " + byteBuffer);
        System.out.println("\t" + new String(byteBuffer.array()));
        /*
        * compact：
        * 压缩。
        * 把从position到limit中的内容移到0到limit-position的区域内，
        * position和limit的取值也分别变成limit-position、capacity
        * */
        byteBuffer.compact();
        System.out.println("compact 之后 = " + byteBuffer);
        System.out.println("\t" + new String(byteBuffer.array()));

        byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put((byte)'a').put((byte)'b').put((byte)'c')
                .put((byte)'d').put((byte)'e').put((byte)'f');
        System.out.println("flip 之前 = " + byteBuffer);
        byteBuffer.flip(); // 转为读取模式
        System.out.println("get() 之前 = " + byteBuffer);
        /*
        * 相对读写：（改变position）
        *     get()、put()
        * 绝对读写：（不改变position）
        *     get(index)、put(index, xxx)
        * */
        System.out.println((char) byteBuffer.get());
        System.out.println("get() 之后 = " + byteBuffer);
        System.out.println((char) byteBuffer.get(3));
        // get(index)不影响position的值
        System.out.println("get(index) 之后 = " + byteBuffer);

        byte[] dst = new byte[10];
        byteBuffer.get(dst, 0, 2);
        System.out.println("get(dst, 0, 2) 之后 = " + byteBuffer);
        System.out.println("dst = " + new String(dst));
        System.out.println("byteBuffer array = " + new String(byteBuffer.array()));

        ByteBuffer bb = ByteBuffer.allocate(32);
        System.out.println("bb = " + bb);
        bb.put((byte)'z');
        System.out.println("put((byte)'z') 之后 = " + bb);
        // 指定index的put，不改变position
        bb.put(2, (byte) 'c');
        System.out.println("put(2, (byte) 'c') = " + bb);
        System.out.println("bb = " + new String(bb.array()));
        // 从 byteBuffer 当前position的数据填充到bb的当前position
        bb.put(byteBuffer);
        System.out.println("bb put(byteBuffer) 之后 = " + bb);
        System.out.println("bb array = " + new String(bb.array()));
    }

    /**
     * ByteBuffer 的order(ByteOrder.LITTLE_ENDIAN);这个方法只对"非字节数组类型"的变量有效!!
     *
     * 因为我们直接操作字节数组的时候，无论CPU如何存放，我们已经手动规定死了字节的存放顺序。
     *
     * 所以这一方法无影响。
     */
    static void byteOrder(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.asCharBuffer().put("abcde");
        System.out.println("默认的字节序 = " + Arrays.toString(buffer.array()));

        buffer.rewind();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.asCharBuffer().put("abcde");
        System.out.println("设置 BIG_ENDIAN 后 = " + Arrays.toString(buffer.array()));

        buffer.rewind();
        buffer.order(ByteOrder.LITTLE_ENDIAN); // 1. 先
        buffer.asCharBuffer().put("abcde"); // 2. 后
        System.out.println("设置 LITTLE_ENDIAN 后 = " + Arrays.toString(buffer.array()));

        /*
        * 根据以上的结论：
        * 1. order方法设置字节的排放次序
        *
        * 2. 注意顺序：先order，再put（颠倒失效）
        * */
        long2Byte(8L);
    }

    static void long2Byte(long num){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.asLongBuffer().put(num);
        System.out.println("默认的字节顺序 = " + Arrays.toString(buffer.array()));
        System.out.println("字节序 = " + buffer.order());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asLongBuffer().put(num);
        System.out.println("设置 LITTLE_ENDIAN 后 = " + Arrays.toString(buffer.array()));
        System.out.println("字节序 = " + buffer.order());
    }
}
