package sort;

import java.util.Arrays;

/**
 *
 * 快速排序
 *
 * time： O(N*logN)
 *
 * 思想：冒泡 + 二分 + 递归分治
 *
 * 一种不稳定排序.
 *  原因：当待排序元素类似[6,1,3,7,3]且基准元素为6时，经过分区，
 *       形成[1,3,3,6,7],两个3的相对位置发生了改变。
 *
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] A = { 1, 4, 2, 8, 5, 6, 3, 10, 100, 99, 88};
//        int[] A = new int[]{1, 4, 2, 8, 5};
        QuickSort quickSort = new QuickSort();
//        quickSort.sort(A, 0, A.length-1);
        quickSort.quickSort(A, 0, A.length-1);
        System.out.println("quickSort = " + Arrays.toString(A));;
    }

    // 东拆西补。https://www.cnblogs.com/MOBIN/p/4681369.html
    public static void quickSort(int arr[],int _left,int _right){
        int i = _left;
        int j = _right;
        int temp = 0;
        if(i > j)  //待排序的元素至少有两个的情况
            return;

        temp = arr[i];  //待排序的第一个元素作为基准元素
        while(i != j){   //从左右两边交替扫描，直到left = right

            while(j > i && arr[j] >= temp)
                j --;        //从右往左扫描，找到第一个比基准元素小的元素
            arr[i] = arr[j];  //找到这种元素arr[right]后与arr[left]交换

            while(i < j && arr[i] <= temp)
                i ++;         //从左往右扫描，找到第一个比基准元素大的元素
            arr[j] = arr[i];  //找到这种元素arr[left]后，与arr[right]交换

        }
        arr[j] = temp;    //基准元素归位
        quickSort(arr,_left,i-1);  //对基准元素左边的元素进行递归排序
        quickSort(arr, j+1,_right);  //对基准元素右边的进行递归排序
    }

    @Deprecated
    public void sort(int[] A, int left, int right){
        int index = partrition(A, left, right);
        if (left < index - 1) sort(A, left, index - 1);
        if (right > index) sort(A, index, right);
    }

    // 找出"基准数"，可以是数组的中值
    public int partrition(int[] A, int left, int right){
        int pivot = A[left];
//        int pivot = A[left + (right - left)/2];
//        int pivot = A[left + (left + right)/2]; 错了！

        // 不是 "left < right"？
        while (left <= right){

            while (A[left] < pivot) left++;

            while (A[right] > pivot) right--;

            // 不是 "left < right"？
            if (left <= right){
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
                left++;
                right--;
            }
        }

        // 考虑：为什么返回是 left ？
        return left;
    }


}
