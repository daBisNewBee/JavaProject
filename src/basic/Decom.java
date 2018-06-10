package basic;

public class Decom {
    public static void main(String[] args) {
        Integer i = 100; // 实际为"Integer.valueOf(100)"，javap查看
        Integer j = Integer.valueOf(100);
        Integer m = new Integer(100);
    }
}
