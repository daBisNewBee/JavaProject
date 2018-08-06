package sort;

import java.util.Arrays;

/**
 *
 * ϣ������
 *
 * Ҳ��һ�ֲ�������
 *
 * ���ȶ�
 *
 * ���������������������з��顢ÿ��������в������򡢵���������ѭ��������
 *
 * ϣ����������ϣ����������������ã�����һ�����ţ�
 * ѡ������gap=length/2����С����������gap = gap/2�ķ�ʽ
 *
 * �������е�ѡ���Ǹ���ѧ���⣡
 *
 * ��Ԫ�ػ��������ˣ�������С�� ��������������������Ч�ʺܸߡ�
 * ���ԣ�ϣ�������ʱ�临�ӶȻ��O(n^2)��һЩ
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
        // gap Ϊ����
        for (int gap = A.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < A.length; i++) {
                int j = i;
                while ( (j-gap)>=0 && A[j]<A[j-gap]){
                    //����������ý�����
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
