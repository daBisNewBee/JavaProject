package offer.tree

import offer.TreeNode
import java.util.*

/*
* 60、把二叉树打印成多行
*
* 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
*
* */

fun printAsLine(node:TreeNode?) {
    if (node == null) {
        return
    }
    var queue = LinkedList<TreeNode>()
    queue.offer(node)
    while (queue.isNotEmpty()) {
        var curLevelSize = queue.size
        while (curLevelSize-- > 0) {
            var cur = queue.poll()
            print("${cur.value}")
            if (cur.left != null) {
                queue.offer(cur.left)
            }
            if (cur.right != null) {
                queue.offer(cur.right)
            }
        }
        println()
    }
}

fun main(args:Array<String>) {
    var data = listOf(0,1,2,3,4,5,6)
    var array = offer.createTree(data)
    printAsLine(array[0])
}
