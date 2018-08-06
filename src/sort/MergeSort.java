package sort;

import java.util.Arrays;

/**
 *
 * 归并排序（MERGE-SORT）
 *
 * 过程包括了： 分 + 治
 *
 * 稳定排序
 *
 * time： 分：logN  合并：N  总计：
 *
 * 是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
 * （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的
 * 阶段得到的各答案"修补"在一起，即分而治之)。
 *
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 *
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        int []arr = {9,8,7,6,5,4,3,2,1};
        new MergeSort().sort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    public void sort(int[] a){
        int[] tmp = new int[a.length];
        sort(a, 0, a.length-1, tmp);
    }

    public void sort(int[] a, int left, int right, int[] tmp){
        if (left < right){
            // 递归深度为log2n
            int mid = (left + right)/2;
            sort(a, left, mid, tmp);
            sort(a, mid+1, right, tmp);
            merge(a, left, mid, right, tmp);
        }
    }

    public void merge(int[] a, int left, int mid, int right, int[] tmp){

        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j<= right) {

            if (a[i] <= a[j]) {
                tmp[t++] = a[i++];
            } else {
                tmp[t++] = a[j++];
            }
        }

            while (i <= mid)
                tmp[t++] = a[i++];

            while (j <= right)
                tmp[t++] = a[j++];

            t = 0;

            while (left<=right)
                a[left++] = tmp[t++];
        }
}
