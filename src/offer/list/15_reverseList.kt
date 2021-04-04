package offer.list

import offer.ListNode
import offer.buildList
import java.util.*

/*
*
* "����һ��������ת�����������������Ԫ��"
*
*  null     1       2       3       4       5
* 2.pre   cur     1.next
*         3.pre   4.cur     next
*
*  ���ֽⷨ��
*  1. ����/����ⷨ������ָ��
*  2. ʹ��ջ���ɣ�����ջ�����ջ��ע��β�ڵ��ÿ�
*  3. ��������ÿ�β��뵽������ͷ�ڵ㣺ͷ����β��
*  4. �ݹ飬ע��ģ��
* */

// (����ü���һ��)����ⷨ:��ε�������ָ�룬���ﵽ��ת�����Ŀ�ġ�
fun reverseList(pHead:ListNode):ListNode? {
    if (pHead == null) return null

    var reverseHead:ListNode ?= null
    var curNode:ListNode ?= pHead
    var preNode:ListNode ?= null

    // ������ɣ�ÿһ����ʽ����ֵ������һ����ʽ����ֵ�����ĸ���ʽ
    while (curNode != null) {
        // 1. ��������
        var next = curNode.next
        if (next == null) {
            reverseHead = curNode // ��ʵҲ����"preNode"
        }
        // 2. δ��ת����ĵ�һ���ڵ���¸�ָ��ָ���ѷ�ת��������һ���ڵ�
        curNode.next = preNode
        // 3.4.ָ����ƣ�������һ��δ��ת����ĵ�һ���ڵ�
        preNode = curNode
        curNode = next
    }

    return reverseHead
}

// ���д�������
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

// ʹ��ջ���
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
    curNode?.next = null //�����ˣ�����Ҫ�ÿգ������ɻ���
    return reverseHead
}

// ˫�������:ԭ����Ľ��һ����ժ����ÿ��ժ��������������Ϊ�µ������ͷ���
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
* �ݹ�ģ�壺
*
* public ListNode reverseList(����0) {
    if (��ֹ����)
        return;

    �߼����������У�Ҳ����û�У�����������������

    //�ݹ����
    ListNode reverse = reverseList(����1);

    �߼����������У�Ҳ����û�У�����������������
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
