package offer.list

/*
*
* 输入一个复杂链表（每个节点中有节点值，以及两个指针，
* 一个指向下一个节点，另一个特殊指针random指向一个随机节点），
* 请对此链表进行深拷贝，并返回拷贝后的头结点。
*（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
*
* 普通原始思路：
* 先按照next复制，然后依次添加random指针，添加时需要定位random的位置，定位一次需要一次遍历，需要O（n^2）的复杂度。
*
* 稍高级思路：
* 定位random时，根据第一次存下的map，以o(n)的空间复杂度使时间复杂度降到了o(n)
*
* 不容易想到的思路：
* 同样先按next复制，但是把复制后的节点放到原节点后面，则可以很容易的添加random，
* 最后按照奇偶位置拆成两个链表，时间复杂度O（n），不需要额外空间。
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

// 也是用map，但是实现比较简单：第一遍遍历建立新节点，第二遍遍历建立映射关系
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