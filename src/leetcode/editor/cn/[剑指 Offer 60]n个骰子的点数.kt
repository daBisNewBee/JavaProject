import offer.tree.map

//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
//
// 
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
// 
//
// 示例 2: 
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778] 
//
// 
//
// 限制： 
//
// 1 <= n <= 11 
// Related Topics 数学 动态规划 概率与统计 
// 👍 325 👎 0


class P_Offer_60_NgeTouZiDeDianShuLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        private fun processDp(n:Int):DoubleArray {
            // dp[i][j] 表示投完第i个筛子，点数j的次数
            var m = n * 6 // 求和范围不大于m
            var dp = Array(n+1){IntArray(m+1)}

//            dp[0..n-1][0] = 0
            for (j in 1..6) {
                dp[1][j] = 1
            }

            for (index in 2..n) {
                for (j in index..6*n) {
                    // 第index 出现的筛子只可能为1..6，点数总和为j，
                    // 即从dp[index-1][j-1]、dp[index-1][j-2]、...dp[index-1][j-6]得来
                    for (cur in 1..6) {
                        if (j > cur) {
                            dp[index][j] += dp[index-1][j-cur]
                        }
                    }
                }
            }

            var ans = mutableListOf<Double>()
            // 总次数
            var countSum = Math.pow(6.0,n * 1.0)
            for (i in n*1 ..n*6) {
                ans.add(dp[n][i] / countSum)
            }
            return ans.toDoubleArray()
        }
        // TODO: 优化空间解法有点不理解

        /**
         * 暴力递归：(Time Limit Exceeded)
         *
         * index: 当前第index个数
         * sum： 前0..index-1 的和
         * map: sum -> 出现次数
         */

        private fun process(n:Int, index:Int, sum:Int, map:MutableMap<Int, Int>) {
            if (index == n) {
                map[sum] = map.getOrDefault(sum, 0) + 1
                return
            }
            for (i in 1..6) {
                process(n, index+1, sum+i, map)
            }
        }

        private fun _dicesProbability(n:Int):DoubleArray {
            var map = mutableMapOf<Int,Int>()

            process(n, 0, 0, map)

            var sum = 0
            for (one in map.values) {
                sum += one
            }
            var sums = map.keys
            sums.sorted()

            var ans = mutableListOf<Double>()

            for (one in sums) {
                ans.add(map[one]!! * 1.0 / sum)
            }

            return ans.toDoubleArray()
        }

    fun dicesProbability(n: Int): DoubleArray {
        return processDp(n)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_Offer_60_NgeTouZiDeDianShuLcof.Solution().dicesProbability(2)
    for (one in ans) {
        println(one)
    }
}