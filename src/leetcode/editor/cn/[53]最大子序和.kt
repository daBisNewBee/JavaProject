//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
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
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 3482 👎 0


class P_53_MaximumSubarray {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         *
         * 这个思路更常规一点！DP
         *
         * 动态规划转移方程：
         * f(i)=max{f(i−1)+nums[i],nums[i]}
         *
         * 本质是，
         * 求f(i)时，只要nums[i]，还是要求(nums[i] + pre)的累加和？
         * 最终是对nums[i-1]的取舍。
         *
         */
        fun maxSubArray(nums: IntArray): Int {
            if (nums.isEmpty()) return 0
            if (nums.size == 1) return nums[0]
            var max = nums[0]
            var pre = 0
            for (ele in nums) {
                pre = Math.max(ele + pre, ele)
                max = Math.max(pre, max)
            }
            return max
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_53_MaximumSubarray.Solution().maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
}