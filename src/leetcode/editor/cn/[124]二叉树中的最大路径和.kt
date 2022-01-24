import kotlin.math.max

//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 1368 👎 0


class P_124_BinaryTreeMaximumPathSum {
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

    private var maxSum = Int.MIN_VALUE

    private class Info(var maxValue:Int = Int.MIN_VALUE)

    // TODO: 不通过，想想为什么
    private fun process(x:TreeNode?):Info? {
        x ?: return Info(0)

        var leftInfo = process(x.left)
        var rightInfo = process(x.right)

        // 经过x
        var maxValue = x.`val`

        if (leftInfo != null && leftInfo.maxValue > 0) {
            maxValue += leftInfo.maxValue
        }

        if (rightInfo != null && rightInfo.maxValue > 0) {
            maxValue += rightInfo.maxValue
        }

        maxSum = Math.max(maxValue, maxSum)

        // 不经过x
        var singleNodeMaxValue = x.`val` + Math.max(leftInfo?.maxValue ?: 0, rightInfo?.maxValue ?: 0)
//        println("current ${x.`val`} max: maxChildValue: $maxChildValue maxContValue: $maxContValue")
        return Info(singleNodeMaxValue)
    }

    /**
     * 需要区分""节点最大路径和" 与 "节点的最大贡献值" "，不一样！
     *
     *
     */
    fun maxGan(node:TreeNode?):Int {
        node ?: return 0

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        var leftGain = Math.max(0, maxGan(node.left))

        var rightGain = Math.max(0, maxGan(node.right))

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        var maxPrice = leftGain + rightGain + node.`val`

        maxSum = Math.max(maxPrice, maxSum)

        // 返回节点的最大贡献值
        return node.`val` + Math.max(leftGain, rightGain)
        // fixme:"核心在于计算结果的时候要计算左右子树，递归返回的时候只能返回较大的一边"
    }

    fun maxPathSum(root: TreeNode?):Int {
        maxGan(root)
        return maxSum
    }


    fun maxPathSum2(root: TreeNode?): Int {
        process(root)
        return maxSum
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}