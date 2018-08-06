package sort;

import java.util.Arrays;

/**
 *
 * 希尔排序
 *
 * 也是一种插入排序
 *
 * 不稳定
 *
 * 设置增量、根据增量进行分组、每个分组进行插入排序、调整增量、循环。。。
 *
 * 希尔增量：（希尔建议的增量，常用，但不一定最优）
 * 选择增量gap=length/2，缩小增量继续以gap = gap/2的方式
 *
 * 增量序列的选择是个数学难题！
 *
 * 当元素基本有序了，步长很小， 插入排序对于有序的序列效率很高。
 * 所以，希尔排序的时间复杂度会比O(n^2)好一些
 *
 * https://www.cnblogs.com/chengxiao/p/6104371.html
 *
 */
public class ShellSort {

    void swap(int[] A, int a, int b){
        A[a] ^= A[b];
        A[b] = A[a] ^ A[b];
        A[a] = A[a] ^ A[b];
    }

    void sort(int[] A){
        // gap 为增量
        for (int gap = A.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < A.length; i++) {
                int j = i;
                while ( (j-gap)>=0 && A[j]<A[j-gap]){
                    //插入排序采用交换法
                    swap(A,j,j-gap);
                    j-=gap;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new ShellSort().sort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
