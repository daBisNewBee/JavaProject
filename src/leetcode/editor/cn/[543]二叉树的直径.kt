import offer.online.helper

//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 871 👎 0


class P_543_DiameterOfBinaryTree {
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

    private var maxValue = Int.MIN_VALUE

    // 递归解法
    private fun process2(x:TreeNode?):Int {
        x ?: return 0

        var left = process2(x.left)

        var right = process2(x.right)

        maxValue = Math.max(maxValue, left + right + 1)

        return Math.max(left, right) + 1
    }

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        maxValue = 1
        process2(root)
        return maxValue-1
    }

    private class Info(var maxDistance:Int, var height:Int)

    private fun process(x: TreeNode?):Info {
        x ?: return Info(0,0)

        var leftInfo = process(x.left)

        var rightInfo = process(x.right)

        var height = Math.max(leftInfo.height, rightInfo.height) + 1

        /**
         *
         * 当前节点x，经过还是不经过？要还是不要？
         *
         * 要？
         *   当前节点最大距离 = 左树高度 + 右树高度 + 1
         *
         * 不要？
         *   当前节点最大距离 = max(左树最大距离，右树最大距离)
         *
         * 所以，需要从子树得到的信息有 高度、最大距离
         *
         * 所以，定义出"Info"
         *
         * 所以，用子树的Info构造出自己的Info，返回
         *
         */
        var maxDistance = Math.max(leftInfo.height + rightInfo.height + 1,
                Math.max(leftInfo.maxDistance, rightInfo.maxDistance))

        return Info(maxDistance, height)
    }

    fun diameterOfBinaryTree2(root: TreeNode?): Int {
        return process(root).maxDistance - 1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}