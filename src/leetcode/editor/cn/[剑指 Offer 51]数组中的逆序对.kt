//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 
// 👍 550 👎 0


class P_Offer_51_ShuZuZhongDeNiXuDuiLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         * 暴力解法：
         for (i in index until nums.size - 1) {
            for (j in i+1 until nums.size) {
            if (nums[i] > nums[j]) count++
            }
            }
         */
        /**
         * 很经典的一道题：
         * 核心思想，两个有序数组在归并的时候，逆序对等于另一数组剩余个数。
         * ps:
         *
         * 2 3 5 7 || 1 4 6 8
         *
         * 在比较时，右数组第一个数字"1"小于左边的"2"，则构成一个逆序对，
         * 又左数组有序，"2"小于后面的数字(一共四个)，因此与"1"构成的逆序对是4；
         *
         * 2 3 5 7 || 4 6 8
         * 1
         *
         * 视频讲的很好，很多细节，值得反复推敲！
         */
    fun reversePairs(nums: IntArray): Int {
            var len = nums.size

            if (len < 2) return 0

            var copy = IntArray(len)

            for (i in nums.indices) {
                copy[i] = nums[i]
            }

            var tmp = IntArray(len)

        return reversePairs(copy, 0, len-1, tmp) // 0，len-1 说明是数组的两个闭区间
    }

        private fun reversePairs(nums: IntArray, left: Int, right: Int, tmp: IntArray): Int {

            if (left == right) {
                return 0
            }

            var mid = left + (right-left) / 2 // 经验：需要这样写！因为原型是: (left+right)/2，但是有潜在的两个整数过大溢出问题

            var leftPairsCount = reversePairs(nums, left, mid, tmp) // 满足闭区间

            var rightPairsCount = reversePairs(nums, mid+1, right, tmp) // 满足闭区间

            if (nums[mid] <= nums[mid+1])
                return leftPairsCount + rightPairsCount // 已经有序，避免下面做无意义的判断

            var mergeCount = mergeAndCount(nums, left, mid, right, tmp)

            return leftPairsCount + rightPairsCount + mergeCount
        }

        /**
         * nums[left .. mid-1] 有序
         * nums[mid .. right] 有序
         */
        private fun mergeAndCount(nums: IntArray, left: Int, mid: Int, right: Int, tmp: IntArray):Int {
            // 因为是对nums的修改，所以要先拷贝出来
            // 为什么这里要预算分配数组tmp？避免分配很多临时数组
            for (p in left until right+1) {
                tmp[p] = nums[p]
            }

            var i = left
            var j = mid+1
            var count = 0
            for (k in left until right+1) {
                if (i == mid+1) { //左数组到了边界
                    nums[k] = tmp[j++]
                } else if (j == right+1) { //右数组到了边界
                    nums[k] = tmp[i++]
                } else if (tmp[i] <= tmp[j]) { // 注意这里要有"="，表示归并是稳定的排序
                    nums[k] = tmp[i++]
                } else {
                    nums[k] = tmp[j++]
                    count += mid-i+1 // 重要！只有这里是在计算逆序！为何得出这个公式，手动演算下。其实只比"归并排序"多了一个这个步骤！
                }
            }
            return count
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}