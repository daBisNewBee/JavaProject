package algorithm;

import com.sun.tools.javac.util.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 *  https://www.youtube.com/watch?v=8j4nHj92uRA&t=63s
 *
 *  Leetcode : 146. LRU Cache
 *
 * 要求： time: o（1）
 *
 * 采用的数据结构：
 *      hashMap + 双向列表的方式
 *
 *  典型的具有基本的 set 、 get 操作
 *
 *  考虑，为何使用双向链表，不是单向链表？
 *      方便删除node时，对前节点的查找并进行操作。（单向列表只能找到后节点的指针，找不到前节点）
 *
 *      相关：单链表的删除，在链表中删除值为x的元素。此时time：o(n)
             LinkedList LinkedListDelete(LinkedList L,ElemType x)
             {
                 Node *p,*pre;                   //pre为前驱结点，p为查找的结点。
                 p = L->next;
                 while(p->data != x)              //查找值为x的元素
                     {
                         pre = p;
                         p = p->next;
                     }
                 pre->next = p->next;          //删除操作，将其前驱next指向其后继。
                 free(p);
                 return L;
             }
 *
 * 相关：
 *  双向链表
 *      主要优点：
 *          1. 是对于任意给的结点，都可以很轻易的获取其前结点和后结点，
 *          2. 优点，增加删除，用时间很短
 *      主要缺点：
 *          1. 是每个结点需要保存next和prev两个属性，因此需要更多的空间开销，
 *
 *
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> {

    private Map<K,Node> cache;

    private Node<K,V> head,tail;

    private final int cacheSize;

    public LRUCache(int _cacheSize) {
        cache = new HashMap<>();
        head = new Node<>();
        tail = new Node<>();
        cacheSize = _cacheSize;
        // 双向链表，在初始化时，就建立头、尾的关系
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
    }

    public static void main(String[] args) {

        final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
        final float DEFAULT_LOAD_FACTOR = 0.75f;
        boolean accessOrder = true;
        Map<String,String> linkedMap = new LinkedHashMap<String,String>(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR,accessOrder){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return super.removeEldestEntry(eldest);
            }
        };
        linkedMap.put("1","11111");
        linkedMap.put("2","22222");
        linkedMap.put("3","33333");
//        linkedMap.remove("2");
        linkedMap.put("4","44444");
        linkedMap.forEach((x,y)->{
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        });

        linkedMap.get("3");
        System.out.println("================ after get 3");
        linkedMap.forEach((x,y)->{
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        });

        if (true)
            return;

        LRUCache testCache  = new LRUCache(4);
        testCache.set("1","11111");
        testCache.set("2","22222");
        testCache.set("3","33333");
        testCache.printList(testCache.head);
        testCache.get("1");
        System.out.println("====== do get 1 ");
        testCache.printList(testCache.head);
        System.out.println("====== end do get 1 ");
        testCache.set("4","44444");
        testCache.printList(testCache.head);
        System.out.println("LRUCache.main =========== ");
        testCache.set("5","55555");
        testCache.printList(testCache.head);
        testCache.get("3");
        System.out.println("====== do get 3 ");
        testCache.printList(testCache.head);
        System.out.println("====== end do get 3 ");
        Assert.checkNull(testCache.get("2"));
    }

    public void printList(Node node){
        if (node.next != null){
            if (node.key != null && node.value != null){
                System.out.println("node = " + node);
            }
            if (node.next != null){
                printList(node.next);
            }
        }
    }

    public void set(K key, V value){
        if (cache.containsKey(key)){
            Node oldNode = cache.get(key);
            Node newNode = new Node(key, value);
            cache.put(key,newNode);
            moveNodeHead(oldNode);
        }else {
            Node node = new Node(key, value);
            if (cache.size() >= cacheSize){
                removeLast();
            }
            setNodeHead(node);
            cache.put(key, node);
        }
    }

    private void moveNodeHead(Node node){
        removeNode(node);
        setNodeHead(node);
    }

    private void removeLast() {
            cache.remove(tail.pre.key);
            removeNode(tail.pre);
    }

    private void setNodeHead(Node newNode) {
        newNode.next = head.next;
        newNode.pre = head;
        head.next.pre = newNode;
        head.next = newNode;
        /*
        * 这里容易出错！！
        *
        * 注意顺序不要颠倒！！
        *
        * 比如：会造成前指针指向自己！！
        *   head.next = newNode;
            head.next.pre = newNode;
        *
        * 要把原来的指针先交出去，最后才能赋值。否则会丢失原来对象
        *
        * */
    }

    private void removeNode(Node oldNode) {
        oldNode.pre.next = oldNode.next;
        oldNode.next.pre = oldNode.pre;
        // 实际可不需要置空。有GC。这里只为加快回收速度
//        oldNode.pre = null;
//        oldNode.next = null;
    }

    public V get(K key){
        if (cache.containsKey(key)){
            Node<K,V> node = cache.get(key);
            moveNodeHead(node);
            return node.value;
        }else {
            return null;
        }
    }

    static class Node<K,V>{

        K key;

        V value;

        Node pre;

        Node next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("key:%s value:%s",key, value);
        }
    }

}
