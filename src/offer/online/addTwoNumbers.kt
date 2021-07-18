package offer.online

/**
 *
 * 两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun addTwoNumbers(_l1: ListNode?, _l2: ListNode?): ListNode? {
    var l1 = _l1
    var l2 = _l2
    var carry = 0
    var head:ListNode? = null
    var tail:ListNode? = null
    while(l1 != null || l2 != null) {
        var num1 = l1?.`val` ?: 0
        var num2 = l2?.`val` ?: 0
        var sum = num1 + num2 + carry
        var newNode = ListNode(sum % 10)
        if(head == null) {
            tail = newNode
            head = tail
        } else {
            tail!!.next = newNode
            tail = tail!!.next
        }
        carry = sum / 10
        if(l1 != null) {
            l1 = l1!!.next
        }
        if(l2 != null) {
            l2 = l2!!.next
        }
    }
    // 这里容易忘记！！
    if(carry > 0) {
        tail!!.next = ListNode(carry)
    }
    return head
}

fun main() {
    var data1 = listOf(2,4,9)
//    var data1 = listOf(0)
//    var data1 = listOf(2,4,3)
    var data2 = listOf(5,6,4,9)
//    var data2 = listOf(5,6,4)
    var head1:ListNode? = null
    var head2:ListNode? = null
    var last:ListNode? = null

    data1.forEach {
        var node = ListNode(it)
        last?.next = node
        last = node
        if (head1 == null) {
            head1 = node
        }
    }

    last = null
    data2.forEach {
        var node = ListNode(it)
        last?.next = node
        last = node
        if (head2 == null) {
            head2 = node
        }
    }

    var ret = addTwoNumbers(head1, head2)

    last = ret
    while (last != null) {
        print("${last!!.`val`},")
        last = last!!.next
    }

}