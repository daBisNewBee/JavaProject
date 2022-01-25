import kotlin.math.min

//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 1463 👎 0


class P_152_MaximumProductSubarray {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        fun maxProduct(nums: IntArray): Int {
            var minArray = IntArray(nums.size)
            var maxArray = IntArray(nums.size)
            System.arraycopy(nums, 0, minArray, 0, nums.size)
            System.arraycopy(nums, 0, maxArray, 0, nums.size)

            for (i in 1 until nums.size) {
                minArray[i] = Math.min(maxArray[i-1] * nums[i], Math.min(nums[i], minArray[i-1] * nums[i]))
                maxArray[i] = Math.max(maxArray[i-1] * nums[i], Math.max(nums[i], minArray[i-1] * nums[i]))
            }
            var ans = Int.MIN_VALUE
            maxArray.forEach {
                ans = Math.max(ans, it)
            }
            return ans
        }

        /**
         *
         * 想想为什么这样写会失败？不行？
         *
         * 因为："当前位置的最优解未必是由前一个位置的最优解转移得到的"
         *
         * 所以，我们可以根据正负性进行分类讨论。如上
         *
         */
    fun maxProduct_Error(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        var dp = IntArray(nums.size)
        dp[0] = nums[0]
        var ans = Int.MIN_VALUE

        for (i in 1 until nums.size) {
            if (dp[i-1] >= dp[i-1] * nums[i]) {
                dp[i] = nums[i]
            } else {
                dp[i] = dp[i-1] * nums[i]
            }
            ans = Math.max(dp[i], ans)
        }

        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}