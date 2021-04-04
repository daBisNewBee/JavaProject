package offer.list

import offer.ListNode
import offer.buildList
import java.util.*

/*
*
* "输入一个链表，反转链表后，输出链表的所有元素"
*
*  null     1       2       3       4       5
* 2.pre   cur     1.next
*         3.pre   4.cur     next
*
*  几种解法：
*  1. 基本/正规解法：操作指针
*  2. 使用栈过渡：先入栈，后出栈，注意尾节点置空
*  3. 两个链表，每次插入到新链表头节点：头即是尾！
*  4. 递归，注意模板
* */

// (这个好记忆一点)正规解法:如何调整链表指针，来达到反转链表的目的。
fun reverseList(pHead:ListNode):ListNode? {
    if (pHead == null) return null

    var reverseHead:ListNode ?= null
    var curNode:ListNode ?= pHead
    var preNode:ListNode ?= null

    // 记忆规律：每一个等式的右值，是下一个等式的左值；共四个等式
    while (curNode != null) {
        // 1. 保存作用
        var next = curNode.next
        if (next == null) {
            reverseHead = curNode // 其实也就是"preNode"
        }
        // 2. 未反转链表的第一个节点的下个指针指向已反转链表的最后一个节点
        curNode.next = preNode
        // 3.4.指针后移，操作下一个未反转链表的第一个节点
        preNode = curNode
        curNode = next
    }

    return reverseHead
}

// 这个写起来最简单
fun reverseList2(pHead:ListNode):ListNode? {
    var pre:ListNode ?= null
    var cur:ListNode ?= pHead
    var next:ListNode ?= null
    while (cur != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}

// 使用栈解决
fun reverseList3(pHead:ListNode):ListNode? {
    if (pHead == null) return null

    var node:ListNode ?= pHead

    var stack: Stack<ListNode> = Stack()

    while (node != null) {
        stack.push(node)
        node = node?.next
    }
    var reverseHead:ListNode ?= stack.pop()
    var curNode:ListNode ?= reverseHead
    while (!stack.empty()) {
        curNode?.next = stack.pop()
        curNode = curNode?.next
    }
    curNode?.next = null //别忘了，这里要置空！否则会成环！
    return reverseHead
}

// 双链表求解:原链表的结点一个个摘掉，每次摘掉的链表都让他成为新的链表的头结点
fun reverseList4(pHead:ListNode?):ListNode? {
    var cur:ListNode ?= pHead
    var newHead:ListNode ?= pHead
    while (cur != null) {
        var next = cur?.next
        cur?.next = newHead
        newHead = cur
        cur = next
    }
    pHead?.next = null
    return newHead
}

/*
*
* 递归模板：
*
* public ListNode reverseList(参数0) {
    if (终止条件)
        return;

    逻辑处理（可能有，也可能没有，具体问题具体分析）

    //递归调用
    ListNode reverse = reverseList(参数1);

    逻辑处理（可能有，也可能没有，具体问题具体分析）
}
*
* */


fun main() {
    var data = arrayOf(1,2,3,4,5,6,7,8,9)
    var head = ListNode()
    buildList(head, data)
    var reverseHead = reverseList(head)
    while (reverseHead != null) {
        print(reverseHead)
        reverseHead = reverseHead.next
    }

    println()

    head = ListNode()
    buildList(head, data)
    reverseHead = reverseList2(head)
    while (reverseHead != null) {
        print(reverseHead)
        reverseHead = reverseHead.next
    }

    println()

    head = ListNode()
    buildList(head, data)
    reverseHead = reverseList3(head)
    while (reverseHead != null) {
        print(reverseHead)
        reverseHead = reverseHead.next
    }

    println()

    head = ListNode()
    buildList(head, data)
    reverseHead = reverseList4(head)
    while (reverseHead != null) {
        print(reverseHead)
        reverseHead = reverseHead.next
    }
}
