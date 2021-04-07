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