//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。如果存在，返回 true ；否则，返回 false 。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//解释：等于目标和的根节点到叶节点路径如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
//解释：树中存在两条根节点到叶子节点的路径：
//(1 --> 2): 和为 3
//(1 --> 3): 和为 4
//不存在 sum = 5 的根节点到叶子节点的路径。 
//
// 示例 3： 
//
// 
//输入：root = [], targetSum = 0
//输出：false
//解释：由于树是空的，所以不存在根节点到叶子节点的路径。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 781 👎 0


class P_112_PathSum {
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

    // 比较简易的写法：结果不要求具体路径节点，就不需要缓存了
    fun dfs_simple(node: TreeNode?, restSum: Int):Boolean {
        node ?: return false

        if (node.left == null && node.right == null) {
            return restSum == node.`val`
        }

        return dfs_simple(node.left, restSum-node.`val`) || dfs_simple(node.right, restSum-node.`val`)
    }

    // 需要额外的内存cur，联系"113 路径总和2"的解法，基本一样，比较好记忆
    fun dfs(node: TreeNode?, restSum:Int, cur:MutableList<Int>):Boolean {
        node ?: return false

        cur.add(node.`val`)
        var restVal = restSum - node.`val`

        if (restVal == 0 && node.left == null && node.right == null) {
            return true
        }
        var ans = dfs(node.left, restVal, cur)
        if (ans) return true

        ans = dfs(node.right, restVal, cur)
        if (ans) return true

        cur.removeAt(cur.size-1)
        return false
    }

    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        return dfs_simple(root, targetSum)
//        return dfs(root, targetSum, mutableListOf())
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}