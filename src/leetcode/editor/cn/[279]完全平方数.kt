//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 1196 👎 0


class P_279_PerfectSquares {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        var min = Int.MAX_VALUE

    fun dpWay(list:List<Int>, rest:Int, dp:IntArray):Int {

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
        for (i in list.indices) {
            var ans = dpWay(list, rest - list[i], dp)
            if (ans >= 0 && ans < min) {
                min = ans + 1
            }
        }
        dp[rest] = if (min == Int.MAX_VALUE) -1 else min
        return dp[rest]
    }


    // 来到第index个数字，剩余target
    fun process(list:List<Int>, index:Int, target:Int, cache:MutableList<Int>) {
        if (index >= list.size) {
            if (target == 0) {
                min = Math.min(min, cache.size)
            }
            return
        }
        if (target < 0) {
            return
        }
        var i = 0
        while (list[index] * i <= target) {
            if (i > 0) {
                cache.add(list[index])
            }
            process(list, index+1, target - list[index]*i, cache)
            i++
        }
        if (i-1 > 0) {
            repeat(i-1) {
                cache.removeAt(cache.size-1)
            }
        }
    }

    fun numSquares(n: Int): Int {
        var array = mutableListOf<Int>()
        for (i in 1..n) {
            var num = Math.pow(i * 1.0, 2.0)
            if (num <= n) {
                array.add(num.toInt())
            }
        }
//        var cache = mutableListOf<Int>()
//        process(array, 0, n, cache)
//        return min
        return dpWay(array, n, IntArray(n+1))
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_279_PerfectSquares.Solution().numSquares(12))
    println(P_279_PerfectSquares.Solution().numSquares(13))
}