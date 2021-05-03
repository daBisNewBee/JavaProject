package offer.tree

import offer.TreeNode
import java.util.*

/*
* 58、对称的二叉树
*
*
* 请实现一个函数，用来判断一颗二叉树是不是对称的。
* 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
*
*           8
*        6     6
*      5   7 7   5
*
*  1. 递归法:
*   稍容易理解些，注意判断对称的对象是左右子树，不是左右节点
*
*  2. BFS法：
*   不易想到！"左的左比较右的右，左的右比较右的左"，根据比较对象决定插入队列的顺序
*
* */

fun isSymmetrical(node:TreeNode?):Boolean {
    if (node == null) {
        return true
    }
    return isSymmetrical(node.left, node.right)
    /*
    fixme:这是错误示范！注意是比较左、右子树，不是左、右结点！
    if (node.left == null && node.right == null) {
        return true
    }
    if (node.left == null || node.right == null) {
        return false
    }
    return node.left?.value == node.right?.value
            && isSymmetrical(node.left)
            && isSymmetrical(node.right)
    */
}

fun isSymmetrical(leftTree:TreeNode?, rightTree:TreeNode?):Boolean {
    if (leftTree == null && rightTree == null) {
        return true
    }
    if (leftTree == null || rightTree == null) {
        return false
    }
    // 左的左比较右的右，左的右比较右的左
    return leftTree.value == rightTree.value
            && isSymmetrical(leftTree.left, rightTree.right)
            && isSymmetrical(leftTree.right, rightTree.left)
}

/*
*
* ps:
* 迭代，重复一定的算法，达到想要的目的。数学上二分法，牛顿法是很好的迭代例子
*
* */
fun isSymmetrical2(node:TreeNode?):Boolean {
    if (node == null) {
        return true
    }
    var queue = LinkedList<TreeNode>()
    queue.offer(node)
    queue.offer(node)
    while (queue.isNotEmpty()) {
        var cur1 = queue.poll()
        var cur2 = queue.poll()
        if (cur1 == null && cur2 == null) {
            continue
        }
        if (cur1 == null || cur2 == null) {
            return false
        }
        if (cur1.value != cur2.value) {
            return false
        }
        queue.offer(cur1.left)
        queue.offer(cur2.right)
        queue.offer(cur1.right)
        queue.offer(cur2.left)
    }
    return true
}

fun main(args:Array<String>) {

    var data = listOf(8,6,6,5,7,7,5)

    var rootNode = offer.createTree(data)

    var ret = isSymmetrical(rootNode[0])

    println("递归法：是否对称二叉树:$ret")

    ret = isSymmetrical2(rootNode[0])

    println("BFS遍历法：是否对称二叉树:$ret")

}