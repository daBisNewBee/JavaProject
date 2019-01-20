package jvm;


/**
 *
 *  （联系ObjectOccupy.java看）
 *
 *  推荐的使用以下方式创建锁对象:
 *      Object lock = new byte[0].
 *      synchronized(lock){
 *          //
 *      }
 *
 *  原因：
 *  1. new byte的操作码行数少。（即：为何不是 new Object() ？）
 *     反编译后，new byte[0]只需要3行，而new Object()需要7行
 *     但是，是否就是操作码步数越少，执行越快？不一定(TODO:验证)
 *
 *  2. new byte[0]的占用内存空间少。仅16（即：为何不是 new byte[1] ?）
 *     RamUsageEstimator.sizeOf(new byte[0]) ：16
 *     RamUsageEstimator.sizeOf(new byte[1]) ：24
 *
 */
public class LockBetter {

    public static void main(String[] args) {
        /*
        * 字节码：
        * Code:
           0: iconst_0
           1: newarray       byte
           3: astore_1
        * */
        byte[] lock = new byte[10];
//        byte lock = 0x11; 不行！synchronize必须对Object操作
        /*
        * Code:
           0: new           #2                  // class java/lang/Object
           3: dup
           4: invokespecial #1                  // Method java/lang/Object."<init>":()V
           7: astore_1
        *
        * invokespecial：
        *   调用Object对象的构造函数
        *
        * dup：
        *   因为方法调用会弹出参数(这里是Object对象)，
        *   因此需要上面的dup指令，保证在调用构造函数之后栈顶上还是 Object对象的引用，
        *   很多种情况下dup指令都是为这个目的而存在的
        *
        * Code为何不连续？0、3、4、7...
        *   一些指令的参数占据了一些bytecode数组空间。
        *   每一个指令占据一个字节，比如：
        *   Aload_0指令没有参数，所以占有一个字节，第二个指令invokespecial，
        *   由于它本身带有参数，结果它本身和参数分别就占据了一个位置，所以，上面的4过了就不是7
        *
        * */
//        Object lockObj = new Object();

        // 结果不准确！不能说明问题！
//        cost();
    }

    void cost(){
        Object o;
        long start = System.currentTimeMillis();
        final int MAX = (int)1.0e300;
        for (int i = 0; i < MAX; i++) {
            o = new byte[0];
        }
        long end = System.currentTimeMillis();
        System.out.println("new byte: " + (end-start));
        start = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            o = new Object();
        }
        end = System.currentTimeMillis();
        System.out.println("new Object: " + (end-start));
    }
}
