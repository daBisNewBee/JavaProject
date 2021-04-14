package offer

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