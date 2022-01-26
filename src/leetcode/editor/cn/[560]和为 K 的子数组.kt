import java.util.*

//给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -1000 <= nums[i] <= 1000 
// -107 <= k <= 107 
// 
// Related Topics 数组 哈希表 前缀和 
// 👍 1285 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
 class Solution {

    fun subarraySum(nums: IntArray, k: Int): Int {
        if (nums.size == 1) return if (nums[0] == k) 1 else 0

        var map = mutableMapOf<Int, Int>()
        var ans = 0
        var pre = 0

        map.put(0,1)

        for (i in nums.indices) {
            pre += nums[i]
            if (map.containsKey(pre-k)) {
                ans += map.getOrDefault(pre-k, 0)
            }
            map[pre] = map.getOrDefault(pre, 0) + 1
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

fun main() {
    println(Solution().subarraySum(intArrayOf(1,1,1), 2))
}