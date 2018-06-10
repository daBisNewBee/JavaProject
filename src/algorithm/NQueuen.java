package algorithm;

/**
 *
 *
 * time: o(n^n)
 *
 * space: o(n)
 *
 * Leetcode : 52 N Queens II 讲解：
 *
 * https://www.youtube.com/watch?v=0uZg0ZkUgP8
 *
 */
public class NQueuen {

    int count = 0;

    void NQueueTotal(int n){

        boolean[] cols = new boolean[n];
        boolean[] diagonal1 = new boolean[ 2 * n];// "/"
        boolean[] diagonal2 = new boolean[ 2 * n];// "\"

        helper(0,cols,diagonal1,diagonal2, n);

        System.out.println("count = " + count+" when N:"+n);

        count = 0;

    }

    public void helper(int row, boolean[] cols, boolean[] dg1, boolean[] dg2, int n){

        if (row == n){
            count++;
            return;
        }

        for (int col = 0;col < n; col++){

            // 根据两个对角线上数字的index规律，凭肉眼看出来
            int id1 = col + row;
            int id2 = col - row + n;

            // 剪枝
            if (cols[col] || dg1[id1] || dg2[id2])  continue;

            cols[col] = true; dg1[id1] = true; dg2[id2] = true;

            helper(row+1,cols,dg1,dg2,n);

            cols[col] = false; dg1[id1] = false; dg2[id2] = false;
        }


    }

    public static void main(String[] args) {

        for (int i = 1; i != 9; i++) {
            new NQueuen().NQueueTotal(i);
        }

    }

}
