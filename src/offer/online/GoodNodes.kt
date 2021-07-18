/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 *
 * 1448. 统计二叉树中好节点的数目
 *
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。

「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。

 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {

    var count = 0

    fun dfs(node: TreeNode?, curMax: Int) {
        if (node == null) return
        if (node.`val` >= curMax) {
            count++
        }
        dfs(node.left, Math.max(node.`val`, curMax))
        dfs(node.right, Math.max(node.`val`, curMax))
    }

    fun goodNodes(root: TreeNode?): Int {
        if (root == null) return 0
        dfs(root, root!!.`val`)
        return count
    }
}