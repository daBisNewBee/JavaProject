package algorithm;

import java.util.*;

/**
 *
 * �㷨��n�����������ظ�����������Ǹ���
 *
 * �ں���������ͳ�Ƴ��ִ�������n��
 *
 *
 */
public class RepeatMost {

    static void getDisMost(int[] a){

        int b = a[0], max = 0, tmp = 0;

        for (int i = 1; i< a.length; i++){
            if (a[i-1] == a[i]){
                tmp++;
            }else {
                tmp=1;;
            }

            if (tmp > max){
                max = tmp;
                b = a[i];
            }
        }
        System.out.println("�ظ��������ǣ�"+b+" �����ǣ�"+max);
    }

    public static void main(String[] args) {

        Integer[] col = new Integer[10];
        int[] colBasic = new int[10];
        Random random = new Random();

        for (int i = 0; i < col.length; i++) {
            col[i] = random.nextInt(col.length);
            colBasic[i] = random.nextInt(col.length);
        }

        // ����һ�� �������������Ƚϡ����Ӷȣ������Ӷ� + N
        Arrays.sort(colBasic);
        getDisMost(colBasic);

        // �������� ���취
        Arrays.asList(col).stream().forEach(System.out::println);
//        Arrays.asList(col).stream().forEach(n->System.out.println(n));

        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> repeatMap = new HashMap<>();

        for (Integer one :
                col) {
            boolean addSuc = set.add(one);
            if (!addSuc){
                Integer repeats = repeatMap.get(one);
                if ( repeats == null){
                    repeatMap.put(one, 1 + 1);
                }else {
                    repeatMap.put(one, ++ repeats);
                }
            }
            System.out.println("one = " + one+" result:" + addSuc);
        }
        System.out.println("set size = " + set.size());

        int max = 0;
        int max_ele = 0;
        for (Map.Entry<Integer,Integer> entry : repeatMap.entrySet()){
            if (entry.getValue() > max){
                max = entry.getValue();
                max_ele = entry.getKey();
            }
            System.out.println("entry = " + entry);
        }

//        System.out.println("ret = " + majorityElement(colBasic));;
        System.out.println("�ظ��������ǣ�" + max_ele+" �����ǣ�"+max);

    }
}
