package basic;

public class CollectionTest {
    // Java 集合 ：Set , List , Map


	/*
	 * Set , List , Map：
	 * 1. 都将持有对象视为Object
	 * 2. 都是接口，不能实例化
	 *
	 * Set : 关心某元素是否属于集合，对象不可重复，对象无顺序
	 *  + HashSet
	 *  + LinkedHashSet 以元素添加的顺序访问（迭代顺序与插入顺序一致）
	 *  + TreeSet 树结构
	 *
	 * Map :
	 *  + HashMap 快速插入、查询，基于hashCode()
	 *  + HashTable
	 *  + LinkedHashMap 关心添加元素的顺序
	 *  + TreeMap
	 *  + WeakHashMap
	 *  + IdentifyHashMap
	 *
	 * List ：有顺序
	 *  + ArrayList : 用于随机访问元素
 	 *  + LinkedList : 用于插入和删除频繁的场合，比如 堆栈和队列
 	 *  + Vector
	 *
	 * 其他：
	 * HashMap 和 HashTable的区别：
	 * 1. HashMap可以有一个“null”键和多个“null”值; HashTable的key和value都不可为null。
	 *    (hashMap允许空键值，而hashTable不允许)
	 *    因此使用containsKey()来判断是否存在key，而不用get();
	 * 2. HashMap 速度快，非同步；HashTable速度慢，同步。
	 * 	     在多线程并发的环境下，可以直接使用Hashtable， 但是要使用HashMap的话就要自己增加同步处理了。
	 * 3.继承不同：
	 *   public class Hashtable extends Dictionary implements Map
		 public class HashMap  extends AbstractMap implements Map
	 *
	 *
	 *
	 * */

    public static void main(String[] args) {
        int i = 0x80;
        int ii = i >> 4;
        int iii = i >>> 4;

        System.out.println("i = " + Integer.toHexString(i));

        System.out.println("ii = " + Integer.toHexString(ii)); // 248,实际8？
        System.out.println("iii = " + Integer.toHexString(iii)); // 8

    }

}
