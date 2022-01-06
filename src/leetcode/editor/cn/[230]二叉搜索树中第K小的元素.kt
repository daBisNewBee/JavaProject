import offer.other.pop
import java.util.*

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,1,4,null,2], k = 1
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数为 n 。 
// 1 <= k <= n <= 104 
// 0 <= Node.val <= 104 
// 
//
// 
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 552 👎 0


class P_230_KthSmallestElementInABst {
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
     *
     * 如果二叉搜索树经常被修改（插入/删除操作）
     * 并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     *
     * 1.
     * 记录每个节点的子树节点数量，与k比较，进一步在左子树或者右子树中查找。
     * 搜索时间复杂度o(H)  H 是树高度
     *
     * 2. 更快的办法:o(logN)
     * 二叉搜索树 转换为 AVL 平衡二叉搜索树
     *
     */
    fun kthSmallest(root: TreeNode?, _k: Int): Int {
        root ?: return 0
        var k = _k
        var cur:TreeNode?=root
        var stack = Stack<TreeNode>()
        while (stack.isNotEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur)
                cur = cur.left
            }
            if (stack.isNotEmpty()) {
                cur = stack.pop()
                if (--k == 0) {
                    return cur.`val`
                }
                cur = cur.right
            }
        }
        return -1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}