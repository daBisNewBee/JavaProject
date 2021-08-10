import java.util.*
import kotlin.collections.ArrayList

//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 963 👎 0


class P_102_BinaryTreeLevelOrderTraversal {
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
    // 自己写出来的！！
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        var ans = ArrayList<ArrayList<Int>>()
        if (root == null) {
            return ans
        }
        var queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            // 关键！代表了当前层的结点数目！
            var size = queue.size
            var level = ArrayList<Int>(size)
            while (size-- > 0) {
                var cur = queue.poll()
                level.add(cur.`val`)
                if (cur.left != null) {
                    queue.offer(cur.left)
                }
                if (cur.right != null) {
                    queue.offer(cur.right)
                }
            }
            ans.add(level)
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}