package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 二分查找。
 *
 * 适用于不经常变动而查找频繁的有序列表
 *
 *
 */
public class HalfFind {

    // 返回数组中与target最接近的数字索引
    // TODO：不支持多个结果  time:
    int searchNearlyNums(int[] A, int target){
        Map<Integer, Integer> map = new HashMap<>();
        int[] tmp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            tmp[i] = Math.abs(A[i]-target);
            map.put(tmp[i],i);
        }

        Arrays.sort(tmp);
        System.out.println("tmp = " + tmp.toString());

        return map.get(tmp[0]);

    }

    // 二分查找。 递归方式。要求数组默认有序
    int find(int[] A, int start, int end, int target){

       if (target < A[start] || target > A[end] || start > end)
           return -1;

        int mid = (start+end)/2;

        if (target < A[mid]){

            return find(A, start, mid-1,target);

        }else if (target == A[mid]){

            return mid;

        }else {

            return find(A, mid+1,end, target);

        }
    }

    // 二分查找。循环方式。
    int findbyCircul(int[] A, int target){
        int start = 0;
        int end = A.length-1;

        while (start <= end){
            int mid = (start+end)/2;
            if (target < A[mid]){
                end = mid-1;
            }else if (target > A[mid]){
                start = mid+1;
            }else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,3,5,6,7,8,};
        int target = 5;
        int ret = new HalfFind().searchNearlyNums(A,target);
        System.out.println("ret = " + ret);

        int index = new HalfFind().find(A,0,A.length-1,1);
        System.out.println("index = " + index);

        int index2 = new HalfFind().findbyCircul(A,4);
        System.out.println("index2 = " + index2);
    }
}
