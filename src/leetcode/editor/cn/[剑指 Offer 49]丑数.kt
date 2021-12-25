//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 
// 👍 252 👎 0


class P_Offer_49_ChouShuLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun nthUglyNumber(n: Int): Int {
        var p2 = 0
        var p3 = 0
        var p5 = 0
        var ans = IntArray(n)
        ans[0] = 1
        for (i in 1 until n) {
            ans[i] = Math.min(ans[p2] * 2,
                    Math.min(ans[p3] * 3,
                            ans[p5] * 5))
            if (ans[i] == ans[p2] * 2) {
                p2++
            }
            if (ans[i] == ans[p3] * 3) {
                p3++
            }
            if (ans[i] == ans[p5] * 5) {
                p5++
            }
        }
        return ans[n-1]
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_Offer_49_ChouShuLcof.Solution().nthUglyNumber(10)
}