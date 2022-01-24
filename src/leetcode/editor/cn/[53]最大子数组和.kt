//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 4269 👎 0


class P_53_MaximumSubarray2 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 缩减空间o(n) 到 o(1)，因为每次计算只和最近一个结果有关系
    fun maxSubArray(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]

        var fnMax = nums[0]
        var ans = fnMax
        for (i in 1 until nums.size) {
            fnMax = Math.max(nums[i], nums[i] + fnMax)
            ans = Math.max(fnMax, ans)
        }
        return ans
    }

    fun maxSubArray2(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        var dp = IntArray(nums.size)

        dp[0] = nums[0]
        var ans = dp[0]

        for (i in 1 until nums.size) {
            dp[i] = Math.max(nums[i] , dp[i-1] + nums[i] )
            ans = Math.max(ans, dp[i])
        }

        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_53_MaximumSubarray2.Solution().maxSubArray(intArrayOf(-1,-2))
    println(ans)
//    P_53_MaximumSubarray2.Solution().maxSubArray(intArrayOf(5,4,-1,7,8))
}