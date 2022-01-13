//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划 
// 👍 1829 👎 0


class P_198_HouseRobber {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    fun dpWay2(nums: IntArray):Int {
        if (nums.size == 1) return nums[0]

        var dp = IntArray(nums.size+1)
        dp[nums.size] = 0
        dp[nums.size-1] = nums[nums.size-1]

        for (i in nums.size-2 downTo 0) {
            dp[i] = Math.max(nums[i]+dp[i+2], dp[i+1])
        }
        return dp[0]
    }

    fun dpWay(nums: IntArray, index: Int, dp:IntArray):Int {
        if (index >= nums.size) {
            return 0
        }
        if (index == nums.size-1) {
            return nums[nums.size-1]
        }
        if (nums.size >= 2 && nums.size - index == 1) {
            return Math.max(nums[index], nums[nums.size-1])
        }
        if (dp[index] != -1) {
            return dp[index]
        }
        var yes = nums[index]
        if (index + 2 < nums.size) {
            yes += dpWay(nums, index+2, dp)
        }
        var no = 0
        if (index + 1 < nums.size) {
            no = dpWay(nums, index+1, dp)
        }
        dp[index] = Math.max(yes, no)
        return dp[index]
    }

    fun process(nums: IntArray, index:Int):Int {
        if (index >= nums.size) {
            return 0
        }
        if (index == nums.size-1) {
            return nums[nums.size-1]
        }
        if (nums.size >= 2 && nums.size - index == 1) {
            return Math.max(nums[index], nums[nums.size-1])
        }
        var yes = nums[index]
        if (index + 2 < nums.size) {
            yes += process(nums, index+2)
        }
        var no = 0
        if (index + 1 < nums.size) {
            no = process(nums, index+1)
        }
        return Math.max(yes, no)
    }

    fun rob(nums: IntArray): Int {
        var dp = IntArray(nums.size+1)
        for (i in dp.indices) {
            dp[i] = -1
        }
        return dpWay2(nums)
//        return dpWay(nums, 0, dp)
//        return process(nums, 0)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}