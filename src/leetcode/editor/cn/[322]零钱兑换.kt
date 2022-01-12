//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 1652 👎 0


class P_322_CoinChange {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        var min = Int.MAX_VALUE


        // 记忆化搜索
        fun dpWay(coins: IntArray, rest: Int, dp: IntArray):Int {
            if (rest < 0) {
                return -1
            }
            if (rest == 0) {
                return 0
            }
            if (dp[rest] != 0) {
                return dp[rest]
            }
            var min = Int.MAX_VALUE
            for (i in coins.indices) {
                var res = dpWay(coins, rest-coins[i], dp)
                if (res >= 0 && res < min) {
                    min = res + 1 // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
                }
            }
            dp[rest] = if (min == Int.MAX_VALUE) -1 else min
            return dp[rest]
        }

        // 经典动态规划：自低向上
        fun dpWay2(coins: IntArray, amount: Int):Int {
            if (amount == 0) return 0

            var dp = IntArray(amount+1)
            dp[0] = 0
            for (i in 1 ..amount) {
                var min = Int.MAX_VALUE
                for (j in coins.indices) {
                    if (i - coins[j] >= 0 && dp[i-coins[j]] < min) {
                        min = dp[i-coins[j]] + 1
                    }
                }
                dp[i] = min
            }
            return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
        }

        // 暴力递归：适合改造dp的写法
        fun dpWay_Baoli(coins: IntArray, rest: Int, count: Int) {
            if (rest == 0) {
                min = Math.min(min, count)
                return
            }
            if (rest < 0) {
                return
            }
            for (i in coins.indices) {
                dpWay_Baoli(coins, rest - coins[i], count+1)
            }
        }

        // fixme: 想想这么写为什么会出错？因为有后效性！这个关于f(index、rest)的函数，前面的选择会影响f(index、rest)的结果！
        fun dpWay_wrong(coins: IntArray, index: Int, rest: Int, count: Int, dp:Array<IntArray>):Int {
            if (dp[index][rest] != -1) {
                return dp[index][rest]
            }
            if (index == coins.size) {
                var ans = 0
                if (rest == 0) {
                    min = Math.min(count, min)
                    ans = min
                }
                dp[index][rest] = ans
                return dp[index][rest]
            }

            var i = 0
            var curMin = Int.MAX_VALUE
            while (rest - coins[index] * i >= 0) {
                var ans = dpWay_wrong(coins, index+1, rest - coins[index] * i, count + i, dp)
                if (ans > 0) {
                    curMin = Math.min(curMin, ans)
                }
                i++
            }
            dp[index][rest] = if (curMin == Int.MAX_VALUE) 0 else curMin
            return dp[index][rest]
        }

        // 暴力递归
        fun findWay(coins: IntArray, index:Int, amount: Int, count:Int) {
            if (index >= coins.size) {
                if (amount == 0) {
                    min = Math.min(min, count)
                }
                return
            }
            if (amount == 0) {
                min = Math.min(min, count)
                return
            }
            var i = 0
            while (amount - coins[index] * i >= 0) {
                findWay(coins, index+1, amount - coins[index] * i, count + i)
                i++
            }
        }

    fun coinChange(coins: IntArray, amount: Int): Int {
        if (coins.isEmpty()) return -1
//        var dp = IntArray(amount+1)
//        var ans = dpWay(coins, amount, dp)
//        return ans
        return dpWay2(coins, amount)
//        findWay(coins, 0, amount, 0)

//        return if (min == Int.MAX_VALUE) -1 else min
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
//    println(P_322_CoinChange.Solution().coinChange(intArrayOf(1), 2))
    println(P_322_CoinChange.Solution().coinChange(intArrayOf(5,1,2), 11))
//    println(P_322_CoinChange.Solution().coinChange(intArrayOf(1,2), 3))
}