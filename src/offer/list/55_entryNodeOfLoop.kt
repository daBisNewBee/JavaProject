package offer.list

import offer.ListNode

/*
* 链表中环的入口结点：
* "给定一个单链表，如果有环，返回环的入口结点，否则，返回nullptr"
*
* 解法一：(比较简单)
* 哈希法，利用map的唯一键值性，会有额外的o(n)的空间
*
* 解法二：(比较巧妙，fixme:需要数学推导公式)
* 双指针法：
* 1. 两个指针：快指针(每次2步)、慢指针1(每次1步)
* 2. 第一次相遇，是p点
* 3. 慢指针2从头结点触发
* 4. 慢指针1、慢指针2同时走
* 5. 下次相遇就是入口结点
*
* 核心思路是：2*(a+b) = a + b + c + b
*           即: a = c
*
*           再精确点：2(A+B)= A+ nB + (n-1)C）
*           (如果环前面的链表很长，而环短，那么快指针进入环以后可能转了好几圈(假设为n圈)才和慢指针相遇)
* 思路：https://blog.nowcoder.net/n/deaa284f105e48f49f38b5d7cb809cd7?f=comment
*
* */

fun entryNodeOfLoop(head:ListNode?):ListNode? {
    if (head == null) {
        return null
    }
    var pHead:ListNode ?= head
    var set:MutableSet<ListNode> = HashSet()
    while (pHead != null) {
        if (set.add(pHead)) {
            pHead = pHead.next
        } else {
            return pHead
        }
    }
    return null
}

fun entryNodeOfLoop2(head:ListNode?):ListNode? {
    if (head == null) {
        return null
    }

    var slow:ListNode ?= head
    var slow2:ListNode ?= head
    var fast:ListNode ?= head

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast?.next?.next
        if (slow == fast) {
            while (slow != slow2) {
                slow = slow?.next
                slow2 = slow2?.next
            }
            return slow // slow2也可以
        }

    }
    return null
}

fun main(args:Array<String>) {
    val REPEAT_NUM = 3
    val data = listOf(1,2,3,4,5,6,7)
    var head:ListNode ?= null

    var lastNode:ListNode ?= null
    for (ele in data) {
        var node = ListNode.obtainNode(ele)
        if (lastNode != null) {
            lastNode.next = node
        } else {
            head = node
        }
        lastNode = node
    }
    lastNode?.next = ListNode.obtainNode(REPEAT_NUM)


    var found = entryNodeOfLoop(head)
    println("找到的结点: $found 匹配: ${found?.value == REPEAT_NUM}")
    found = entryNodeOfLoop2(head)
    println("找到的结点: $found 匹配: ${found?.value == REPEAT_NUM}")
}