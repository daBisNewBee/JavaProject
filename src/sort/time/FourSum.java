package sort.time;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Leetcode 454. 4Sum II
 *
 * "�ռ任ʱ��"
 *
 * ˼·��
 *
 *  ��ά���ĸ������ ����ת��Ϊ ������ ��͡�
 *       �ϲ���һ�������С��ԭ���� n? ��
 *
 *  ��ʱ�临�Ӷ��½�������������
 *      n^4 -> n^2 + n^2 ~~ o(n^2)
 *
 *  ������ ��� Ϊ0 ����ת��Ϊ
 *      ��һ�������� map�У�
 *      �ڶ����� *��-1���ж� �Ƿ���ڵ�һ����������map���Ƿ���ڸ�key
 *
 *   Example:
     Input:
     A = [ 1, 2]
     B = [-2,-1]
     C = [-1, 2]
     D = [ 0, 2]
     Output:
     2
     Explanation:
     The two tuples are:
     1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 */
public class FourSum {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // ������ <---> �ۼƴ���
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<C.length; i++) {
            for(int j=0; j<D.length; j++) {
                int sum = C[i] + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        System.out.println("map = " + map);

        // map = {-1=1, 1=1, 2=1, 4=1}

        int res=0;
        for(int i=0; i<A.length; i++) {
            for(int j=0; j<B.length; j++) {
                res += map.getOrDefault(-1 * (A[i]+B[j]), 0);
            }
        }

        return res;
    }

    void backStreet(){

    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,8};


        int[] A = new int[]{1,2};
        int[] B = new int[]{-2,-1};
        int[] C = new int[]{-1,2};
        int[] D = new int[]{0,2};
        int ret = new FourSum().fourSumCount(A,B,C,D);
        System.out.println("ret = " + ret);
    }
}
