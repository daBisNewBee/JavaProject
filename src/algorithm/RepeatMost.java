package algorithm;

import java.util.*;

/**
 *
 * 算法：n个整数，求重复次数最多是那个？
 *
 * 在海量数据中统计出现次数最多的n个
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
        System.out.println("重复最多的数是："+b+" 次数是："+max);
    }

    public static void main(String[] args) {

        Integer[] col = new Integer[10];
        int[] colBasic = new int[10];
        Random random = new Random();

        for (int i = 0; i < col.length; i++) {
            col[i] = random.nextInt(col.length);
            colBasic[i] = random.nextInt(col.length);
        }

        // 方法一： 先排序，再两两比较。复杂度：排序复杂度 + N
        Arrays.sort(colBasic);
        getDisMost(colBasic);

        // 方法二： 笨办法
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
        System.out.println("重复最多的数是：" + max_ele+" 次数是："+max);

    }
}
