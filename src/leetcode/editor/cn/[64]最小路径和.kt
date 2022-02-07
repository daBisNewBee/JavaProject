
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 1128 👎 0


class P_64_MinimumPathSum {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // 空间复杂度 从o(MN)优化成o(N)
        fun dpWay2(array:Array<IntArray>):Int {

            if (array.isEmpty()) return 0

            var M = array.size
            var N = array[0].size

            var rowDp  = IntArray(N)

            rowDp[0] = array[0][0]

            for (i in 1 until N) {
                rowDp[i] = rowDp[i-1] + array[0][i]
            }

            for (i in 1 until M) {
                for (j in 1 until N) {
                    if (j == 1) {
                        rowDp[0] += array[i][0]
                    }
                    rowDp[j] = Math.min(rowDp[j-1], rowDp[j]) + array[i][j]
                }
            }

            return rowDp[N-1]
        }

        fun dpWay(grid: Array<IntArray>):Int {
            val M = grid.size
            var N = grid[0].size

            var dp = Array(M){IntArray(N)}

            dp[0][0] = grid[0][0]

            for (i in 1 until M) {
                dp[i][0] = dp[i-1][0] + grid[i][0]
            }

            for (i in 1 until N) {
                dp[0][i] = dp[0][i-1] + grid[0][i]
            }

            for (i in 1 until M) {
                for (j in 1 until N) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
                }
            }

            return dp[M-1][N-1]
        }

        var minSum = Int.MAX_VALUE

        fun process(grid: Array<IntArray>, row:Int, col:Int, sum:Int) {
            if (row >= grid.size || col >= grid[0].size) return

            var curSum = sum + grid[row][col]
            if (row == grid.size-1 && col == grid[0].size-1) {
                minSum = Math.min(minSum, curSum)
                return
            }

            if (row == grid.size-1) {
                process(grid, row, col+1, curSum)
                return
            }
            if (col == grid[0].size-1) {
                process(grid, row+1, col, curSum)
                return
            }
            process(grid, row+1, col, curSum)
            process(grid, row, col+1, curSum)
        }

    fun minPathSum(grid: Array<IntArray>): Int {
//        process(grid, 0, 0, 0)
//        minSum = dpWay(grid)
        minSum = dpWay2(grid)
        return minSum
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}


fun main() {
    var matrix = Array(3){IntArray(2)}
//    matrix[0] = intArrayOf(1,3,1)
//    matrix[1] = intArrayOf(1,5,1)
//    matrix[2] = intArrayOf(4,2,1)

    matrix[0] = intArrayOf(1,2)
    matrix[1] = intArrayOf(5,6)
    matrix[2] = intArrayOf(1,1)


    println(P_64_MinimumPathSum.Solution().minPathSum(matrix)) // 14
}