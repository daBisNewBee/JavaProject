package offer

import java.util.*

fun createTree(data:List<Int>):Array<TreeNode> {
    var arrays: Array<TreeNode> = Array(data.size) { i -> TreeNode(data[i])}

    for (index in 0 until arrays.size/2) {
        arrays[index].left = arrays[2*index+1]
        if (2*index+2 < arrays.size) {
            arrays[index].right = arrays[2*index+2]
        }
    }
    return arrays
}

fun buildList(head:ListNode, data: Array<Int>){
    var node = head
    for (index in data.indices) {
        node.value = data[index]
        if (index < data.size - 1) {
            var next = ListNode()
            node.next = next
            node = next
        } else {
            node.next = null
        }
    }
}

fun buildList(head:ListNode, data: List<Int>){
    var node = head
    for (index in data.indices) {
        node.value = data[index]
        if (index < data.size - 1) {
            var next = ListNode()
            node.next = next
            node = next
        } else {
            node.next = null
        }
    }
}
/*
* 前序、中序、后序其实是 DFS 的三种策略，就特妈一回事
* */

fun printTreePreOrder(root:TreeNode?) {
    if (root == null) return
    print(root.value)
    root.left?.let { printTreePreOrder(root.left) }
    root.right?.let { printTreePreOrder(root.right) }
}

fun printTreeInnOrder(root:TreeNode?) {
    if (root == null) return
    root.left?.let { printTreeInnOrder(root.left) }
    print(root.value)
    root.right?.let { printTreeInnOrder(root.right) }

}

fun printTreePostOrder(root:TreeNode?) {
    if (root == null) return
    root.left?.let { printTreePostOrder(root.left) }
    root.right?.let { printTreePostOrder(root.right) }
    print(root.value)
}

// 广度优先 BFS
fun breadthFirst(root: TreeNode) {

    var queue = LinkedList<TreeNode>()

    queue.offer(root)

    while (!queue.isEmpty()) {
        var cur = queue.poll()
        print(cur.value)
        if (cur.left != null) {
            queue.offer(cur.left)
        }
        if (cur.right != null) {
            queue.offer(cur.right)
        }
    }
}

// 深度优先 DFS (先序),ps:比较常用，记忆简单！
fun depthFirst(root: TreeNode) {

    var stack = Stack<TreeNode>()

    stack.push(root)

    while (!stack.empty()) {
        var cur = stack.pop()
        print(cur.value)
        // 注意顺序，为了先遍历左子树，再遍历右子树。因此此处右子树先入栈
        if (cur.right != null) {
            stack.push(cur.right)
        }
        if (cur.left != null) {
            stack.push(cur.left)
        }
    }
}

// 深度优先 DFS (中序)
fun depthFirstInOrder(root: TreeNode) {
    var stack = Stack<TreeNode>()
    var cur:TreeNode ?= root
    while (!stack.empty() || cur != null) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }
        if (!stack.empty()) {
            cur = stack.pop()
            print(cur.value)
            cur = cur.right
        }

    }
}