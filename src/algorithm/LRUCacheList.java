package algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * "��Ԫ�� LRU ����"
 *
 * ����
 *      �Զ����ѡ�ߣ�ͨ������ж���Ч��һ����
 *
 *      �´��ٴ��ж�ʱ���ɴӵ�һ����ѡ�߿�ʼ�жϣ���Ϊ�ϴε���Ч��ѡ������������ͷ����
 *
 *      �������ظ��жϵ�ʱ�䡣
 *
 * ���ã�
 *      1. λ���б�ͷ��Ԫ����������ʵ�Ԫ�أ�
 *
 *      2. ���ʵĶ�������"set"��"get"��
 *
 *      3. ��Ԫ�صĳ��ȴ���ָ������ʱ�����Զ��������õ�Ԫ�أ�������β����Ԫ�ء�
 *
 * ʱ�临�Ӷȣ�����LinkedList��ͬ��
 *
 *      ��ӣ�O(1)
 *      ��ȡ/���ң�O(N/2)����Ҫ�Ǹ���index����node��ʱ
 *
 *
  ��ʵ�ֵ���Ҫ����

  1. ��ȡԪ�صķ�ʽ��ͬ��
       ����ʵ��Ϊkey-value������index�������key��
       LRUCache Ϊ˫Ԫ�ػ��棬ÿ�α������key����ȡ��Ϊvalue��Ԫ��
       LRUCacheList Ϊ��Ԫ�ػ��棬�ɴ�index = 0����ʼ����

  2. LRUCache ȡ����Ԫ����key��Ӧֵ������������ʵ�ֵ����������ά����˫������δ�������ã�

  3. Ŀ�ģ����ã���ͬ��
       LRUCache Ϊ���ṩ�ڴ�ʹ���ʣ�����IO����ָ����С���ڴ��б�����������Ƶ�������ݣ�����APP������������������IO
       LRUCacheList Ϊ��ÿ��ȡ�����һ�η��ʵ�ֵ��

  4. �ײ����ݽṹ��ͬ��
       "LRUCache"������LinkedHashMap��
       "LRUCacheList" ����LinkedList
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
        *   ���������ʼ����˳�򣬳�ʼ������
        *
        *   cacheList = [0, 1, 2, 3, 4, 5]
        * */
        cacheList.set(Arrays.asList(arrays));
        /*
        *   ע��˴���˳�򣺺�set������λ������head
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
        // ע�⣡ͨ��get����һ���list��˳�����˷�ת
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
                    String.format("������������ȹ���:%d ����:%d �ܼ�����:%d"
                            ,c.size(), cache.size(), cacheSize));
        return cache.addAll(c);
    }

    public synchronized void set(E e){
        // �˴���������"����"��ϵ
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
