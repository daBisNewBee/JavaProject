//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1275 👎 0


class P_31_NextPermutation {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun swap(nums: IntArray, i:Int, j:Int) {
        var tmp = nums[i]
        nums[i] = nums[j]
        nums[j] = tmp
    }

    fun reverse(nums: IntArray,begin:Int) {
        var end = nums.size - 1
        var start = begin
        while (start < end) {
            swap(nums, start, end)
            start++
            end--
        }
    }

        /**
         * 两步走：
         *
         * 1.
         *
         * 2.
         *
         * 其实还是有点懵逼！
         *
         */
    fun nextPermutation(nums: IntArray): Unit {
        var i = nums.size - 2
        // 1. 使下一个排列比当前大：找到 a[i] < a[i+1]，并交换
        // ex，1,2,3,8,5,7,6,4 ，找到 a[i] = 5
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--
        }
        // 思考，若此时直接交换：1,2,3,8,7,5,6,4，显然不行，大的幅度太大了
        // 2. "大"的幅度尽可能小：较小数尽可能靠右，较大数尽可能小
        if (i >= 0) {
            var j = nums.size - 1
            while (j >= 0 && nums[j] <= nums[i]) {
                j--
            }
            // 找到 a[j] = 6
            // 交换5、6，得到"1,2,3,8,6,7,5,4"
            swap(nums, i, j)
        }
        // "1,2,3,8,6,4,5,7"
        reverse(nums, i+1)
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_31_NextPermutation.Solution().nextPermutation(intArrayOf(1,2,3,8,5,7,6,4))
    // 12386457
}