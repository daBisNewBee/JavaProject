//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X"
//,"X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [["X"]]
//输出：[["X"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 707 👎 0


class P_130_SurroundedRegions {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 由于"边界上的O不会被填充X"，那隔壁的'O'肯定也是不行的。所以从边界开始寻找'O'并标记
         *
         */
        fun dfs(board: Array<CharArray>, i:Int, j:Int) {

            if (i < 0 || i >= board.size || j < 0
                    || j >= board[0].size || board[i][j] != 'O') return

            board[i][j] = 'A'
            dfs(board, i-1, j)
            dfs(board, i+1, j)
            dfs(board, i, j-1)
            dfs(board, i, j+1)
        }

    fun solve(board: Array<CharArray>): Unit {
        var n = board.size
        if (n == 0) return

        var m = board[0].size
        for (i in 0 until n) {
            dfs(board, i, 0)
            dfs(board, i, m-1)
        }

        for (i in 1 until m-1) {
            dfs(board, 0, i)
            dfs(board, n-1, i)
        }

        for (i in board.indices) {
            for (j in board[0].indices) {
                if ('A' == board[i][j]) {
                    board[i][j] = 'O'
                } else if ('O' == board[i][j]) {
                    // 其余的'O'符合条件，修改为'X'
                    board[i][j] = 'X'
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}