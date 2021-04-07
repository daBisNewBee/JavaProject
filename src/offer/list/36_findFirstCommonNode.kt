package offer.list

import offer.ListNode
/*
*
* "输入两个链表，找出它们的第一个公共结点。"
*
* 1 - 2 - 3 - 6 - 7
*            /
*     4 - 5
*
*  fixme: 想一想，怎么把复杂的问题转换成另一个简单的问题？比如计算长度不等链表复杂，长度相等链表就简单，而不是直接硬结！！
*
*
*  解法1：(目的都是制造两个长度相等的链表！为什么要长度相等？长度相等了就可以比较了！)
*   双指针法：两个链表相加
*   差值法：比较长度差值，长的先走，再同时走
*
*
*  解法2：
*   map解法，重复元素，联想到map的键是唯一的！！
*
*
*  解法3：
*   stack，入栈后依次出栈，最后一个相等的结点
*
*
* ps:
* 1. 但由于是单向链表的节点，每个节点只有一个next，因此从第一个公共节点开始，
*    之后他们的所有节点都是重合的，不可能再出现分叉
*
* 2. 差值法注意时间复杂度的计算：
*   双指针法：o(m+n)
*   差值法：o(max(m,n) + max(m,n))
*    这两个其实同一个数量级(都是O(n)级别)，但若硬比较，双指针法会快一些！
*
* */

fun findFirstCommonNode(head1:ListNode?, head2:ListNode?):ListNode? {
    if (head1 == null || head2 == null) {
        return null
    }
    var pHead1 :ListNode ?= head1
    var pHead2 :ListNode ?= head2
    while (pHead1 != pHead2) {
        pHead1 = if (pHead1 == null) head2 else pHead1.next
        pHead2 = if (pHead2 == null) head1 else pHead2.next
    }
    return pHead1
}

fun findFirstCommonNode2(head1:ListNode?, head2:ListNode?):ListNode? {
    if (head1 == null || head2 == null) {
        return null
    }

    var pHead1 :ListNode ?= head1
    var pHead2 :ListNode ?= head2
    var found:ListNode ?= null

    var map : MutableMap<ListNode, Int> = HashMap()
    while (pHead1 != null) {
        map[pHead1] = pHead1.value
        pHead1 = pHead1.next
    }
    while (pHead2 != null) {
        var ret = map.put(pHead2, pHead2.value)
        if (ret != null) {
            // 利用"map的唯一键性质"
            found = pHead2
            break
        }
        pHead2 = pHead2.next
    }
    return found

}


fun main(args:Array<String>){
    var data1 = listOf(1,2,3,6,7)
    var data2 = listOf(4,5,6,7)

    var lastNode:ListNode ?= null
    var head1:ListNode ?= null
    var head2:ListNode ?= null
    for (ele in data1) {
        var node = ListNode.obtainNode(ele)
        if (lastNode == null) {
            head1 = node
        }
        lastNode?.next = node
        lastNode = node
    }

    lastNode = null
    for (ele in data2) {
        var node = ListNode.obtainNode(ele)
        if (lastNode == null) {
            head2 = node
        }
        lastNode?.next = node
        lastNode = node
    }


    var retNode = findFirstCommonNode2(head1, head2)
    println(retNode)
    retNode = findFirstCommonNode(head1, head2)
    println(retNode)
}