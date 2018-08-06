package sort;

import java.util.Arrays;

/**
 *
 * 插入排序("插入"的对象是一个有序序列)
 *
 * 思想：每一次将一个待排序的元素，插入到一个已经排好序的"有序序列"中，直到插入完所有元素为止
 *
 * 平均O(n^2),最好O(n),最坏O(n^2);空间复杂度O(1);稳定;简单
 *
 */
public class InsertionSort {

    public void insertionSort(int[] a){
        int tmp;
        for (int i = 1; i < a.length; i++) {
            for (int j = i;j > 0;j--){
                if ( a[j] < a[j-1] ){
                    tmp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new InsertionSort().insertionSort(a);
        System.out.println("a = " + Arrays.toString(a));
    }
}
