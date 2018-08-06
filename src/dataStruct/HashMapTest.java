package dataStruct;

/**
 *
 *
 * 1. 默认长度或者扩容长度为 2^n ,为什么？
 *
 *  服务于从key到Index的hash算法：indexFor，
 *  为了最大程度减少冲突，位运算（hash运算）的分布均匀
 *
 *
 *
 *
 HashMap的存取实现：
 // 存储时:
 int hash = key.hashCode(); // 这个hashCode方法这里不详述,只要理解每个key的hash是一个固定的int值
 int index = hash % Entry[].length;
 Entry[index] = value;

 // 取值时:
 int hash = key.hashCode();
 int index = hash % Entry[].length;
 return Entry[index];
 *
 *
 *
 * HASH COLLISION DOS 问题：
 *
 * https://coolshell.cn/articles/6424.html
 *
 */
public class HashMapTest {

    // 确定数组的索引。作用上相当于取模mod或者取余%
    static int indexFor(int h, int length) {
        return h & (length-1);
    }

    // Java 底层使用的
    static int hash(int h)
    {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public static void main(String[] args) {


        /*
        *
        *  1. Hash Collision：
        *
        *  “非随机的”
        *
        * */
        String tmp1 = "Aa";
        String tmp2 = "BB";

        int tmp1HashCode = tmp1.hashCode();
        int tmp2HashCode = tmp2.hashCode();
        System.out.println("tmp2HashCode = " + tmp2HashCode);
        System.out.println(tmp1HashCode == tmp2HashCode);

        // 2. 因为hashcode 冲突概率高，因此，在HashMap（JDK 1.7）中的元素都一个单链表中，
        // o(1)的复杂度降为 o(n),数据为N个时，性能降为o(n^2).
        // JDK 1.7 HashMap: https://blog.csdn.net/hxpjava1/article/details/55670439

        // 3. 因此，为了改善极端情况下o(n)的情况，
        // JDK1.8做了改进：
        // 同一个bucket中的 key <= 8 个时，使用链表结构存储
        // key > 8 个时，会调用treeifyBin函数，将链表转换为红黑树 ,
        // 即使hashCode冲突，o(n)也会降低到 "o(logN)"
        // 前提：key的对象必须正确的实现了Compare接口
        // https://www.cnblogs.com/stevenczp/p/7028071.html

        /*
        * 4. ConcurrentHashMap 集合了 HashMap的读写快、HashTable的线程安全特点。
        *
        * 将锁的粒度减少到 segment，是效率提高的关键。
        *
        * 内存直接分为了16个segment，每个segment实际上还是存储的哈希表，写入的时候，先找到对应的segment，然后锁这个segment，写完，解锁
        *
        * */

    }
}
