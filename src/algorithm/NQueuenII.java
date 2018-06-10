package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Leetcode : 51 N Queens 讲解：
 *
 * https://www.youtube.com/watch?v=6GnaTKD_Y0I
 *
 *
 * int[] queuen = {0, 1, 0, 3};
 *
 * 数组代表每一行的Q所在列的位置（因为每一行只可能存在一个Q）
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
     * @param res       代表"结果"的集合
     * @param queuen    当前路径：paths
     * @param pos       当前位置：point
     */
    // 核心函数方法！！
    void helper(List<List<String>> res, int[] queuen, int pos){

        //
        if (pos == queuen.length){
            addSolution(res,queuen);
            return;
        }

        // 遍历所有结果
        for (int col = 0; col < queuen.length; col++){
            // push_back
            queuen[pos] = col;
            // 剪枝
            if (isValidPlace(queuen, pos))
                helper(res,queuen,pos+1);
            // pop back。回溯
        }
    }

    boolean isValidPlace(int[] queuen, int pos){

        for (int i = 0; i < pos; i++){

            // 判断是否在同一列
            if (queuen[i] == queuen[pos])
                return false;

            // 判断是否在对角线（如果两点的行之差(绝对值)等于列之差(绝对值) 那两点就在同一对角线上?）
            if (Math.abs(queuen[pos] - queuen[i]) == Math.abs(pos-i))
                return false;
        }

        return true;
    }

    // 添加符号条件的数组到结果
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
