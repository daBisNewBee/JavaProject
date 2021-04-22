package offer.tree

import offer.TreeNode
import offer.breadthFirst
import offer.depthFirst
import offer.depthFirstInOrder
import java.util.*

/*
*
* 操作给定的二叉树，将其变换为源二叉树的镜像。
*           8
           /  \
          6   10
         / \  / \
        5  7 9 11

        变成：

        镜像二叉树
            8
           /  \
          10   6
         / \  / \
        11 9 7  5
*
* 解答：
* 交换"每个"节点的左右子树，需要遍历每个节点，其实就是考察"如何遍历树的所有节点"？
*
* 那就联想到了几种常见遍历方式：
* 1. BFS
* 2. DFS (先序、中序、后序)
*
*
* */

/*
* DFS
* */
fun mirror(root:TreeNode?) {
    var tmp: TreeNode?
    var stack = Stack<TreeNode>()
    stack.push(root)
    while (!stack.empty()) {
        var cur = stack.pop()
        tmp = cur.right
        cur.right = cur.left
        cur.left = tmp
        if (cur.left != null) {
            stack.push(cur.left)
        }
        if (cur.right != null) {
            stack.push(cur.right)
        }
    }
}

// 递归简单些
fun mirror2(root:TreeNode?) {
    if (root != null) {
        if (root.left != null || root.right != null) {
            var tmp = root.left
            root.left = root.right
            root.right = tmp
            mirror2(root.left)
            mirror2(root.right)
        }
    }
}

fun main(args:Array<String>) {
    var data = listOf(0,1,2,3,4,5,6,7)
    var arrays: Array<TreeNode> = Array(data.size) { i -> TreeNode(data[i])}

    for (index in 0 until arrays.size/2) {
        arrays[index].left = arrays[2*index+1]
        if (2*index+2 < arrays.size) {
            arrays[index].right = arrays[2*index+2]
        }
    }

    println("原始数据BFS遍历:")

    breadthFirst(arrays[0])

    mirror(arrays[0])

    println("\n镜像后BFS结果:")

    breadthFirst(arrays[0])

    mirror2(arrays[0])

    println("\n镜像*2:")

    println("BFS:")

    breadthFirst(arrays[0])

    println("\nDFS 先序：")

    depthFirst(arrays[0])

    println("\nDFS 中序：")

    depthFirstInOrder(arrays[0])
}