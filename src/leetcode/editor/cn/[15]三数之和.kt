import java.util.*

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 4189 👎 0


class P_15_ThreeSum {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // 再写一遍！不要忘了两处去重！
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size <= 2) return emptyList()

        Arrays.sort(nums)
        var ans = mutableListOf<List<Int>>()

        for (first in nums.indices) {
            // 容易忘记！要去重
            if (first > 0 && nums[first] == nums[first-1]) {
                continue
            }
            var target = -nums[first]
            var third = nums.size-1

            for (second in first+1 until nums.size) {
                // 容易忘记！也要去重
                if (second > first+1 && nums[second] == nums[second-1]) {
                    continue
                }

                while (second < third && nums[second] + nums[third] > target) {
                    third--
                }
                if (second == third) {
                    break
                }
                if (nums[second] + nums[third] == target) {
                    var cur = mutableListOf<Int>()
                    cur.add(nums[first])
                    cur.add(nums[second])
                    cur.add(nums[third])
                    ans.add(cur)
                }
            }
        }
        return ans
    }

        // 排序 + 双指针
    fun threeSum2(nums: IntArray): List<List<Int>> {

        if (nums.isEmpty() || nums.size <= 2) return emptyList()

        // 排序后，保证结果唯一
        Arrays.sort(nums)

        var ans = mutableListOf<List<Int>>()

        for (first in nums.indices) {
            if (first > 0 && nums[first] == nums[first-1])
                continue

            var target = -nums[first]
            var third = nums.size-1

            for (second in first+1 until nums.size) {
                if (second > first+1 && nums[second] == nums[second-1])
                    continue

                // 保证左指针在右指针左侧
                while (second < third && nums[second] + nums[third] > target) {
                    third--
                }

                if (second == third) {
                    break
                }

                if (target == nums[second] + nums[third]) {
                    var list = mutableListOf<Int>()
                    list.add(nums[first])
                    list.add(nums[second])
                    list.add(nums[third])
                    ans.add(list)
                }
            }

        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_15_ThreeSum.Solution().threeSum(intArrayOf(1,-1,-1,0))
}