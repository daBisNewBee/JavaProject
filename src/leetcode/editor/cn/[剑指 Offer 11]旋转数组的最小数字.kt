//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2
//] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sor
//ted-array-ii/ 
// Related Topics 数组 二分查找 
// 👍 440 👎 0


class P_Offer_11_XuanZhuanShuZuDeZuiXiaoShuZiLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun minArray(numbers: IntArray): Int {
        if (numbers.isEmpty()) return -1
        var low = 0
        var high = numbers.size - 1
        while (low < high) {
            var mid = low + (high - low) / 2
            // 4 5 1 2 3
            if (numbers[mid] < numbers[high]) {
                high = mid
                // 4 5 6 1 2  举点例子，判断指针的移动情况
            } else if(numbers[mid] > numbers[high]) {
                low = mid + 1 // 为什么前面是 high = mid, 这里是 mid + 1 要 +1？
                // 主要是分析当前节点是否有可能是结果！+1就不可能是结果，排除在外了
            } else {
                // 4 3 4 4 4  暴力法，针对有重复数字的情况
                high--
            }
        }
        return numbers[low]
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ret = P_Offer_11_XuanZhuanShuZuDeZuiXiaoShuZiLcof.Solution().minArray(intArrayOf(1,3,5))
    println(ret)
}