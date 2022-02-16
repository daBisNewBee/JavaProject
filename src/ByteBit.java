public class ByteBit {

    static void bytebit() {
        int a = 1;
        Integer b = 1; // 反编译后：Integer.valueOf(1);
        Integer c = new Integer(1);
        System.out.println("a == b " + (a == b)); // true 因为自动拆箱
        System.out.println("b == c " + (b == c)); // false
        System.out.println("a == c " + (a == c)); // true 因为自动拆箱


        Integer i = 10; // 自动装箱： Integer.valueOf(10);
        int n = i;      // 自动拆箱： i.intValue();
    }

    public static void main(String[] args) {
        bytebit();
    }
}
