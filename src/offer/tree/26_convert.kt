package offer.tree

import offer.TreeNode

/*
*
* 26、二叉搜索树与双向链表
*
* 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
* 要求不能创建任何新的结点，只能调整树中结点指针的指向。
*
* fixme: 遍历两次 -> 遍历一次，但是需要额外root节点 -> 遍历一次，不需要root节点
*
* 注意三个方法是相关递进的，后一个方法解决前一个方法的缺点
*
* */

private class Node(intValue:Int = 0) {
    var next:Node ?= null
    var prev:Node ?= null
    var value:Int = intValue
}

fun inOrder(node:TreeNode?, list:MutableList<TreeNode>){
    if (node == null) {
        return
    }
    inOrder(node.left, list)
    list.add(node)
    inOrder(node.right, list)
}

// 先遍历，再建立链表关系。缺点：需要遍历两次 优点：保留二叉树结构
private fun convert(root:TreeNode?):Node? {
    if (root == null) {
        return null
    }
    var ret = ArrayList<TreeNode>()

    inOrder(root, ret)

    var head = Node()
    var pre:Node? = null
    for (treeNode in ret) {
        var node = Node(treeNode.value)
        node.prev = pre
        if (pre != null) {
            pre.next = node
        } else {
            head.next = node
        }
        pre = node
    }
    return head.next
}

var prev2:TreeNode ?= null
var root:TreeNode ?= null

// 在遍历的时候链表化。优点：只遍历一次 缺点：破坏原有二叉树的结构
fun convert2(node:TreeNode?):TreeNode? {
    if (node == null) {
        return null
    }
    convert2(node.left)
    // 保留头节点，返回时用
    if (root == null) {
        root = node
    }
    // 建立与前一个结点的关系
    if (prev2 != null) {
        prev2?.right = node
        node.left = prev2
    }

    prev2 = node

    convert2(node.right)

    return root
}

var prev3:TreeNode ?= null

// 先遍历右子树，再左子树。解决的是方法2的降序问题，那就不用root节点了
fun convert3(node:TreeNode?):TreeNode? {
    if (node == null) {
        return null
    }
    convert3(node.right)
    if (prev3 != null) {
        // 注意，这里和方法2有区别！
        node.right = prev3
        prev3?.left = node
    }
    prev3 = node
    convert3(node.left)
    return prev3
}

fun main(args:Array<String>) {

    var data = listOf(10,6,14,4,8,12,16)

    var array = offer.createTree(data)

    var headNode = convert(array[0])

    var cur:Node?=headNode

    while (cur != null) {

        print("${cur.value},")

        cur = cur.next
    }

    println()

    var root = convert2(array[0])

    var ret:TreeNode? = root

    while (ret != null) {

        print("${ret.value},")

        ret = ret.right
    }

    println()

    array = offer.createTree(data)

    ret = convert3(array[0])
    while (ret != null) {

        print("${ret.value},")

        ret = ret.right
    }

}