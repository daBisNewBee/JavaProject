package sort;

import java.util.Arrays;

/**
 *
 * ��������
 *
 * time�� O(N*logN)
 *
 * ˼�룺ð�� + ���� + �ݹ����
 *
 * һ�ֲ��ȶ�����.
 *  ԭ�򣺵�������Ԫ������[6,1,3,7,3]�һ�׼Ԫ��Ϊ6ʱ������������
 *       �γ�[1,3,3,6,7],����3�����λ�÷����˸ı䡣
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

    // ����������https://www.cnblogs.com/MOBIN/p/4681369.html
    public static void quickSort(int arr[],int _left,int _right){
        int i = _left;
        int j = _right;
        int temp = 0;
        if(i > j)  //�������Ԫ�����������������
            return;

        temp = arr[i];  //������ĵ�һ��Ԫ����Ϊ��׼Ԫ��
        while(i != j){   //���������߽���ɨ�裬ֱ��left = right

            while(j > i && arr[j] >= temp)
                j --;        //��������ɨ�裬�ҵ���һ���Ȼ�׼Ԫ��С��Ԫ��
            arr[i] = arr[j];  //�ҵ�����Ԫ��arr[right]����arr[left]����

            while(i < j && arr[i] <= temp)
                i ++;         //��������ɨ�裬�ҵ���һ���Ȼ�׼Ԫ�ش��Ԫ��
            arr[j] = arr[i];  //�ҵ�����Ԫ��arr[left]����arr[right]����

        }
        arr[j] = temp;    //��׼Ԫ�ع�λ
        quickSort(arr,_left,i-1);  //�Ի�׼Ԫ����ߵ�Ԫ�ؽ��еݹ�����
        quickSort(arr, j+1,_right);  //�Ի�׼Ԫ���ұߵĽ��еݹ�����
    }

    @Deprecated
    public void sort(int[] A, int left, int right){
        int index = partrition(A, left, right);
        if (left < index - 1) sort(A, left, index - 1);
        if (right > index) sort(A, index, right);
    }

    // �ҳ�"��׼��"���������������ֵ
    public int partrition(int[] A, int left, int right){
        int pivot = A[left];
//        int pivot = A[left + (right - left)/2];
//        int pivot = A[left + (left + right)/2]; ���ˣ�

        // ���� "left < right"��
        while (left <= right){

            while (A[left] < pivot) left++;

            while (A[right] > pivot) right--;

            // ���� "left < right"��
            if (left <= right){
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
                left++;
                right--;
            }
        }

        // ���ǣ�Ϊʲô������ left ��
        return left;
    }


}
