import com.sun.tools.corba.se.idl.constExpr.BooleanNot
import kotlin.math.sign

//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1568 👎 0


class P_55_JumpGame {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        private fun best(nums: IntArray):Boolean {
            var rightMost = 0
            for (i in 0 until nums.size-1) {
                if (i <= rightMost) {
                    rightMost = Math.max(rightMost, nums[i] + i)
                    if (rightMost >= nums.size-1) {
                        return true
                    }
                }
            }
            return false
        }

        // 动态规划也可以
    private fun dpWay(nums: IntArray, index: Int, visited:BooleanArray):Boolean {
        if (index > nums.size - 1) {
            return false
        }
        if (index + nums[index] >= nums.size - 1) {
            return true
        }
        if (visited[index]) {
            return false
        }
        if (nums[index] > 0) {
            for (i in nums[index] downTo 1) {
                if (dpWay(nums, index + i, visited)) {
                    return true
                }
            }
        }
        visited[index] = true
        return false
    }

    private fun process(nums: IntArray, index:Int):Boolean {
        if (index > nums.size - 1 || nums[index] == 0) {
            return false
        }
        if (index + nums[index] >= nums.size - 1) {
            return true
        }
        for (i in nums[index] downTo 1) {
            if (process(nums, index + i)) {
                return true
            }
        }
        return false
    }

    fun canJump(nums: IntArray): Boolean {
        if (nums.size == 1) return true
//        var visited = BooleanArray(nums.size-1)
//        return dpWay(nums, 0, visited)
//        return process(nums, 0)
        return best(nums)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_55_JumpGame.Solution().canJump(intArrayOf(3,0,8,2,0,0,1))
    println(ans)
}