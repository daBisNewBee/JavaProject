import java.lang.StringBuilder

//给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
// 
// 
// 每条从根节点到叶节点的路径都代表一个数字： 
//
// 
// 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。 
// 
//
// 计算从根节点到叶节点生成的 所有数字之和 。 
//
// 叶节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：25
//解释：
//从根到叶子节点路径 1->2 代表数字 12
//从根到叶子节点路径 1->3 代表数字 13
//因此，数字总和 = 12 + 13 = 25 
//
// 示例 2： 
//
// 
//输入：root = [4,9,0,5,1]
//输出：1026
//解释：
//从根到叶子节点路径 4->9->5 代表数字 495
//从根到叶子节点路径 4->9->1 代表数字 491
//从根到叶子节点路径 4->0 代表数字 40
//因此，数字总和 = 495 + 491 + 40 = 1026
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 1000] 内 
// 0 <= Node.val <= 9 
// 树的深度不超过 10 
// 
// 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 480 👎 0


class P_129_SumRootToLeafNumbers {
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

    fun dfs(node: TreeNode?, cur:MutableList<Int>, ans:MutableList<Int>) {
        node ?: return
        cur.add(node.`val`)

        if (node.left == null && node.right == null) {
            var sb = StringBuilder()
            cur.forEach { sb.append(it.toString()) }
            ans.add(Integer.valueOf(sb.toString()))
            cur.removeAt(cur.size-1)
            return
        }

        dfs(node.left, cur, ans)
        dfs(node.right, cur, ans)
        cur.removeAt(cur.size-1)
    }

    fun sumNumbers(root: TreeNode?): Int {
        root ?: return 0

        var ans = mutableListOf<Int>()
        dfs(root, mutableListOf(), ans)

        var sum = 0
        ans.forEach { sum += it }
        return sum
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}