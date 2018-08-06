package sort.time;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Leetcode 454. 4Sum II
 *
 * "空间换时间"
 *
 * 思路：
 *
 *  降维：四个数求和 可以转化为 两个数 求和。
 *       合并后一个数组大小是原来的 n? 倍
 *
 *  但时间复杂度下降两个数量级：
 *      n^4 -> n^2 + n^2 ~~ o(n^2)
 *
 *  两个数 求和 为0 可以转化为
 *      第一个数存于 map中；
 *      第二个数 *（-1）判断 是否等于第一个数，即：map中是否存在该key
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
        // 两数和 <---> 累计次数
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
