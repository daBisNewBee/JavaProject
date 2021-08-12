//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 2568 👎 0


class P_42_TrappingRainWater {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 总和等于每个单位的储水相加
         *
         * 每个单位的储水 = 两边最大高度的较小值 - 当前高度
         *
         * 至于"两边最大高度的较小值"怎么求？
         *
         * 可以每次都扫描，需要o(n2)，即暴力求解
         *
         * 也可以扫描两次，用o(n)的空间记录下结果，即动态编程
         *
         */
        fun trap(height: IntArray): Int {
        if (height.isEmpty()) return 0

        var ans = 0

        var left_max = IntArray(height.size)

        var right_max = IntArray(height.size)

        left_max[0] = height[0]

        for (i in 1 until height.size) {
            left_max[i] = Math.max(height[i], left_max[i-1])
        }

        right_max[height.size-1] = height[height.size-1]

        for (i in height.size-2 downTo 0) {
            right_max[i] = Math.max(right_max[i+1], height[i])
        }

        for (i in 1 until height.size) {
            ans += (Math.min(left_max[i], right_max[i]) - height[i])
        }

        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}