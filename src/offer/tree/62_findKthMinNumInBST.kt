package offer.tree

import offer.TreeNode
import java.util.*


/*
*
* 62、二叉搜索树的第k个结点
*
* 给定一棵二叉搜索树，请找出其中的第k小的结点。
* 例如（5，3，7，2，4，6，8） 中，按结点数值大小顺序第三小结点的值为4。
*
*
* 思路：二叉搜索树中序遍历正好就是递增序列
* */

fun inOrder(node:TreeNode?, retList:MutableList<Int>, seq:Int) {
    if (node == null) return
    if (retList.size >= seq) return // 小优化：及时终止，否则会有多余的遍历

    inOrder(node.left,retList,seq)
    retList.add(node.value)
    inOrder(node.right,retList,seq)
}

// 中序遍历：数组保存
fun find(node:TreeNode, seq:Int):Int {

    var inOrderList:MutableList<Int> = ArrayList()

    inOrder(node, inOrderList,seq)

    if (seq - 1 < 0 || seq > inOrderList.size) {
        return -1
    }
    return inOrderList[seq-1]
}

// 中序遍历：栈
fun find2(node: TreeNode?, seq: Int):Int {

    var stack = Stack<TreeNode>()

    var cur:TreeNode? = node

    var _seq = seq

    while (stack.isNotEmpty() || cur != null) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }
        if (stack.isNotEmpty()) {
            cur = stack.pop()
            if (--_seq == 0) { //计数器功能
                return cur.value
            }
            cur = cur.right
        }
    }
    return -1
}

var curIndex = 0

// 中序遍历: 递归法  TODO:不太明白
fun find3(node: TreeNode, seq: Int):TreeNode? {
    var cur:TreeNode?=null
    if (node.left != null) {
        cur = find3(node.left!!,seq)
    }
    curIndex++
    if (curIndex == seq) {
        cur = node
    }
    if (cur == null && node.right != null) {
        cur = find3(node.right!!, seq)
    }
    return cur
}

fun main(args:Array<String>) {

    var data = listOf(5,3,7,2,4,6,8)

    var array = offer.createTree(data)

    var seq = 3

    var ret = find(array[0], seq)

    println("中序遍历：数组保存. 第 $seq 小的数字为 $ret")

    ret = find2(array[0], seq)
    println("中序遍历：栈. 第 $seq 小的数字为 $ret")

    var ret2 = find3(array[0], seq)
    println("中序遍历: 递归法. 第 $seq 小的数字为 ${ret2?.value}")
}