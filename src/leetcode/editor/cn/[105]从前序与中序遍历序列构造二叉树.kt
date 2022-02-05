//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 1405 👎 0


class P_105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
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

    fun dfs(preorder: IntArray, inorder: IntArray, map: Map<Int, Int>,
            preStart:Int, preEnd:Int, inStart:Int, inEnd:Int):TreeNode? {

        if (preStart > preEnd) {
            return null
        }

        if (preStart == preEnd) {
            return TreeNode(preorder[preStart])
        }

        var rootValue = preorder[preStart]
        var rootInOrderIndex = map[rootValue]!!
        var leftChildCount = rootInOrderIndex - inStart

        var node = TreeNode(rootValue)

        var leftNode = dfs(preorder, inorder, map, preStart+1, preStart+leftChildCount,
                inStart, rootInOrderIndex-1)

        var rightNode = dfs(preorder, inorder, map, preStart+1+leftChildCount, preEnd, rootInOrderIndex+1, inEnd)

        node.left = leftNode
        node.right = rightNode
        return node
    }

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        var map = mutableMapOf<Int, Int>()
        for (i in inorder.indices) {
            map[inorder[i]] = i
        }
        return dfs(preorder, inorder, map, 0, preorder.size-1, 0, inorder.size-1)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_105_ConstructBinaryTreeFromPreorderAndInorderTraversal.Solution().
            buildTree(intArrayOf(1,2,3), intArrayOf(3,2,1))
}