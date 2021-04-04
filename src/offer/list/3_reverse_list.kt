import offer.ListNode
import offer.buildList
import java.util.*
import kotlin.collections.ArrayList

/*
* "��β��ͷ��ӡ����":
*
* ˼·��
*
* 1. ����ջ��"����ȳ�"������
*    ��Ϊ������Stack p0
*    ϵͳ��"ջ"(�ݹ�) p1 (�����)
*
* 2. �ǵݹ飺���飬ÿ���ڵ�0λ�ã���������(ʱ�临�Ӷȣ�O��n^2��) p2
*
* 3. ��ͨʵ��:��ת�����ٱ��� TODO
*
* Ҫ����ı������⣺����������������
*   while (head != null) {
        head = head.next
    }
* */


// "ջ"�����ԣ�����ȳ������ø����Թ���
fun printListFromTailToHead1(head: ListNode):List<Int> {
    var result:MutableList<Int> = ArrayList()
    var stack:Stack<ListNode> = Stack()
    var node:ListNode ?= head

    while (node != null) {
        stack.push(node)
        node = if (node.next != null) node.next!! else null
    }
    println("stack size��${stack.size}")
    // stack�������vector������reverseһ�¾�����

    while (stack.isNotEmpty()) {
        var nodePop = stack.pop()
        result.add(nodePop.value)
    }
    return result
}

// �ǵݹ飺ÿ�η�������ĵ�0λ��
fun printListFromTailToHead2(head:ListNode):List<Int> {
    var result:MutableList<Int> = ArrayList()
    var node:ListNode ?= head
    while (node != null) {
        result.add(0, node.value)// warning:������
        node = if (node.next != null) node.next!! else null
    }
    return result
}


// �ݹ飺����ϵͳ��"ջ"��æ��ӡ
var gResult:MutableList<Int> = ArrayList()

fun printListFromTailToHead3(head:ListNode?):List<Int> {
    if (head != null) {
        if (head.next != null) {
            printListFromTailToHead3(head.next!!)
        }
        gResult.add(head.value)
    }
    return gResult
}

// ��ת����TODO ʵ�����鷳������ʵ����Ҫ����



// ��ͷ��β��ӡ����
fun main(){
    var data = arrayOf(1,2,3,4,5,6,7,8,9)
    var head = ListNode()
    buildList(head, data)

    var result = printListFromTailToHead1(head)
    println(result)

    result = printListFromTailToHead2(head)
    println(result)

    result = printListFromTailToHead3(head)
    println(result)
}