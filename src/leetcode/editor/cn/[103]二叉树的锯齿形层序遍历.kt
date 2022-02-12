import java.util.*

//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 592 👎 0


class P_103_BinaryTreeZigzagLevelOrderTraversal {
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
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        root ?: return emptyList()

        var queue = LinkedList<TreeNode>()
        queue.offer(root)

        var ans = mutableListOf<List<Int>>()
        var levelIndex = 0

        while (queue.isNotEmpty()) {

            var curLevelSize = queue.size

            var curList = mutableListOf<Int>()

            for (i in 0 until curLevelSize) {
                var cur = queue.poll()
                if (cur.left != null) {
                    queue.offer(cur.left)
                }
                if (cur.right != null) {
                    queue.offer(cur.right)
                }
                if (levelIndex % 2 == 0) {
                    curList.add(cur.`val`)
                } else {
                    // 还是正常的走bfs，只是在需要逆序的层，每次插入到头结点，作为逆序
                    curList.add(0,cur.`val`)
                }
            }
            ans.add(curList)
            levelIndex++
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}