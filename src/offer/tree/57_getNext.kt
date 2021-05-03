package offer.tree

import offer.TreeNode

/*
*
* 二叉树的下一个结点
* 给你一颗二叉树的一个结点，返回中序遍历顺序中这个结点的下一结点。二叉树不仅有左右孩子指针，还有指向父亲结点的指针。
*
* 方法1：
* 直接找下一结点
* fixme:需要分析特点，归为几个类别
* 时间复杂度： o(n)
  空间复杂度： o(1)
*
* 方法2：
* 还原二叉树:
* fixme:比较常规
* 时间复杂度： o(n)
  空间复杂度： o(n)
*
*
* */

fun getNext(node:TreeNode?):TreeNode? {
    if (node == null) {
        return null
    }
    /*
    "思想你要明白，当遇到不会的题，可以根据题意画图，分析，分析方法是关键。"

    思路：三种情况：
    （1）若给定结点有右子树，则下一个结点是它右子树中最左边的结点
    （2）若该结点没有右子树，并且是父节点的左结点，则下一个结点就是他的父节点
    （3）若该结点没有右子树，并且是父节点的右结点，则应该沿着父节点向上，直到找到的结点是一个左结点。则下一个结点是该左结点的父节点
    */
    var ret:TreeNode?=null
    if (node.right != null) {
        // 有右子树
        ret = node.right
        while (ret?.left != null) {
            ret = ret.left
        }
        return ret
    }
    // 无右子树
    var cur = node
    while (cur?.next != null) {
        var root = cur.next
        if (root?.left == cur) {
            return root
        }
        cur = cur.next
    }
    return null
}

var list:MutableList<TreeNode> = ArrayList()

fun inOrder(node:TreeNode?) {
    if (node == null) return
    inOrder(node.left)
    list.add(node)
    inOrder(node.right)
}

fun getNext2(node:TreeNode?):TreeNode? {
    if (node == null) {
        return null
    }
    var root = node
    // 找到根节点
    while (root?.next != null) {
        root = root.next
    }
    // 中序遍历
    inOrder(root)
    // 找到下一个节点
    list.let {
        for ((index, value) in it.withIndex()) {
            if (value == node) {
                return if (index + 1 == list.size) null else list[index+1]
            }
        }
    }
    return null
}

fun main(args:Array<String>) {
    var data = listOf(1,2,3,4,5,6)
    data.let {
        for ((index,value) in it.withIndex()) {
            println("index: $index value: $value")
        }
    }

}