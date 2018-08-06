package sort;

import java.util.Arrays;

/**
 * ѡ������"ѡ��"����˼�ǣ�ÿ��ѡ��һ����С�Ļ�����Ԫ����Ϊ��Ԫ�أ�
 *
 * time: o(n^2)
 *
 * ˼�룺ÿһ�˴Ӵ����������Ԫ���У�ѡ����С������󣩵�һ��Ԫ����Ϊ��Ԫ�أ�ֱ������Ԫ������Ϊֹ��
 *
 * ���ȶ�����
 * ԭ��
 * �ٸ����ӣ�����5 8 5 2 9������֪����һ��ѡ���1��Ԫ��5���2������
 * ��ôԭ������2��5�����ǰ��˳��ͱ��ƻ��ˣ�����ѡ��������һ���ȶ��������㷨��
 *
 *
 *
 *
 */
public class SelectSort {


    // ȱ�㣺A[a] + A[b] ����ʱ�������������
    void swap_leak(int A[], int a, int b){
        A[a] = A[a] + A[b];
        A[b] = A[a] - A[b];
        A[a] = A[a] - A[b];
    }

    /*
    * ԭ�����"����A���B���Σ��͵õ�A����B��A������Σ��͵õ�B��"
    int x = 7;
    int y = 8;
    x = x^y; //x��y���һ��
    y = x^y; //��ʱx��ŵ���x��y���������൱�ڱ�y�����һ�Σ�x��y������Σ��õ�x������y
    x = x^y; //��ʱx��ŵ���x��y����򣬶�y��ŵľ���x��ֵ����������һ��y���൱�����xһ�ξ͵õ�x
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
            // ��������СԪ�ص��±꣬��ͬʱ�Ž��н���
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
