package offer.tree

import offer.TreeNode
import offer.treeDepth

/*
* 平衡二叉树
*
* 平衡二叉树（Balanced Binary Tree），具有以下性质：
* 1. 它是一棵空树
* 2. 它的左右两个子树的高度差的绝对值不超过1，&& 左右两个子树都是一棵平衡二叉树。
*   abs(height(node->left, node->right)) <= 1 && isBalanced(left) && isBalanced(right)
*
* 善用map，减少重复计算子树高度的次数！
*
* */

// ps: AVL树，本质上是带了平衡功能的二叉查找树（二叉排序树，二叉搜索树）。

var map:MutableMap<TreeNode, Int> = HashMap()

/*
*
*
* map 的巧用：
* map使用前：
写入 ----> 节点 7 高度: 1
写入 ----> 节点 3 高度: 2
写入 ----> 节点 4 高度: 1
写入 ----> 节点 1 高度: 3
写入 ----> 节点 5 高度: 1
写入 ----> 节点 6 高度: 1
写入 ----> 节点 2 高度: 2
写入 ----> 节点 7 高度: 1
写入 ----> 节点 3 高度: 2
写入 ----> 节点 4 高度: 1
写入 ----> 节点 7 高度: 1
写入 ----> 节点 5 高度: 1
写入 ----> 节点 6 高度: 1

* map使用后：
*
写入 ----> 节点 7 高度: 1
写入 ----> 节点 3 高度: 2
写入 ----> 节点 4 高度: 1
写入 ----> 节点 1 高度: 3
写入 ----> 节点 5 高度: 1
写入 ----> 节点 6 高度: 1
写入 ----> 节点 2 高度: 2
节点 3 找到高度: 2
节点 4 找到高度: 1
节点 7 找到高度: 1
节点 5 找到高度: 1
节点 6 找到高度: 1
*
* */

fun depth(node: TreeNode?):Int {
    if (node == null) return 0
    var heightFound = map[node]
    if (heightFound != null) {
        println("节点 ${node.value} 找到高度: $heightFound")
        return heightFound
    }
    var i = depth(node.left)
    var j = depth(node.right)
    var height = Math.max(i,j) + 1
    map[node] = height
    println("写入 ----> 节点 ${node.value} 高度: $height")
    return height
}

// 方法一：自顶向下
fun isBalanceTree(node:TreeNode?):Boolean {
    if (node == null) return true // 空树也是二叉平衡树
    return Math.abs(depth(node.left) - depth(node.right)) <= 1
            && isBalanceTree(node.left) // fixme: 容易忘记对左右子树平衡性的判断！，比如，
            && isBalanceTree(node.right)
}

/*
*       0
*     1    4
*   2     5
*  3        6
*
*  此时，左右子树高度差不超过1，但是左右子树非平衡树！
*
* */

/*
*
* 方法二：自底向上
*
* 为了避免重复遍历，我们可以得到一种"每个结点只遍历一次的解法":
*
* "利用后序遍历：左子树、右子树、根节点,可以先递归到叶子节点，然后在回溯的过程中来判断是否满足条件。"
*
* */
fun depth2(node: TreeNode?):Int {

    if (node == null) return 0

    var left = depth2(node.left)
    if (left == -1) return -1 // 这里返回-1，相当于剪枝，加速结束递归。

    var right = depth2(node.right)
    if (right == -1) return -1 // 剪枝

    if (Math.abs(left - right) > 1) return -1 // 剪枝

    return Math.max(left, right) + 1
}

fun isBalanceTree2(node:TreeNode?):Boolean {
    if (node == null) return true
    return depth2(node) != -1
}

fun main(args:Array<String>) {

    var data = listOf(0,1,2,3,4,5,6,7)

    var arrays = offer.createTree(data)

    var ret = isBalanceTree(arrays[0])

    println("是否平衡树: $ret")

    ret = isBalanceTree2(arrays[0])
    println("是否平衡树: $ret")
}