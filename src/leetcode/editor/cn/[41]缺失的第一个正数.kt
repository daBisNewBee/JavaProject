import kotlin.math.abs

//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 105 
// -231 <= nums[i] <= 231 - 1 
// 
// Related Topics 数组 哈希表 
// 👍 1152 👎 0


class P_41_FirstMissingPositive {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // 对于一个长度为 N 的数组，其中没有出现的最小正整数只能在[1, N+1]中。
        // 这是因为如果 [1, N] 都出现了，那么答案是 N+1，
        // 否则答案是 [1, N]中没有出现的最小正整数。
    fun firstMissingPositive(nums: IntArray): Int {
        // 替换
        var n = nums.size
        for (i in nums.indices) {
            if (nums[i] <= 0) {
                nums[i] = n + 1
            }
        }
        // 标记
        for (i in nums.indices) {
            var num = Math.abs(nums[i])
            if (num <= n) {
                nums[num-1] = -Math.abs(nums[num-1])
            }
        }
        for (i in nums.indices) {
            if (nums[i] > 0) {
                return i+1
            }
        }
        return n+1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}