package basic;

public class CollectionTest {
    // Java ���� ��Set , List , Map


	/*
	 * Set , List , Map��
	 * 1. �������ж�����ΪObject
	 * 2. ���ǽӿڣ�����ʵ����
	 *
	 * Set : ����ĳԪ���Ƿ����ڼ��ϣ����󲻿��ظ���������˳��
	 *  + HashSet
	 *  + LinkedHashSet ��Ԫ����ӵ�˳����ʣ�����˳�������˳��һ�£�
	 *  + TreeSet ���ṹ
	 *
	 * Map :
	 *  + HashMap ���ٲ��롢��ѯ������hashCode()
	 *  + HashTable
	 *  + LinkedHashMap �������Ԫ�ص�˳��
	 *  + TreeMap
	 *  + WeakHashMap
	 *  + IdentifyHashMap
	 *
	 * List ����˳��
	 *  + ArrayList : �����������Ԫ��
 	 *  + LinkedList : ���ڲ����ɾ��Ƶ���ĳ��ϣ����� ��ջ�Ͷ���
 	 *  + Vector
	 *
	 * ������
	 * HashMap �� HashTable������
	 * 1. HashMap������һ����null�����Ͷ����null��ֵ; HashTable��key��value������Ϊnull��
	 *    (hashMap����ռ�ֵ����hashTable������)
	 *    ���ʹ��containsKey()���ж��Ƿ����key��������get();
	 * 2. HashMap �ٶȿ죬��ͬ����HashTable�ٶ�����ͬ����
	 * 	     �ڶ��̲߳����Ļ����£�����ֱ��ʹ��Hashtable�� ����Ҫʹ��HashMap�Ļ���Ҫ�Լ�����ͬ�������ˡ�
	 * 3.�̳в�ͬ��
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

        System.out.println("ii = " + Integer.toHexString(ii)); // 248,ʵ��8��
        System.out.println("iii = " + Integer.toHexString(iii)); // 8

    }

}
