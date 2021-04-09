package offer.list

import offer.ListNode
import offer.buildList

/*
*
* 56、删除链表中重复的结点
*
* 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
* 重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5。
*
* 关键点：
* 1. 要有pre指针！可以保证删除后，链表的联通性！
* 2. 要头结点！存在头结点就重复的情况
*
* */

fun deleteDuplication(pHead:ListNode):ListNode? {
    var head = ListNode()
    head.next = pHead

    var pre:ListNode ?= head
    var cur:ListNode ?= pHead
    while (cur?.next != null) {
        if (cur.value != cur.next?.value) {
            pre = cur
            cur = cur.next
        } else {
            var value = cur.value
            while (cur?.value == value) {
                cur = cur?.next
            }
            pre?.next = cur
        }
    }
    return head.next
}

fun main(args:Array<String>) {
    var data = listOf(1,1,2,3,3,3,4,4,5)
    var head = ListNode()
    buildList(head, data)
    var ret = deleteDuplication(head)
    while (ret != null) {
        println(ret)
        ret = ret.next
    }
}