import offer.tree.findPath

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 3, n = 7
//输出：28 
//
// 示例 2： 
//
// 
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
// 
//
// 示例 3： 
//
// 
//输入：m = 7, n = 3
//输出：28
// 
//
// 示例 4： 
//
// 
//输入：m = 3, n = 3
//输出：6 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 109 
// 
// Related Topics 数学 动态规划 组合数学 
// 👍 1235 👎 0


class P_62_UniquePaths {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // 再写一遍：dp[i][j] 表示从原点出发，到[i][j]的路径数量
        fun dpWay2(m: Int, n: Int):Int {
            var dp = Array(m){IntArray(n)}
            for (i in 0 until m) {
                dp[i][0] = 1
            }
            for (i in 0 until n) {
                dp[0][i] = 1
            }
            for (i in 1 until m) {
                for (j in 1 until n) {
                    dp[i][j] = dp[i-1][j]
                    dp[i][j] += dp[i][j-1]
                }
            }
            return dp[m-1][n-1]
        }

        fun dpWay(m:Int, n:Int):Int {

            // dp[i][j] 表示从[i,j]位置出发到终点的路径数量
            var dp = Array(m){IntArray(n)}
            dp[m-1][n-1] = 1

            for (i in 0 until n) {
                dp[m-1][i] = 1
            }

            for (i in 0 until m) {
                dp[i][n-1] = 1
            }

            for (i in m-2 downTo 0) {
                for (j in n-2 downTo 0) {
                    dp[i][j] += dp[i][j+1]
                    dp[i][j] += dp[i+1][j]
                }
            }
            return dp[0][0]
        }

        fun process(m:Int, n: Int, row:Int, col:Int):Int {
            if (row >= m || col >= n) {
                return -1
            }
            if (row == m -1 && col == n - 1) {
                return 1
            }
            var ways = 0
            if (row < m - 1) {
                ways += process(m,n,row+1,col)
            }
            if (col < n - 1) {
                ways += process(m,n,row,col+1)
            }
            return ways
        }

    fun uniquePaths(m: Int, n: Int): Int {
        return dpWay2(m,n)
//        return process(m,n,0,0)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_62_UniquePaths.Solution().uniquePaths(3,2)
    println(ans)
}