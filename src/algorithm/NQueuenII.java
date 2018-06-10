package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Leetcode : 51 N Queens ���⣺
 *
 * https://www.youtube.com/watch?v=6GnaTKD_Y0I
 *
 *
 * int[] queuen = {0, 1, 0, 3};
 *
 * �������ÿһ�е�Q�����е�λ�ã���Ϊÿһ��ֻ���ܴ���һ��Q��
 *
     .Q..
     ...Q
     Q...
     ..Q.

     ..Q.
     Q...
     ...Q
     .Q..
 *
 *
 */
public class NQueuenII {

    List<List<String>> solveNQueuens(int n){

        List<List<String>> res = new ArrayList<>();

        if (n <= 0) return res;

        int[] queuen = new int[n];
        helper(res,queuen,0);
        return res;
    }

    /**
     *
     *
     * @param res       ����"���"�ļ���
     * @param queuen    ��ǰ·����paths
     * @param pos       ��ǰλ�ã�point
     */
    // ���ĺ�����������
    void helper(List<List<String>> res, int[] queuen, int pos){

        //
        if (pos == queuen.length){
            addSolution(res,queuen);
            return;
        }

        // �������н��
        for (int col = 0; col < queuen.length; col++){
            // push_back
            queuen[pos] = col;
            // ��֦
            if (isValidPlace(queuen, pos))
                helper(res,queuen,pos+1);
            // pop back������
        }
    }

    boolean isValidPlace(int[] queuen, int pos){

        for (int i = 0; i < pos; i++){

            // �ж��Ƿ���ͬһ��
            if (queuen[i] == queuen[pos])
                return false;

            // �ж��Ƿ��ڶԽ��ߣ�����������֮��(����ֵ)������֮��(����ֵ) ���������ͬһ�Խ�����?��
            if (Math.abs(queuen[pos] - queuen[i]) == Math.abs(pos-i))
                return false;
        }

        return true;
    }

    // ��ӷ������������鵽���
    void addSolution(List<List<String>> res, int[] queuen){
        List<String> list = new ArrayList<>();

        for (int i = 0; i < queuen.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < queuen.length; j++) {
                if (queuen[i] == j)
                    sb.append("Q");
                else
                    sb.append(".");
            }
            sb.append("\n");
            list.add(sb.toString());
        }

        res.add(list);
    }



    public static void main(String[] args) {

        List<List<String>> list = new NQueuenII().solveNQueuens(4);

        list.forEach(System.out::println);
    }
}
