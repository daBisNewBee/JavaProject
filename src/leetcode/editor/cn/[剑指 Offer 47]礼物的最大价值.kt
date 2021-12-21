//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？ 
//
// 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物 
//
// 
//
// 提示： 
//
// 
// 0 < grid.length <= 200 
// 0 < grid[0].length <= 200 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 220 👎 0


class P_Offer_47_LiWuDeZuiDaJieZhiLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    // 从row、col位置出发，到达终点，返回的最大价值
    fun way1(grid: Array<IntArray>, row:Int, col:Int, _curMaxValue:Int):Int {

        var curMaxValue = _curMaxValue + grid[row][col]

        var N = grid.size // 行数
        var M = grid[0].size // 列数

        if (row == N - 1 && col == M - 1)
            return curMaxValue

        if (row == N - 1) {
            return way1(grid, row, col+1, curMaxValue)
        }

        if (col == M - 1) {
            return way1(grid, row+1, col, curMaxValue)
        }

        var nextRight = if (col+1 <= M-1) way1(grid, row, col+1, curMaxValue) else curMaxValue
        var nextDown = if (row+1 <= N-1) way1(grid, row+1, col, curMaxValue) else curMaxValue
        return Math.max(nextRight, nextDown)
    }

    private fun dpWay(grid: Array<IntArray>):Int {

        var N = grid.size // 行数

        var M = grid[0].size // 列数

        var dp = Array(N){IntArray(M)}

        dp[0][0] = grid[0][0]
        for (i in 1 until M) {
            dp[0][i] = dp[0][i-1] + grid[0][i]
        }
        for (j in 1 until N) {
            dp[j][0] = dp[j-1][0] + grid[j][0]
        }

        for (i in 1 until N) {
            for (j in 1 until M) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j]
            }
        }

        return dp[N-1][M-1]
    }

    fun maxValue(grid: Array<IntArray>): Int {
        var ans = way1(grid, 0, 0, 0)
        println("暴力递归: $ans")
        ans = dpWay(grid)
        println("动态规划: $ans")
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var array = Array(2) {IntArray(3)}
//    array[0] = intArrayOf(1,3,1)
//    array[1] = intArrayOf(1,5,1)
//    array[2] = intArrayOf(4,2,1)

    array[0] = intArrayOf(1,2,5)
    array[1] = intArrayOf(3,2,1)
    var ans = P_Offer_47_LiWuDeZuiDaJieZhiLcof.Solution().maxValue(array)
    println(ans)
}