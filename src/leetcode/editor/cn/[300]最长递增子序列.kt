//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 
// 👍 2174 👎 0


class P_300_LongestIncreasingSubsequence {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        fun dpWay(nums: IntArray):Int {
            var dp = IntArray(nums.size)
            dp[0] = 1
            var ans = Int.MIN_VALUE
            for (i in 1 until nums.size) {
                dp[i] = 1
                for (j in 0 until i) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j]+1)
                    }
                }
                ans = Math.max(ans, dp[i])
            }
            return ans
        }

    var maxLen = Int.MIN_VALUE

    fun process(nums: IntArray, index:Int, ans:MutableList<Int>) {
        if (index == nums.size) {
            maxLen = Math.max(maxLen, ans.size)
            return
        }
        // 选中
        if (ans.isNotEmpty() && nums[index] > ans[ans.size-1] || ans.isEmpty()) {
            ans.add(nums[index])
            process(nums, index+1, ans)
            ans.removeAt(ans.size-1)
        }
        // 不选中
        process(nums, index+1, ans)
    }

    fun lengthOfLIS(nums: IntArray): Int {
        if (nums.size == 1) return 1
//        process(nums, 0, mutableListOf())
//        return maxLen
        return dpWay(nums)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}