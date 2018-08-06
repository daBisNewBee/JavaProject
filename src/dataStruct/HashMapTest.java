package dataStruct;

/**
 *
 *
 * 1. Ĭ�ϳ��Ȼ������ݳ���Ϊ 2^n ,Ϊʲô��
 *
 *  �����ڴ�key��Index��hash�㷨��indexFor��
 *  Ϊ�����̶ȼ��ٳ�ͻ��λ���㣨hash���㣩�ķֲ�����
 *
 *
 *
 *
 HashMap�Ĵ�ȡʵ�֣�
 // �洢ʱ:
 int hash = key.hashCode(); // ���hashCode�������ﲻ����,ֻҪ���ÿ��key��hash��һ���̶���intֵ
 int index = hash % Entry[].length;
 Entry[index] = value;

 // ȡֵʱ:
 int hash = key.hashCode();
 int index = hash % Entry[].length;
 return Entry[index];
 *
 *
 *
 * HASH COLLISION DOS ���⣺
 *
 * https://coolshell.cn/articles/6424.html
 *
 */
public class HashMapTest {

    // ȷ��������������������൱��ȡģmod����ȡ��%
    static int indexFor(int h, int length) {
        return h & (length-1);
    }

    // Java �ײ�ʹ�õ�
    static int hash(int h)
    {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public static void main(String[] args) {


        /*
        *
        *  1. Hash Collision��
        *
        *  ��������ġ�
        *
        * */
        String tmp1 = "Aa";
        String tmp2 = "BB";

        int tmp1HashCode = tmp1.hashCode();
        int tmp2HashCode = tmp2.hashCode();
        System.out.println("tmp2HashCode = " + tmp2HashCode);
        System.out.println(tmp1HashCode == tmp2HashCode);

        // 2. ��Ϊhashcode ��ͻ���ʸߣ���ˣ���HashMap��JDK 1.7���е�Ԫ�ض�һ���������У�
        // o(1)�ĸ��ӶȽ�Ϊ o(n),����ΪN��ʱ�����ܽ�Ϊo(n^2).
        // JDK 1.7 HashMap: https://blog.csdn.net/hxpjava1/article/details/55670439

        // 3. ��ˣ�Ϊ�˸��Ƽ��������o(n)�������
        // JDK1.8���˸Ľ���
        // ͬһ��bucket�е� key <= 8 ��ʱ��ʹ������ṹ�洢
        // key > 8 ��ʱ�������treeifyBin������������ת��Ϊ����� ,
        // ��ʹhashCode��ͻ��o(n)Ҳ�ή�͵� "o(logN)"
        // ǰ�᣺key�Ķ��������ȷ��ʵ����Compare�ӿ�
        // https://www.cnblogs.com/stevenczp/p/7028071.html

        /*
        * 4. ConcurrentHashMap ������ HashMap�Ķ�д�졢HashTable���̰߳�ȫ�ص㡣
        *
        * ���������ȼ��ٵ� segment����Ч����ߵĹؼ���
        *
        * �ڴ�ֱ�ӷ�Ϊ��16��segment��ÿ��segmentʵ���ϻ��Ǵ洢�Ĺ�ϣ��д���ʱ�����ҵ���Ӧ��segment��Ȼ�������segment��д�꣬����
        *
        * */

    }
}
