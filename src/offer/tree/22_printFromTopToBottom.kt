package offer.tree

import offer.TreeNode
import java.util.*

/**
 *
 * 22、从上往下打印二叉树
 *
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 */

fun printFromTopToBottom(root:TreeNode?) {

    if (root == null) return

    var queue = LinkedList<TreeNode>()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        var cur = queue.poll()
        print("" + cur.value+",")
        if (cur.left != null) {
            queue.offer(cur.left)
        }
        if (cur.right != null) {
            queue.offer(cur.right)
        }
    }

}

fun main(){
    var data = listOf(1,2,3,4,5,6,7)
    var array = offer.createTree(data)
    printFromTopToBottom(array[0])
}