//已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums
//[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,
//2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 targ
//et ，则返回 true ，否则返回 false 。 
//
// 你必须尽可能减少整个操作步骤。 
//
// 
//m
// 示例 1： 
//
// 
//输入：nums = [2,5,6,0,0,1,2], target = 0
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,5,6,0,0,1,2], target = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -104 <= nums[i] <= 104 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -104 <= target <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。 
// 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？ 
// 
//
// 
// Related Topics 数组 二分查找 
// 👍 541 👎 0


class P_81_SearchInRotatedSortedArrayIi {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun search(nums: IntArray, target: Int): Boolean {
        if (nums.size == 1) return nums[0] == target
        var p1 = 0

        var p2 = nums.size-1


        while (p1 <= p2) {
            var mid = p1 + (p2-p1) / 2

            if (target == nums[mid]) {
                return true
            }

            // 比"33题"只多了这一步！最最关键的"有序数组"无法判断了！所以这里要先调整缩小区间！
            // 有点为难人了！时间可能退化到o(n)，还不如一次遍历！
            if (nums[p1] == nums[mid] && nums[mid] == nums[p2]) {
                p1++
                p2--
            } else
            if (nums[p1] <= nums[mid]) {
                if (target >= nums[p1] && target < nums[mid]) {
                    p2 = mid-1
                } else {
                    p1 = mid + 1
                }
            } else {
                if (target > nums[mid] && target <= nums[p2]) {
                    p1 = mid+1
                } else {
                    p2 = mid-1
                }
            }
        }
        return false
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_81_SearchInRotatedSortedArrayIi.Solution().search(intArrayOf(1,0,1,1,1), 0)
}