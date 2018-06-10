package algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * "单元素 LRU 链表"
 *
 * 需求：
 *      对多个候选者，通过检测判断有效的一个；
 *
 *      下次再次判断时，可从第一个候选者开始判断，因为上次的有效候选者已移至链表头部，
 *
 *      减少了重复判断的时间。
 *
 * 作用：
 *      1. 位于列表头的元素是最近访问的元素；
 *
 *      2. 访问的动作包括"set"、"get"；
 *
 *      3. 当元素的长度大于指定长度时，会自动清除最不常用的元素，即链表尾部的元素。
 *
 * 时间复杂度：（与LinkedList相同）
 *
 *      添加：O(1)
 *      获取/查找：O(N/2)。主要是根据index查找node耗时
 *
 *
  与实现的主要区别：

  1. 获取元素的方式不同。
       （其实都为key-value，不过index是特殊的key）
       LRUCache 为双元素缓存，每次必须根据key才能取到为value的元素
       LRUCacheList 为单元素缓存，可从index = 0处开始遍历

  2. LRUCache 取到的元素是key对应值，不是最近访问的值（即：其中维护的双向链表未发挥作用）

  3. 目的（作用）不同。
       LRUCache 为了提供内存使用率，减少IO。在指定大小的内存中保留被访问最频繁的数据，减少APP从网络或者物理磁盘中IO
       LRUCacheList 为了每次取到最近一次访问的值；

  4. 底层数据结构不同。
       "LRUCache"，基于LinkedHashMap；
       "LRUCacheList" 基于LinkedList
 *
 *
 *
 * @param <E>
 */
public class LRUCacheList<E> {

    private LinkedList<E> cache;

    private int cacheSize;

    private static final int DEFAULT_CACHE_ELEM_SIZE = 1000;

    public LRUCacheList(int _cacheSize) {
        cache = new LinkedList<E>();
        cacheSize = _cacheSize;
    }

    public LRUCacheList(){
        this(DEFAULT_CACHE_ELEM_SIZE);
    }

    public static void main(String[] args) {


        LRUCacheList<String> cacheList = new LRUCacheList<>(6);

        String[] arrays = new String[]{"0","1","2","3","4","5"};

        /*
        *   按照数组初始化的顺序，初始到链表
        *
        *   cacheList = [0, 1, 2, 3, 4, 5]
        * */
        cacheList.set(Arrays.asList(arrays));
        /*
        *   注意此处有顺序：后set的数据位于链表head
        *
        *   cacheList.set("0");
            cacheList.set("1");
            cacheList.set("2");
            cacheList.set("3");
            cacheList.set("4");
            cacheList.set("5");
            cacheList = [5, 4, 3, 2, 1, 0]
        * */
        System.out.println("==========");
        System.out.println("cacheList = " + cacheList);
        System.out.println("==========");

        String value = cacheList.get(1);
        System.out.println("get 1 value = " + value);

        System.out.println("cacheList = " + cacheList);

        cacheList.set("6");

        System.out.println("after set 6");
        System.out.println("cacheList = " + cacheList);

        int size = cacheList.size();
        System.out.println("size = " + size);
        // 注意！通过get遍历一遍后，list的顺序发生了反转
        for (int i = 0; i < size; i++) {
            System.out.println("i = " + cacheList.get(i));
        }

        System.out.println("cacheList = " + cacheList);
        String removed = cacheList.remove(0);
        System.out.println("removed 0 = " + removed);

        System.out.println("cacheList = " + cacheList);
    }

    public synchronized boolean set(Collection<? extends E> c){
        if ((c.size() + cache.size()) > cacheSize)
            throw new IllegalArgumentException(
                    String.format("输入的容器长度过大:%d 已用:%d 总计限制:%d"
                            ,c.size(), cache.size(), cacheSize));
        return cache.addAll(c);
    }

    public synchronized void set(E e){
        // 此处不可能是"大于"关系
        if (cache.size() == cacheSize)
            cache.removeLast();

        cache.addFirst(e);
    }

    public synchronized E get(int index){
        E e = cache.get(index);
        cache.remove(index);
        cache.addFirst(e);
        return e;
    }

    public synchronized E remove(int index){
        return cache.remove(index);
    }

    public synchronized int size(){
        return cache.size();
    }

    @Override
    public String toString() {
        return cache.toString();
    }
}
