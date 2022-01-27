package leetcode.editor.cn

import offer.TreeNode
import offer.createTree


var sum = 0

fun dfs(node: TreeNode?) {

    node ?: return

    if (node.right != null) {
        dfs(node.right)
    }

    sum += node.value

    node.value = sum

    if (node.left != null) {
        dfs(node.left)
    }
}

fun main() {
    var root = createTree(listOf(10,6,15,4,7,12,16))
    dfs(root[0])
    println(sum)
}