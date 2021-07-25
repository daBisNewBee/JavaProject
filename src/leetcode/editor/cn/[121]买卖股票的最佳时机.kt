import kotlin.math.min

//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 104 
// 
// Related Topics 数组 动态规划 
// 👍 1736 👎 0


class P_121_BestTimeToBuyAndSellStock {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         * 一次遍历,o(n),比暴力解法o(n2)快多了
         */
        fun maxProfit(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = 0
        for (price in prices) {
            if (price < minPrice) {
                // 保存的是历史最小值(当前第i次，即0...i-1的最小值)，不是全局最小值
                // 比如[2,5,1,3],返回3，min从2变化到1，会一直在变化
                minPrice = price
            } else if (price - minPrice > maxProfit){
                maxProfit = price - minPrice
            }
        }
        return maxProfit
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}