package sort;

import java.util.Arrays;

/**
 *
 * �鲢����MERGE-SORT��
 *
 * ���̰����ˣ� �� + ��
 *
 * �ȶ�����
 *
 * time�� �֣�logN  �ϲ���N  �ܼƣ�
 *
 * �����ù鲢��˼��ʵ�ֵ����򷽷������㷨���þ���ķ��Σ�divide-and-conquer������
 * �����η��������(divide)��һЩС������Ȼ��ݹ���⣬����(conquer)�Ľ׶��򽫷ֵ�
 * �׶εõ��ĸ���"�޲�"��һ�𣬼��ֶ���֮)��
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
            // �ݹ����Ϊlog2n
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
