import offer.online.ListNode

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1234 👎 0


class P_236_LowestCommonAncestorOfABinaryTree {
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {

    var parent = HashMap<Int, TreeNode>()
    var visited = HashSet<Int>()

    // 通过map 建立与父节点关系
    fun dfs(root:TreeNode?) {
        if (root == null) {
            return
        }
        if (root.left != null) {
            parent[root.left!!.`val`] = root
            dfs(root.left)
        }
        if (root.right != null) {
            parent[root.right!!.`val`] = root
            dfs(root.right)
        }
    }

    // 2. 由下往上遍历
    fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        dfs(root)
        var P:TreeNode? = p
        while (P != null) {
            visited.add(P.`val`)
            P = parent[P.`val`]
        }

        var Q = q
        while (Q != null) {
            if (visited.contains(Q.`val`)) {
                return Q
            }
            Q = parent[Q.`val`]
        }
        return null
    }

    // 其实也符合"二叉树的递归套路"，想想是吗？是的！Info只有一个信息TreeNode
    // 优先考虑：1. 递归法
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null
        if (root == p || root == q) return root

        var left = lowestCommonAncestor(root.left, p, q)
        var right = lowestCommonAncestor(root.right, p, q)
        // 说明p、q分列在root的两侧，此时返回root
        if (left != null && right != null) return root
        // "p、q都在左侧" 或者 "p、q都在右侧"
        return left ?: right
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}