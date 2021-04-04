import offer.ListNode
import offer.buildList
import java.util.*
import kotlin.collections.ArrayList

/*
* "从尾到头打印链表":
*
* 思路：
*
* 1. 利用栈的"后进先出"的特性
*    人为的制造Stack p0
*    系统的"栈"(递归) p1 (易溢出)
*
* 2. 非递归：数组，每次在第0位置，插入数据(时间复杂度：O（n^2）) p2
*
* 3. 普通实现:反转链表，再遍历 TODO
*
* 要解决的本质问题：单链表遍历是正向的
*   while (head != null) {
        head = head.next
    }
* */


// "栈"的特性：后进先出，利用该特性过渡
fun printListFromTailToHead1(head: ListNode):List<Int> {
    var result:MutableList<Int> = ArrayList()
    var stack:Stack<ListNode> = Stack()
    var node:ListNode ?= head

    while (node != null) {
        stack.push(node)
        node = if (node.next != null) node.next!! else null
    }
    println("stack size：${stack.size}")
    // stack如果换成vector，这里reverse一下就行了

    while (stack.isNotEmpty()) {
        var nodePop = stack.pop()
        result.add(nodePop.value)
    }
    return result
}

// 非递归：每次放入数组的第0位置
fun printListFromTailToHead2(head:ListNode):List<Int> {
    var result:MutableList<Int> = ArrayList()
    var node:ListNode ?= head
    while (node != null) {
        result.add(0, node.value)// warning:会扩容
        node = if (node.next != null) node.next!! else null
    }
    return result
}


// 递归：借助系统的"栈"帮忙打印
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

// 反转链表：TODO 实现略麻烦，但是实现需要掌握



// 从头到尾打印链表
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