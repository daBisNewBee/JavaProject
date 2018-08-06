package sort;

import java.util.Arrays;

/**
 *
 * ��������("����"�Ķ�����һ����������)
 *
 * ˼�룺ÿһ�ν�һ���������Ԫ�أ����뵽һ���Ѿ��ź����"��������"�У�ֱ������������Ԫ��Ϊֹ
 *
 * ƽ��O(n^2),���O(n),�O(n^2);�ռ临�Ӷ�O(1);�ȶ�;��
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
