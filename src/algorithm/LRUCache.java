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
 * Ҫ�� time: o��1��
 *
 * ���õ����ݽṹ��
 *      hashMap + ˫���б�ķ�ʽ
 *
 *  ���͵ľ��л����� set �� get ����
 *
 *  ���ǣ�Ϊ��ʹ��˫���������ǵ�������
 *      ����ɾ��nodeʱ����ǰ�ڵ�Ĳ��Ҳ����в������������б�ֻ���ҵ���ڵ��ָ�룬�Ҳ���ǰ�ڵ㣩
 *
 *      ��أ��������ɾ������������ɾ��ֵΪx��Ԫ�ء���ʱtime��o(n)
             LinkedList LinkedListDelete(LinkedList L,ElemType x)
             {
                 Node *p,*pre;                   //preΪǰ����㣬pΪ���ҵĽ�㡣
                 p = L->next;
                 while(p->data != x)              //����ֵΪx��Ԫ��
                     {
                         pre = p;
                         p = p->next;
                     }
                 pre->next = p->next;          //ɾ������������ǰ��nextָ�����̡�
                 free(p);
                 return L;
             }
 *
 * ��أ�
 *  ˫������
 *      ��Ҫ�ŵ㣺
 *          1. �Ƕ���������Ľ�㣬�����Ժ����׵Ļ�ȡ��ǰ���ͺ��㣬
 *          2. �ŵ㣬����ɾ������ʱ��ܶ�
 *      ��Ҫȱ�㣺
 *          1. ��ÿ�������Ҫ����next��prev�������ԣ������Ҫ����Ŀռ俪����
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
        // ˫�������ڳ�ʼ��ʱ���ͽ���ͷ��β�Ĺ�ϵ
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
        * �������׳�����
        *
        * ע��˳��Ҫ�ߵ�����
        *
        * ���磺�����ǰָ��ָ���Լ�����
        *   head.next = newNode;
            head.next.pre = newNode;
        *
        * Ҫ��ԭ����ָ���Ƚ���ȥ�������ܸ�ֵ������ᶪʧԭ������
        *
        * */
    }

    private void removeNode(Node oldNode) {
        oldNode.pre.next = oldNode.next;
        oldNode.next.pre = oldNode.pre;
        // ʵ�ʿɲ���Ҫ�ÿա���GC������ֻΪ�ӿ�����ٶ�
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
