package offer.list

/*
*
* ����һ����������ÿ���ڵ����нڵ�ֵ���Լ�����ָ�룬
* һ��ָ����һ���ڵ㣬��һ������ָ��randomָ��һ������ڵ㣩��
* ��Դ������������������ؿ������ͷ��㡣
*��ע�⣬���������벻Ҫ���ز����еĽڵ����ã�������������ֱ�ӷ��ؿգ�
*
* ��ͨԭʼ˼·��
* �Ȱ���next���ƣ�Ȼ���������randomָ�룬���ʱ��Ҫ��λrandom��λ�ã���λһ����Ҫһ�α�������ҪO��n^2���ĸ��Ӷȡ�
*
* �Ը߼�˼·��
* ��λrandomʱ�����ݵ�һ�δ��µ�map����o(n)�Ŀռ临�Ӷ�ʹʱ�临�ӶȽ�����o(n)
*
* �������뵽��˼·��
* ͬ���Ȱ�next���ƣ����ǰѸ��ƺ�Ľڵ�ŵ�ԭ�ڵ���棬����Ժ����׵����random��
* �������żλ�ò����������ʱ�临�Ӷ�O��n��������Ҫ����ռ䡣
*
* */


class ListNode {
    var value:Int = 0
    var next:ListNode ?= null
    var random:ListNode ?= null

    fun cloneNode():ListNode{
        var new = ListNode()
        new.value = this.value
        return new
    }

    override fun toString(): String {
        return "ListNode(value=$value ${random?.value})->"
    }
}

fun cloneList(pHead:ListNode?):ListNode? {
    if (pHead == null) return null
    var map:MutableMap<ListNode, ListNode> = HashMap()

    var head:ListNode ?= pHead
    var newHead:ListNode? = null
    var cur:ListNode ?= head
    var last:ListNode ?= null

    while (cur != null) {
        var newNode = cur.cloneNode()
        if (newHead == null) {
            newHead = newNode
        }
        last?.next = newNode
        map[cur] = newNode
        last = newNode
        cur = cur.next
    }

    cur = head

    while (cur != null) {
        if (cur.random != null) {
            var curNew = map[cur]
            var curRandomNew = map[cur.random!!]
            curNew?.random = curRandomNew
        }
        cur = cur.next
    }
    return newHead
}

// Ҳ����map������ʵ�ֱȽϼ򵥣���һ����������½ڵ㣬�ڶ����������ӳ���ϵ
fun cloneList2(pHead:ListNode?):ListNode? {
    if (pHead == null) return null
    var map:MutableMap<ListNode, ListNode> = HashMap()

    var newHead = pHead.cloneNode()
    var cur:ListNode ?= pHead
    var curNew:ListNode ?= newHead

    while (cur != null) {
        map[cur] = cur.cloneNode()
        cur = cur.next
    }

    cur = pHead
    while (cur != null) {
        curNew?.next = map[cur.next]
        curNew?.random = map[cur.random]
        cur = cur.next
        curNew = curNew?.next
    }
    return newHead
}

fun main(args:Array<String>) {
    var data = Array(5){ListNode()}
    var last:ListNode? = null
    for (index in data.indices) {
        data[index].value = index
        last?.next = data[index]
        last = data[index]
    }

    data[0].random = data[3]
    data[2].random = data[4]

    var head = data[0]
    var cur:ListNode ? = head
    while (cur != null) {
        println(cur)
        cur = cur.next
    }

    println()

    var ret = cloneList(head)
    while (ret != null) {
        println(ret)
        ret = ret.next
    }

    println()

    ret = cloneList2(head)
    while (ret != null) {
        println(ret)
        ret = ret.next
    }


}