import kotlin.math.max

//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1078 👎 0


class P_104_MaximumDepthOfBinaryTree {
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

    private class Info(var height:Int)

    private fun process(x:TreeNode?):Info {
        if (x == null) {
            return Info(0)
        }
        var leftInfo = process(x.left)
        var rightInfo = process(x.right)
        var height = Math.max(leftInfo.height, rightInfo.height) + 1
        return Info(height)
    }


    fun maxDepth(root: TreeNode?): Int {
        return process(root).height
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}