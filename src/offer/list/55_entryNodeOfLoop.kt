package offer.list

import offer.ListNode

/*
* �����л�����ڽ�㣺
* "����һ������������л������ػ�����ڽ�㣬���򣬷���nullptr"
*
* �ⷨһ��(�Ƚϼ�)
* ��ϣ��������map��Ψһ��ֵ�ԣ����ж����o(n)�Ŀռ�
*
* �ⷨ����(�Ƚ����fixme:��Ҫ��ѧ�Ƶ���ʽ)
* ˫ָ�뷨��
* 1. ����ָ�룺��ָ��(ÿ��2��)����ָ��1(ÿ��1��)
* 2. ��һ����������p��
* 3. ��ָ��2��ͷ��㴥��
* 4. ��ָ��1����ָ��2ͬʱ��
* 5. �´�����������ڽ��
*
* ����˼·�ǣ�2*(a+b) = a + b + c + b
*           ��: a = c
*
*           �پ�ȷ�㣺2(A+B)= A+ nB + (n-1)C��
*           (�����ǰ�������ܳ��������̣���ô��ָ����뻷�Ժ����ת�˺ü�Ȧ(����ΪnȦ)�ź���ָ������)
* ˼·��https://blog.nowcoder.net/n/deaa284f105e48f49f38b5d7cb809cd7?f=comment
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
            return slow // slow2Ҳ����
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
    println("�ҵ��Ľ��: $found ƥ��: ${found?.value == REPEAT_NUM}")
    found = entryNodeOfLoop2(head)
    println("�ҵ��Ľ��: $found ƥ��: ${found?.value == REPEAT_NUM}")
}