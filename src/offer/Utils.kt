package offer

import java.util.*

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
* ǰ�����򡢺�����ʵ�� DFS �����ֲ��ԣ�������һ����
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

// ������� BFS
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

// ������� DFS (����),ps:�Ƚϳ��ã�����򵥣�
fun depthFirst(root: TreeNode) {

    var stack = Stack<TreeNode>()

    stack.push(root)

    while (!stack.empty()) {
        var cur = stack.pop()
        print(cur.value)
        // ע��˳��Ϊ���ȱ������������ٱ�������������˴˴�����������ջ
        if (cur.right != null) {
            stack.push(cur.right)
        }
        if (cur.left != null) {
            stack.push(cur.left)
        }
    }
}

// ������� DFS (����)
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