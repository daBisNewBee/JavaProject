package offer.other

import java.util.*

/*
*
* 20、包含min函数的栈
*
* 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
*
* 问题在于：
* pop的时候怎么更新min值？？
*
* 思路：
* 1. 巧用辅助栈
* 2. o(1): 最小值必须位于栈顶结构
*
* */

var dataStack = Stack<Int>()

// 如果仅仅使用一个辅助变量min，则其值可能因为min元素被出栈而失效，所以就使用两个辅助变量？yes！
var minStack = Stack<Int>()

// 使用辅助栈
fun push1(ele:Int) {
    dataStack.push(ele)
    if (minStack.isEmpty()) {
        minStack.push(ele)
    } else {
        minStack.push(if (minStack.peek() > ele) ele else minStack.peek())
    }
}

fun pop1() {
    if (dataStack.isNotEmpty()) {
        minStack.pop()
        dataStack.pop()
    }
}

fun min1():Int {
    return minStack.peek()
}

fun top1():Int {
    return dataStack.peek()
}


// 优化：解决了方法1每次最小值都进栈，占用空间问题
fun push3(ele:Int) {
    dataStack.push(ele)
    if (minStack.isEmpty()) {
        minStack.push(ele)
    } else {
        if (ele <= minStack.peek()) {
            minStack.push(ele)
        }
    }
}

fun pop3() {
    if (dataStack.isNotEmpty()) {
        var cur = dataStack.pop()
        if (minStack.peek() == cur) {
            minStack.pop()
        }
    }
}


fun main() {

    var data = listOf(3,4,2,1)

    println("两个栈: 数据栈 + 辅助栈， 都次都更新栈")
    for (cur in data) {
        push1(cur)
        println("压入 = $cur 当前最小值 = ${min1()}")
    }

    pop1()
    println("弹出。 当前最小值 = ${min1()}")
    pop1()
    println("弹出。 当前最小值 = ${min1()}")

    push1(0)
    println("压入 = 0 当前最小值 = ${min1()}")


    dataStack.clear()

    minStack.clear()

    println("两个栈: 数据栈 + 辅助栈， 有条件更新栈")

    for (cur in data) {
        push3(cur)
        println("压入 = $cur 当前最小值 = ${min1()}")
    }

    pop3()
    println("弹出。 当前最小值 = ${min1()}")
    pop3()
    println("弹出。 当前最小值 = ${min1()}")

    push3(0)
    println("压入 = 0 当前最小值 = ${min1()}")

}