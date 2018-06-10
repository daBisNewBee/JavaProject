package jvm.gc;

/**
 *
 * https://www.zhihu.com/question/21539353
 *
 * 引用计数算法的缺点：
 *  循环引用
 *
 * 结果：
 *  内存泄漏
 *
 * 解决：
 *  可达性算法
 *
 *
 *
 */
public class GCTest {

    static class GCObject{
        public Object instance = null;
    }

    public static void main(String[] args) {


        // GcObject实例1的引用计数加1，实例1的引用计数=1；
        GCObject gcObject1 = new GCObject();

        // GcObject实例2的引用计数加1，实例2的引用计数=1；
        GCObject gcObject2 = new GCObject();

        // GcObject实例2的引用计数再加1，实例2的引用计数=2；
        gcObject1.instance = gcObject2;

        // GcObject实例1的引用计数再加1，实例1的引用计数=2；
        gcObject2.instance = gcObject1;

        // 栈帧中obj1不再指向Java堆，GcObject实例1的引用计数减1，结果为1；
        gcObject1 = null;

        // 栈帧中obj2不再指向Java堆，GcObject实例2的引用计数减1，结果为1。
        gcObject2 = null;
    }
}
