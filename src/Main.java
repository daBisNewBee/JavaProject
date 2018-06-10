public class Main {

    static void log(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
//        System.out.println("Hello World!");

        // 常量位于栈中
        String str1 = "abc";
        String str2 = "abc";
        log(Boolean.toString(str1 == str2));// true

        // new后的对象位于堆中
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
