//给定一个二叉树，判断它是否是高度平衡的二叉树。
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 852 👎 0


class P_110_BalancedBinaryTree {
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

    // "二叉树的递归套路"

    // 3. 列出所有可能性后，确定到底要向左树、右树要什么样的信息
    class Info(var isBalance:Boolean, var height:Int)

    // 1. 假设以"x"节点为头，可以向x左树、x右树要任何信息
    private fun process(x:TreeNode?):Info { // 4. 左树信息+右树信息求全集，作为任何一颗子树要返回的信息S
        // 5. 递归函数都返回S，每一颗子树都这么要求

        x?:return Info(true, 0)
        // 2. x要或者不要。在1的假设下，讨论以x为头结点的树，得到答案的可能性(最重要)

        var leftInfo = process(x.left)

        var rightInfo = process(x.right)

        var height = Math.max(leftInfo.height, rightInfo.height) + 1

        var isBalance = false

        if (leftInfo.isBalance
                && rightInfo.isBalance
                && Math.abs(leftInfo.height - rightInfo.height) <= 1) {
            isBalance = true
        }
        // 6. 通过左树信息、右树信息整合出整棵树的信息
        return Info(isBalance, height)
    }


    fun isBalanced(root: TreeNode?): Boolean {
        return process(root).isBalance
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}