package offer.list

import offer.ListNode
/*
*
* "�������������ҳ����ǵĵ�һ��������㡣"
*
* 1 - 2 - 3 - 6 - 7
*            /
*     4 - 5
*
*  fixme: ��һ�룬��ô�Ѹ��ӵ�����ת������һ���򵥵����⣿������㳤�Ȳ��������ӣ������������ͼ򵥣�������ֱ��Ӳ�ᣡ��
*
*
*  �ⷨ1��(Ŀ�Ķ�����������������ȵ�����ΪʲôҪ������ȣ���������˾Ϳ��ԱȽ��ˣ�)
*   ˫ָ�뷨�������������
*   ��ֵ�����Ƚϳ��Ȳ�ֵ���������ߣ���ͬʱ��
*
*
*  �ⷨ2��
*   map�ⷨ���ظ�Ԫ�أ����뵽map�ļ���Ψһ�ģ���
*
*
*  �ⷨ3��
*   stack����ջ�����γ�ջ�����һ����ȵĽ��
*
*
* ps:
* 1. �������ǵ�������Ľڵ㣬ÿ���ڵ�ֻ��һ��next����˴ӵ�һ�������ڵ㿪ʼ��
*    ֮�����ǵ����нڵ㶼���غϵģ��������ٳ��ֲַ�
*
* 2. ��ֵ��ע��ʱ�临�Ӷȵļ��㣺
*   ˫ָ�뷨��o(m+n)
*   ��ֵ����o(max(m,n) + max(m,n))
*    ��������ʵͬһ��������(����O(n)����)������Ӳ�Ƚϣ�˫ָ�뷨���һЩ��
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
            // ����"map��Ψһ������"
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