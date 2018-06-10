package shotEveryDay;

import java.io.*;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484460&idx=1&sn=75b79ee49bfdaab55b0d6c4fd5fa7a78&chksm=eb509fcedc2716d836f2b5f5e5f3cd31a4925204bea1682d570eea8b03518cd04460fbf4aa12&scene=21#wechat_redirect
 *
 * 以"二进制"字节方式读写的流：
 *
 * InputStream、OutputStream:
 *
 * （常用！！ 二进制方式，效率高！  缺点是无缓存！！）
 *      FileInputStream、FileOutputStream: 输入输出目标是文件的流。无缓存，flush无效
 *
 *      ByteArrayInputStream、ByteArrayOutputStream: 输入输出目标是字节数组的流。无缓存，flush无效
 *
 * FilterInputStream、FilterOutputStream：
 * |
 *      DataInputStream、DataOutputStream:按基本类型和字符串而非只是字节读写流装饰类。
 *       转换为其对应的二进制字节再进行读写，效率较低
 *
 *      BufferedInputStream、BufferedOutputStream:对输入输出流提供缓冲功能的装饰类。
 *          默认的new byte[]的缓冲区大小为8192，flush提交buf内容到流。
 *
 *      PrintStream
 *
 * PipedInputStream、PipedOutputStream：分别是管道输入输出流，
 *  作用是让多线程可以通过管道进行线程间的通讯，
 *  在使用管道通信时，必须将 PipedOutputStream 和 PipedInputStream 配套使用。
 *
 *
 *
 * 以文本"字符"方式读写的流（文本文件中编码非常重要）：
 *
 * Reader、Writer：字符流的抽象基类。
 *
 *      InputStreamReader、OutputStreamWriter:将字节流转换为字符流，
 *          一个重要的参数是编码类型，如果没有传则为系统默认编码。new char[]{(char)var1}
 *
 *          FileReader、FileWriter：输入输出目标是文件的字符流.不能指定编码类型。无缓冲！
 *
 *      CharArrayReader、CharArrayWriter: 输入输出目标是 char 动态调整数组的字符流，
 *          类似 ByteArrayInputStream、ByteArrayOutputStream。
 *
 *      StringReader、StringWriter：输入输出目标是 String 的字符流，
 *          与 CharArrayReader、CharArrayWriter 类似。
 *
 * (常用！ 字符流！ 有缓存！！)
 *      BufferedReader、BufferedWriter：装饰类，对输入输出流提供缓冲以及按行读写功能，
 *          FileReader、FileWriter 是没有缓冲的、也不能按行读写，所以一般应该在它们的外面包上对应的缓冲类。
 *
 *      PrintWriter：装饰类，可直接指定文件名作为参数、可以指定编码类型、可以自动缓冲、
 *          可以自动将多种类型转换为字符串，在输出到文件时可以优先选择该类。
 *
 *      PipedReader、PipedWriter：分别是字符管道输入输出流
 *
 */
public class IOStream {

    public static void main(String[] args) throws Exception {

        String filePath = "";
        File file = new File(filePath);

        /*
        * 链接（Chaining）机制:
        *
        * 可以将一个流处理器跟另一个流处理器首尾相接，
        * 以其中之一的输出作为另一个的输入而形成一个流管道链接
        *
        * */
        new DataInputStream(new FileInputStream(file));

        /*
        * 对称性的设计策略:
        *
        * InputStream 和 OutputStream 的"字节"输入输出操作
        *
        * Reader 和 Writer 的"字符"输入输出操作
        *
        * */

        /*
        *
        * 装饰者模式：赋予被装饰对象一个新的能力，比如缓存Buffered
        *
        * 就是给一个对象增加一些新的功能，而且是动态的，要求装饰对象和被装饰对象
        * 实现同一个接口(比如"Reader")，装饰对象持有被装饰对象的实例（各种字符流间装饰，各种字节流间装饰）。
        *
        * */
        //把InputStreamReader装饰成BufferedReader来成为具备缓冲能力的Reader。
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(isr);

        /*
        *
        * 适配器模式: 比如，把字节流 适配成 字符流
        *
        * 就是将某个类的接口转换成我们期望的另一个接口表示，
        *
        * 目的是消除由于接口不匹配所造成的类的兼容性问题（字符流与字节流间互相适配）。
        *
        * */
        //把FileInputStream文件字节流适配成InputStreamReader字符流来操作文件字符串。
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fis);

    }
}
