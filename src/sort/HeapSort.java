package sort;

import java.util.Arrays;

/**
 *
 * 堆排序
 *
 *  一种选择排序，
 *
 *  思想：构建初始堆(o(n))、交换堆顶元素和末尾元素(o(n-1))、重建堆(log(n-1))
 *
 * time: O(nlogn)
 *
 *  https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 */
public class HeapSort {

    void sort(int[] A){
        for (int i = A.length/2 - 1; i >= 0; i--) {
            // 1. 构建初始堆(最大堆)。从最后一个非叶子节点（注意其标识）开始
            adjustHeap(A, i, A.length);
        }

        for (int j = A.length - 1 ; j > 0; j--) {
            // 2. 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
            swap(A, 0, j);
            // 3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，
            // 反复执行调整+交换步骤，直到整个序列有序。
            adjustHeap(A, 0, j);
        }
    }

    // 建立大顶堆。从第i个位置，adjust的结果为：第i个元素及其子节点的元素为最大值
    void adjustHeap(int[] A, int i, int length){
        int tmp = A[i];
        for (int j = 2*i+1; j < length; j = 2*j +1) {
            if (j+1 < length && A[j] < A[j+1])
                j++;

            if (A[j] > tmp){
//            if (A[j] > A[i]){
                A[i] = A[j];
                i = j;
            }else {
                break;
            }
        }
        A[i] = tmp;
    }

    void swap(int[] A, int a, int b){
        A[a] ^= A[b];
        A[b] = A[a] ^ A[b];
        A[a] = A[a] ^ A[b];
    }

    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new HeapSort().sort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
