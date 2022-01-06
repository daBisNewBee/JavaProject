//给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。 
//
// 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-10,-3,0,5,9]
//输出：[0,-3,9,-10,null,5]
//解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
//
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,3]
//输出：[3,1]
//解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 按 严格递增 顺序排列 
// 
// Related Topics 树 二叉搜索树 数组 分治 二叉树 
// 👍 903 👎 0


class P_108_ConvertSortedArrayToBinarySearchTree {
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {

    private fun process(nums: IntArray, start:Int, end:Int):TreeNode? {
        if (start > end) {
            return null
        }
        var mid = start + (end - start) / 2 // 选择中间左边的作为根节点
//        var mid = (start + end + 1) / 2 // 中间右边节点作为根节点

        var x = TreeNode(nums[mid])
        x.left = process(nums, start, mid - 1) // mid 不能被选进去，已经是根节点了
        x.right = process(nums, mid + 1, end)
        return x
    }

    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return process(nums, 0, nums.size-1)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_108_ConvertSortedArrayToBinarySearchTree.Solution().sortedArrayToBST(intArrayOf(-10,-3,0,5,9))
    println(ans)
}