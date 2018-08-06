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
        int left = _left;
        int right = _right;
        int temp = 0;
        if(left <= right){   //�������Ԫ�����������������
            temp = arr[left];  //������ĵ�һ��Ԫ����Ϊ��׼Ԫ��
            while(left != right){   //���������߽���ɨ�裬ֱ��left = right

                while(right > left && arr[right] >= temp)
                    right --;        //��������ɨ�裬�ҵ���һ���Ȼ�׼Ԫ��С��Ԫ��
                arr[left] = arr[right];  //�ҵ�����Ԫ��arr[right]����arr[left]����

                while(left < right && arr[left] <= temp)
                    left ++;         //��������ɨ�裬�ҵ���һ���Ȼ�׼Ԫ�ش��Ԫ��
                arr[right] = arr[left];  //�ҵ�����Ԫ��arr[left]����arr[right]����

            }
            arr[right] = temp;    //��׼Ԫ�ع�λ
            quickSort(arr,_left,left-1);  //�Ի�׼Ԫ����ߵ�Ԫ�ؽ��еݹ�����
            quickSort(arr, right+1,_right);  //�Ի�׼Ԫ���ұߵĽ��еݹ�����
        }
    }

    public void sort(int[] A, int left, int right){
        int index = partrition(A, left, right);
        if (left < index - 1) sort(A, left, index - 1);
        if (right > index) sort(A, index, right);
    }

    // �ҳ�"��׼��"���������������ֵ
    public int partrition(int[] A, int left, int right){
        int pivot = A[left + (right - left)/2];
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
