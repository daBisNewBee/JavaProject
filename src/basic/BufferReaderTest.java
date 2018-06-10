package basic;

import java.io.*;

/**
 *
 *
 * windows: CRLF    \r\n    0d0a
 * Linux:   LF      \n      0a
 * MACOS:   CR      \r      0d
 *
 *  BufferedReader 读取 CRLF 的ini文件时，末尾行回车，会造成null
 *
 *  LF、CR时，可允许空行。
 *
 */
public class BufferReaderTest {

    public static void main(String[] args) throws Exception {

        String testFilePath = "/Users/user/Documents/git/MyApplication/test/src/main/assets/test.ini";

        FileWriter fw = new FileWriter(testFilePath,true);
        for (int i = 0; i < 10; i++) {
            fw.write(i);
            Thread.sleep(2000);
        }
        fw.close();

//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(testFilePath))));
        BufferedReader reader = new BufferedReader(new FileReader(testFilePath));
//        BufferedReader reader = new BufferedReader(new StringReader("hello\n"));

        String buf = null;
        int len = 0;

        try {
            while (reader.ready()){

                buf = reader.readLine().trim();
                len += buf.length();
                Thread.sleep(3000);
                System.out.println("buf = " + buf+" len:"+len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
