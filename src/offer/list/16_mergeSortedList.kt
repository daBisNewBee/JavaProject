package offer.list

import offer.ListNode
import offer.buildList

/*
*
* "���������������������������������ϳɺ��������Ȼ������Ҫ�ϳɺ���������㵥����������"
*
* ���룺
* {1,3,5},{2,4,6}
* �����
* {1,2,3,4,5,6}
*
* �ⷨ��
* 1. �ݹ飺�Ƚ�������������Ҳ���ͷ������ʼ�Ƚ�����
* fixme:�и��ݹ�˼·����������
*   a. ȷ���ݹ麯���Ĺ���
*           �ϲ�������������������������ͷ���ֵС���Ǹ��ڵ㡣
*   b. ȷ���߽磬��ֹ����
*           ����������ǣ����Ϊ�գ�����ʲô
*   c. ��һ���ĵݹ�����(����һ������С�ݹ������)
*           ���PHead1����ָ�ڵ�ֵС�ڵ���pHead2��ָ�Ľ��ֵ����ôphead1�����ڵ��pHead�ڵ�����ݹ�
*
* 2. �������Ƚϳ��������������������fixme:���׺����ڱ��ڵ�(������)��
*
*
* ����ֻ��Ҫ��ͷ���������жϵ�ǰָ�룬�ĸ������е�ֵС���������ϲ�����ָ�뼴�ɡ�ʹ��"�ݹ�"�Ϳ�������ʵ�֡�
*
*
*
* */
fun merge(head1:ListNode?, head2:ListNode?):ListNode?{
    // 1. ��ֹ����
    if (head1 == null) return head2
    if (head2 == null) return head1
    // 2. �߼�����
    var mergeNode:ListNode
    if (head1.value < head2.value) {
        mergeNode = head1
        // 3. ��ʼ�ݹ�
        mergeNode.next = merge(head1.next, head2)
    } else {
        mergeNode = head2
        mergeNode.next = merge(head1, head2.next)
    }
    return mergeNode
}

// �����汾���
fun merge2(pHead1:ListNode?, pHead2:ListNode?):ListNode? {
    if (pHead1 == null) return pHead2
    if (pHead2 == null) return pHead1

    // �˲������׺��ԣ�һ�㴴��������������һ������ͷ��㣬Ҳ���ڱ�����Ϊ����ÿһ����㶼��һ��ǰ����㡣
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