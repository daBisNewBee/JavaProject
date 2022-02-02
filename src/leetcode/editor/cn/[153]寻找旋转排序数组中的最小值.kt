//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变
//化后可能得到：
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2] 
// 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,5,1,2]
//输出：1
//解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
// 
//
// 示例 3： 
//
// 
//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 中的所有整数 互不相同 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
// Related Topics 数组 二分查找 
// 👍 659 👎 0


class P_153_FindMinimumInRotatedSortedArray {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 思想：
         *
         * 数组最后一位数字pivot，最小值右边的数字，一定小于pivot;
         * 最小值左边的数字一定大于pivot
         *
         * 根据上述规律，调整区间
         *
         */
        fun findMin(nums: IntArray): Int {
            var p1 = 0
            var p2 = nums.size-1
            var pivot = nums[nums.size-1]

            while (p1 < p2) {
                var mid = p1 + (p2-p1)/2
                if (nums[mid] > pivot) {
                    p1 = mid+1 // 这里要加1！！因为nums[mid]肯定不会是结果！
                } else {
                    p2 = mid
                }
            }
            return nums[p1]
        }

        /**
         *
         * 这个自己写出来的！
         *
         * 主要思想：最小值一定位于有序数组右边界的右一位。因此先找到有序数组，缩小查找范围。在剩余的无序部分，判断边界大小
         *
         * 思想参考：33、81题
         *
         */
        fun findMin2(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]

        var p1 = 0
        var p2 = nums.size-1

        while (p1 <= p2) {
            if (nums[p1] < nums[p2]) return nums[p1]

            var mid = p1 + (p2-p1) / 2
            if (nums[p1] < nums[mid]) {
                p1 = mid
            } else {
                // 已经找到了无序部分
                if (nums[p1] > nums[p1+1]) {
                    return nums[p1+1]
                } else {
                    p2 = mid
                }
            }
        }
        return -1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_153_FindMinimumInRotatedSortedArray.Solution().findMin(intArrayOf(3,4,5,1,2)))
}