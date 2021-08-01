//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 1147 👎 0


class P_98_ValidateBinarySearchTree {
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
    fun isValidBST(root: TreeNode?): Boolean {
        // 用Int.MIN_VALUE 会报错？
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    fun helper(root: TreeNode?, lower:Long, upper:Long):Boolean {
        if (root == null) {
            return true
        }
        // 注意：BST，不包括"="的情况
        if (root.`val` >= upper || root.`val` <= lower) {
            return false
        }
        return helper(root.left, lower, root.`val`.toLong())
                && helper(root.right, root.`val`.toLong(), upper)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}