package offer.other

import java.util.*

/*
*
* 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
*
* 两个栈实现一个队列:
* 本质是，两个"后进先出"的栈，数据倒腾两次，就成了"先进先出"
*
* 扩展，
* 两个队列实现一个栈：
* 本质是，两个"先进先出"的队列，数据倒腾两次，就成了"后进先出"
*
* */

// 两个栈实现一个队列: 一个用来放数据，一个用来取数据时的过渡
var stack1 = Stack<Int>()
var stack2 = Stack<Int>()

fun push(ele:Int){
    stack1.push(ele)
}

fun pop():Int {
    if (stack2.isEmpty()) {
        // 为空时将栈1的元素“倒进去”
        while (stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }
    }
    if (stack2.isNotEmpty()) {
        return stack2.pop()
    }
    return -1
}

// 两个队列实现一个栈: 入队：找到非空的队列；出队：找到非空队列，出队直到最后一个数据，另一个队列依旧做过渡
var queue1 = LinkedList<Int>()
var queue2 = LinkedList<Int>()

fun push2(ele:Int) {
    if (queue1.isNotEmpty()) {
        queue1.offer(ele)
    } else {
        queue2.offer(ele)
    }
}

fun pop2():Int {
    while (queue1.isNotEmpty()) {
        var tmp = queue1.pop()
        if (queue1.isEmpty()) {
            return tmp
        }
        queue2.offer(tmp)
    }
    while (queue2.isNotEmpty()) {
        var tmp = queue2.pop()
        if (queue2.isEmpty()) {
            return tmp
        }
        queue1.offer(tmp)
    }
    return -1
}

fun main(){
    var data = listOf(1,2,3,4,5,6,7,8)
    for (ele in data) {
        push(ele)
    }
    println("两个栈实现一个队列:")
    var ret = pop()
    while (ret != -1) {
        println(ret)
        ret = pop()
    }

    for (ele in data) {
        push2(ele)
    }
    println("两个队列实现一个栈:")
    ret = pop2()
    while (ret != -1) {
        println(ret)
        ret = pop2()
    }
}