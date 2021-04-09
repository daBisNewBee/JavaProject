package offer.list

import offer.ListNode
import offer.buildList

/*
*
* 56��ɾ���������ظ��Ľ��
*
* ��һ������������У������ظ��Ľ�㣬��ɾ�����������ظ��Ľ�㣬
* �ظ��Ľ�㲻��������������ͷָ�롣 ���磬����1->2->3->3->4->4->5 �����Ϊ 1->2->5��
*
* �ؼ��㣺
* 1. Ҫ��preָ�룡���Ա�֤ɾ�����������ͨ�ԣ�
* 2. Ҫͷ��㣡����ͷ�����ظ������
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