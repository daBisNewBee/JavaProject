public class Main {

    static void log(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
//        System.out.println("Hello World!");

        // ����λ��ջ��
        String str1 = "abc";
        String str2 = "abc";
        log(Boolean.toString(str1 == str2));// true

        // new��Ķ���λ�ڶ���
        String str3 = new String("abc");
        log(Boolean.toString(str1 == str3)); // false

        String str4 = new String("abc");
        log(Boolean.toString(str3 == str4));
        log(Boolean.toString(str3 .equals(str4)));// false

        SingleTon singleTon = SingleTon.getInstance("");

        System.gc();
        int a = 1;

        int b;

        a = a++;

        log("a:"+a);

        b = a++;

        log("a:"+a);
        log("b:"+a);

        System.gc();

    }
}
