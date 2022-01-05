package leetcode.editor.cn

import offer.TreeNode

/**
 *
 *
 * 给定一颗二叉树的头结点head，
 *
 * 返回这颗二叉树中最大的二叉搜索子树
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

    Note:
    A subtree must include all of its descendants.
    Here's an example:

    10
    / \
    5  15
    / \   \
    1   8   7
    The Largest BST Subtree in this case is the highlighted one.
    The return value is the subtree's size, which is 3.
 *
 *
 * 参考：
 * 333. Largest BST Subtree最大二叉搜索树子树
 *
 */

class SolutionMaxBST {

    private class Info(var isBST:Boolean, var maxValue:Int,
                       var minValue:Int, var maxBSTSize:Int)

    private fun process(x: TreeNode?):Info? {
        x ?: return null

        var leftInfo = process(x.left)
        var rightInfo = process(x.right)

        var isBst = (leftInfo?.isBST ?: true) &&
                (rightInfo?.isBST ?: true)
        if (leftInfo != null) {
            isBst = isBst && leftInfo.maxValue < x.value
        }
        if (rightInfo != null) {
            isBst = isBst && rightInfo.minValue > x.value
        }

        var maxValue = Math.max(Math.max(leftInfo?.maxValue?:0, rightInfo?.maxValue?:0), x.value)

        var minValue = Math.min(Math.min(leftInfo?.minValue?:0, rightInfo?.minValue?:0), x.value)

        // 不经过x
        var maxBSTSize = Math.max(leftInfo?.maxBSTSize?:0, rightInfo?.maxBSTSize?:0)

        // 经过x
        if (isBst) {
            // 如果子树是搜索树，那么子树的最大搜索子树值，就是子树本身
            maxBSTSize = Math.max(maxBSTSize, (leftInfo?.maxBSTSize ?:0) + (rightInfo?.maxBSTSize ?: 0) + 1)
        }

        return Info(isBst, maxValue, minValue, maxBSTSize)
    }

    private fun way(head:TreeNode):Int {
        return process(head)!!.maxBSTSize
    }
}


