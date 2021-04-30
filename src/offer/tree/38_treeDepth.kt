package offer.tree

import offer.TreeNode
import offer.treeDepth
import java.util.*

/*
*
* 二叉树的深度
*
* 递归法：
* 二叉树的深度 = max(左子树高度，右子树高度) + 1
*
* 层次遍历：(不容易想到)
* 遍历完一层的所有节点，高度+1
* 关键：如何知道当前层的节点总数？节点出队前！
*
* */

/* 自己写的稍繁琐
private fun treeDepth(node:TreeNode?):Int {
    if (node == null) return 0
    if (node.left == null
            && node.right == null) return 1
    if (node.left == null)
        return treeDepth(node.right) + 1
    if (node.right == null)
        return treeDepth(node.left) + 1
    return Math.max(treeDepth(node.left), treeDepth(node.right)) + 1
}
*/


// 层次遍历
private fun treeDepth3(node: TreeNode?):Int {
    if (node == null) return 0
    var queue: Deque<TreeNode> = ArrayDeque()
    queue.offer(node)
    var depth = 0
    while (queue.isNotEmpty()) {
        // 这是关键点！未出队列前，表示当前层总的节点数！
        var curSize = queue.size
        while (curSize-- > 0) {
            var cur = queue.poll()
            if (cur.left != null) queue.offer(cur.left)
            if (cur.right != null) queue.offer(cur.right)
        }
        depth++
    }
    return depth
}

fun main(args:Array<String>) {

    var data = listOf(0,1,2,3,4,5,6,7)

    var arrays = offer.createTree(data)

    var depth = treeDepth(arrays[0])

    println("递归法/分治法： depth: $depth")

    depth = treeDepth3(arrays[0])
    println("层次遍历：depth: $depth")
}