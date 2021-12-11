//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。 
//
// 示例 1： 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36 
//
// 提示： 
//
// 
// 2 <= n <= 58 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
// Related Topics 数学 动态规划 
// 👍 329 👎 0


class P_Offer_14_I_JianShengZiLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     *
     * 可以拆解如下情况：
     * 1. i * n-i       拆解成两个数
     * 2. i, f(n-i)     拆解成一个数i，对n-i继续分解
     * 3. f(i), n-i     拆解成一个数n-i，对i继续分解
     * 4. f(i),f(j)     对i、j都继续分解
     *
     * 想下，其实2、3是一样的，因此保留一个
     * 4 也和2、3一样
     *
     * 因此问题归结为：
     * 1. 2
     * max(i * n-i、i, f(n-i))
     */

    lateinit var memory:IntArray // 记忆化搜索，避免计算重复值


        // 递归：自上向下
    fun cuttingRopeHelper(n:Int):Int {

        if (n == 2 || n == 1) {
            return 1
        }

        if (memory[n] != 0) return memory[n]

        println("cuttingRopeHelper: $n")

        var maxValue = -1
        for (i in 1..n-1) {
            maxValue = Math.max(maxValue, Math.max(i * (n-i), i * cuttingRopeHelper(n-i)))
        }
        memory[n] = maxValue
        return maxValue
    }

        // 动态规划：自下向上
    fun dpHelper(n:Int):Int {
        if (n == 2) return 1
        memory[2] = 1
        for (i in 3..n) {
            for (j in 1..(i/2 + 1)) {
                memory[i] = Math.max(memory[i], Math.max(j * (i-j), j * memory[i-j]))
            }
        }
        return memory[n]
    }

    fun cuttingRope(n: Int): Int {
        memory = IntArray(n + 1)
        var ans = dpHelper(n)
//        var ans = cuttingRopeHelper(n)
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_Offer_14_I_JianShengZiLcof.Solution().cuttingRope(10)
}