package sort;

import java.util.Arrays;

/**
 *
 *  ð������
 *
 *  time: O(n^2)
 *
 *  ԭ�����ٽ��������������бȽ�,���մ�С������ߴӴ�С��˳����н���,

    ����һ�˹�ȥ��,������С�����ֱ������������һλ,
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new BubbleSort().sort2(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    // �����Ƚ�
    public void sort2(int[] a){
        int tmp;
        for (int i = 0; i < a.length - 1; i++) {
            // �趨һ����ǣ���Ϊtrue�����ʾ�˴�ѭ��û�н��н�����
            // Ҳ���Ǵ��������Ѿ�����������Ȼ��ɡ�
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

    // �����ʵ���Ƿ��׼ð�ݣ�Ϊ�β��ǣ�
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
