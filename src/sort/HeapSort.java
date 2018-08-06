package sort;

import java.util.Arrays;

/**
 *
 * ������
 *
 *  һ��ѡ������
 *
 *  ˼�룺������ʼ��(o(n))�������Ѷ�Ԫ�غ�ĩβԪ��(o(n-1))���ؽ���(log(n-1))
 *
 * time: O(nlogn)
 *
 *  https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 */
public class HeapSort {

    void sort(int[] A){
        for (int i = A.length/2 - 1; i >= 0; i--) {
            // 1. ������ʼ��(����)�������һ����Ҷ�ӽڵ㣨ע�����ʶ����ʼ
            adjustHeap(A, i, A.length);
        }

        for (int j = A.length - 1 ; j > 0; j--) {
            // 2. ���Ѷ�Ԫ����ĩβԪ�ؽ����������Ԫ��"��"������ĩ��;
            swap(A, 0, j);
            // 3. ���µ����ṹ��ʹ������Ѷ��壬Ȼ����������Ѷ�Ԫ���뵱ǰĩβԪ�أ�
            // ����ִ�е���+�������裬ֱ��������������
            adjustHeap(A, 0, j);
        }
    }

    // �����󶥶ѡ��ӵ�i��λ�ã�adjust�Ľ��Ϊ����i��Ԫ�ؼ����ӽڵ��Ԫ��Ϊ���ֵ
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
