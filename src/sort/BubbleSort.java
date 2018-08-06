package sort;

import java.util.Arrays;

/**
 *
 *  冒泡排序：
 *
 *  time: O(n^2)
 *
 *  原理是临近的数字两两进行比较,按照从小到大或者从大到小的顺序进行交换,

    这样一趟过去后,最大或最小的数字被交换到了最后一位,
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new BubbleSort().sort2(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    // 两两比较
    public void sort2(int[] a){
        int tmp;
        for (int i = 0; i < a.length - 1; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，
            // 也就是待排序列已经有序，排序已然完成。
            boolean flag = true;
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j-1]){
                    tmp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = tmp;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }

    // 想想该实现是否标准冒泡？为何不是？
    public void sort(int[] a){
        int tmp;
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i] > a[j]){
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    
    }
}
