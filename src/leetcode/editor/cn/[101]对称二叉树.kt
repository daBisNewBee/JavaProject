import java.util.*
//import offer.TreeNode

//给定一个二叉树，检查它是否是镜像对称的。
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1689 👎 0


class P_101_SymmetricTree {
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

    /**
     *
     * 为什么不能用"中序遍历比较回文串"？
     *
     * 中序不能推断一棵树。中序遍历是回文串不代表原树是对称的，比如[1,2,null,1]这样的。
     *
     * 如果推断一颗树？
     *
     * 中序 + 先序/后序
     *
     * 先序 + 后序 可以吗？不行！
     *
     *  ps.
        A
        /
        B
        前序遍历： AB, 后序遍历： BA
        A
        \
        B
        前序遍历： AB, 后序遍历： BA
     *
     */

    fun isSymmetric(root: TreeNode?): Boolean {
        root ?: return true

        var queue1 = LinkedList<TreeNode?>()
        var queue2 = LinkedList<TreeNode?>()
        var cur :TreeNode?= root
        queue1.offer(cur)
        queue2.offer(cur)
        while (queue1.isNotEmpty()) {
            var cur1 = queue1.poll()
            var cur2 = queue2.poll()
            if (cur1 == null && cur2 == null) {
                continue
            }
            if (cur1 == null || cur2 == null) {
                return false
            }
            if (cur1.`val` != cur2.`val`) {
                return false
            }
            queue1.offer(cur1.left)
            queue1.offer(cur1.right)
            queue2.offer(cur2.right)
            queue2.offer(cur2.left)
        }
        return true
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

//fun main() {
//    var data = listOf(1,2,2,3,4,4,3)
//    var arrays = offer.createTree(data)
//    var ans = P_101_SymmetricTree.Solution().isSymmetric(arrays[0])
//    println(ans)
//}