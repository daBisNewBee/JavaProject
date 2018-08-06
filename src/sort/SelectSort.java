package sort;

import java.util.Arrays;

/**
 * 选择排序（"选择"的意思是：每次选择一个最小的或最大的元素作为首元素）
 *
 * time: o(n^2)
 *
 * 思想：每一趟从待排序的数据元素中，选择最小（或最大）的一个元素作为首元素，直到所有元素排完为止。
 *
 * 不稳定排序。
 * 原因：
 * 举个例子，序列5 8 5 2 9，我们知道第一遍选择第1个元素5会和2交换，
 * 那么原序列中2个5的相对前后顺序就被破坏了，所以选择排序不是一个稳定的排序算法。
 *
 *
 *
 *
 */
public class SelectSort {


    // 缺点：A[a] + A[b] 过大时，计算结果会溢出
    void swap_leak(int A[], int a, int b){
        A[a] = A[a] + A[b];
        A[b] = A[a] - A[b];
        A[a] = A[a] - A[b];
    }

    /*
    * 原理就是"数字A异或B两次，就得到A。而B被A异或两次，就得到B。"
    int x = 7;
    int y = 8;
    x = x^y; //x被y异或一次
    y = x^y; //此时x存放的是x与y的异或，因此相当于被y又异或一次，x被y异或两次，得到x并赋给y
    x = x^y; //此时x存放的是x与y的异或，而y存放的就是x的值，因此再异或一次y就相当于异或x一次就得到x
    *
    * */
    public void swap(int[] arr, int a, int b) {
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }

    void sort(int[] a){
        int min ;
        for (int i = 0; i < a.length - 1; i++) {
            // 仅保存最小元素的下标，不同时才进行交换
            min = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            if (min != i)
                swap(a, min, i);
        }
    }

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new SelectSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
