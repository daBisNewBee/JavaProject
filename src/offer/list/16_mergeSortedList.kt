package offer.list

import offer.ListNode
import offer.buildList

/*
*
* "输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。"
*
* 输入：
* {1,3,5},{2,4,6}
* 输出：
* {1,2,3,4,5,6}
*
* 解法：
* 1. 递归：比较巧妙，但是容易找不到头绪，开始比较困难
* fixme:有个递归思路：！！！！
*   a. 确定递归函数的功能
*           合并两个单链表，返回两个单链表头结点值小的那个节点。
*   b. 确定边界，终止条件
*           对于链表就是，如果为空，返回什么
*   c. 下一步的递归区间(函数一定是缩小递归区间的)
*           如果PHead1的所指节点值小于等于pHead2所指的结点值，那么phead1后续节点和pHead节点继续递归
*
* 2. 迭代：比较常规的做法，创建新链表，fixme:容易忽略哨兵节点(虚拟结点)！
*
*
* 我们只需要从头遍历链表，判断当前指针，哪个链表中的值小，即赋给合并链表指针即可。使用"递归"就可以轻松实现。
*
*
*
* */
fun merge(head1:ListNode?, head2:ListNode?):ListNode?{
    // 1. 终止条件
    if (head1 == null) return head2
    if (head2 == null) return head1
    // 2. 逻辑处理
    var mergeNode:ListNode
    if (head1.value < head2.value) {
        mergeNode = head1
        // 3. 开始递归
        mergeNode.next = merge(head1.next, head2)
    } else {
        mergeNode = head2
        mergeNode.next = merge(head1, head2.next)
    }
    return mergeNode
}

// 迭代版本求解
fun merge2(pHead1:ListNode?, pHead2:ListNode?):ListNode? {
    if (pHead1 == null) return pHead2
    if (pHead2 == null) return pHead1

    // 此步骤容易忽略！一般创建单链表，都会设一个虚拟头结点，也叫哨兵，因为这样每一个结点都有一个前驱结点。
    var mergeNode = ListNode()
    mergeNode.value = -1
    var curNode:ListNode ?= mergeNode

    var head1:ListNode ?= pHead1
    var head2:ListNode ?= pHead2

    while (head1 != null && head2 != null) {
        if (head1.value <= head2.value) {
            curNode?.next = head1
            head1 = head1.next
        } else{
            curNode?.next = head2
            head2 = head2.next
        }
        curNode = curNode?.next
    }
    curNode?.next = head1 ?: head2
    return mergeNode.next
}

fun main(args:Array<String>){
    var data1 = arrayOf(1,3,5,7,9)
    var data2 = arrayOf(2,4,6,8,10)
    var head1 = ListNode()
    var head2 = ListNode()
    buildList(head1, data1)
    buildList(head2, data2)
    var mergeList = merge(head1, head2)
    while (mergeList != null) {
        print(mergeList.value)
        mergeList = mergeList.next
    }

    println()

    head1 = ListNode()
    head2 = ListNode()
    buildList(head1, data1)
    buildList(head2, data2)
    mergeList = merge2(head1, head2)
    while (mergeList != null) {
        print(mergeList.value)
        mergeList = mergeList.next
    }
}