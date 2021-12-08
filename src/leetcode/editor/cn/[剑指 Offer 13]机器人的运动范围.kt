import java.util.*

//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 
// 👍 394 👎 0


class P_Offer_13_JiQiRenDeYunDongFanWeiLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 矩阵搜索问题，用深度优先比较直观！推荐！
         *
         * 模式识别：搜索问题可以用深度优先或者广度优先
         *
         * 深度优先dfs 就是 递归
         *
         * 广度优先bfs 就是 队列
         *
         * 递归的几个要点：
         *
         * 1. 拆解成子问题
         *
         * 2. 保存状态，用于剪枝
         *
         * 此题优化点：
         * 其实有效搜索只是向右、向下，因此可以减小无用子问题，减少问题规模
         *
         */
    fun getSum(index:Int):Int {
        var sum = index % 10
        var tmp = index / 10
        while (tmp > 0) {
          sum += tmp % 10
          tmp /= 10
        }
        return sum
    }

    fun dfs(i:Int, j:Int, m:Int, n:Int, k:Int, visited:Array<BooleanArray>):Int {
        if (i < 0 || j < 0 ||
                i >= m || j >= n ||
                visited[i][j] ||
                getSum(i) + getSum(j) > k) {
            // 剪枝
            return 0
        }
        // 记录状态
        visited[i][j] = true
        // 拆解子问题，子问题求和
        return 1 +
//                dfs(i-1, j, m, n, k, visited) +
                dfs(i+1, j, m, n, k, visited) +
//                dfs(i, j-1, m, n, k, visited) +
                dfs(i, j+1, m, n, k, visited)
    }

        /**
         *
         * 广度优先搜索。作为参考，也是个思路
         *
         */
        fun bfs(m: Int, n: Int, k: Int, visited:Array<BooleanArray>):Int {
            if (k == 0) return 1

            // 向右和向下的方向数组
            var dx = intArrayOf(0,1)

            var dy = intArrayOf(1,0)

            var queue = LinkedList<IntArray>()

            queue.offer(intArrayOf(0,0))

            var ans = 1

            while (queue.isNotEmpty()) {
                var node = queue.poll()
                for (i in 0 until 2) {
                    var x = node[0] + dx[i]
                    var y = node[1] + dy[i]
                    if (x >= m || y >= n || getSum(x) + getSum(y) > k || visited[x][y]) {
                        continue
                    }
                    visited[x][y] = true
                    ans++
                    queue.offer(intArrayOf(x,y))
                }
            }
            return ans
        }

    fun movingCount(m: Int, n: Int, k: Int): Int {
        var visited = Array(m){BooleanArray(n)}
        return bfs(m, n, k, visited)
//        dfs(0,0,m,n,k,visited)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}