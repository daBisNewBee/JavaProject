//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 10^5 
//
// 
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-s
//tock/ 
// Related Topics 数组 动态规划 
// 👍 197 👎 0


class P_Offer_63_GuPiaoDeZuiDaLiRunLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun maxProfit(prices: IntArray): Int {

        if (prices.size <= 1) return 0

        // 第0...i日的最小价格
        var dp = IntArray(prices.size-1)

        dp[0] = prices[0]

        for (i in 1 until prices.size-1) {
            dp[i] = Math.min(dp[i-1], prices[i])
        }

        var ans = 0
        for (i in 1 until prices.size) {
            ans = Math.max(ans, prices[i]-dp[i-1])
        }

        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}