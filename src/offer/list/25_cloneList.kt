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


class Node(var `val`:Int) {

    var next:Node ?= null
    var random:Node ?= null


    override fun toString(): String {
        return "Node(value=$`val` ${random?.`val`})->"
    }
}

/**
 * 只要遍历一次，时间o(n),空间o(n)
 * TODO: 但是把复制后的节点放到原节点后面，空间o(1)
 */

var visited:MutableMap<Node, Node> = HashMap()

fun getClonedNode(node:Node?):Node? {
    if(node == null) return null
    if(!visited.containsKey(node)) {
        var newNode = Node(node.`val`)
        visited[node] = newNode
    }
    return visited[node]
}

fun copyRandomList(node: Node?): Node? {
    if(node == null) return null
    var cur = node
    var curNew = getClonedNode(cur)
    while(cur != null) {
        curNew?.next = getClonedNode(cur!!.next)
        curNew?.random = getClonedNode(cur!!.random)
        cur = cur.next
        curNew = curNew?.next
    }
    return visited[node]
}

fun main() {
    var data = Array(5){Node(0)}
    var last:Node? = null
    for (index in data.indices) {
        data[index].`val` = index
        last?.next = data[index]
        last = data[index]
    }

    data[0].random = data[3]
    data[2].random = data[4]

    var head = data[0]
    var cur:Node ? = head
    while (cur != null) {
        println(cur)
        cur = cur.next
    }

    println()

    var ret = copyRandomList(head)
    while (ret != null) {
        println(ret)
        ret = ret.next
    }

}