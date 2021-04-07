package offer.list

import offer.ListNode
import offer.buildList


/*
*
* "输入一个链表，输出该链表中倒数第k个结点。如果该链表长度小于k，请返回空。"
*
*
* 思路：
* 1. 倒数第k个结点，即求的是：最后一个节点与倒数第k个结点相距k-1
* 2. 因此，找到最后一个节点；找到相距最后一个节点k-1距离的节点，就找到倒数第k个结点了
* 2.1. 相距k-1的两个节点：两个节点，第一个先走k-1；再同时走
* 2.2. 倒数第一个节点：next 为空就行
*
* */
fun findKthToTail(pHead:ListNode, k:Int):ListNode? {
    if (pHead == null || k <= 0) return null
    var head = pHead
    var tail = pHead

    for (i in 0 until k - 1) {
        if (head?.next != null) {
            head = head.next!!
        } else {
            return null
        }
    }
    while (head?.next != null) {
        head = head.next!!
        tail = tail.next!!
    }
    return tail
}

fun main(args:Array<String>){
    var data = arrayOf(1,2,3,4,5,6,7,8,9)
    var head = ListNode()
    buildList(head, data)
    var kNode = findKthToTail(head, 4) // "6"
    print("找到的倒数第k个结点是：$kNode")
}