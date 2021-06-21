package offer.other

import java.util.*

/*
* 21、栈的压入、弹出序列
*
* 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
* 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
* 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
* 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
*
* 如果，
* 1,2,3,4,5
* 是栈的压入顺序
*
* 那么，
* 4,5,3,2,1
* 就是出栈顺序，
*
* 但是4,3,5,1,2
* 不是出栈顺序
*
* 思路：
*   模拟压栈和出栈过程
  规律是：
    根据弹出序列，如果下一个弹出的元素正好是栈顶元素，则弹出；
    如果下一个弹出的数字不是栈顶元素，则将还未进栈的元素依次进栈，
    直到找到要弹出的那个数字；
    最终，如果所有数字都压入栈中还没有找到弹出元素，则说明不是一个弹出序列
*
* */

fun isPopOrder(pushA:List<Int>, popA:List<Int>):Boolean {

    if (pushA.isEmpty() || popA.isEmpty()) return false

    var len = popA.size

    // 进栈序列下标
    var p = 0

    var stack = Stack<Int>()

    for (i in 0 until len) {
        if (stack.isNotEmpty() &&
                popA[i] == stack.peek()) {
            // 弹出的元素正好是栈顶元素
            stack.pop()
        } else {
            // 下一个弹出的数字不是栈顶元素
            while (p < len && pushA[p] != popA[i]) {
                // 所有数字都压入栈中还没有找到弹出元素，则说明不是一个弹出序列
                stack.push(pushA[p++])
            }
            p++
        }
    }
    return stack.isEmpty()
}


// 比较简洁巧妙：
// 思路：新建一个栈，将数组A压入栈中，当栈顶元素等于数组B时，就将其出栈，当循环结束时，判断栈是否为空，若为空则返回true.
fun isPopOrder2(pushA:List<Int>, popA:List<Int>):Boolean {
    if (pushA.isEmpty() || popA.isEmpty()
            || pushA.size != popA.size) {
        return false
    }
    var stack = Stack<Int>()
    var j = 0
    for (ele in pushA) {
        stack.push(ele)

        while (stack.isNotEmpty()
                && popA[j] == stack.peek()) {
            stack.pop()
            j++
        }
    }
    return stack.isEmpty()
}

fun main(){
    var pushA = listOf(1,2,3,4,5)

    var popA = listOf(4,5,3,2,1)

    var popB = listOf(4,3,5,1,2)

    var ret = isPopOrder2(pushA, popA)

    println("$popA 结果:$ret")

    ret = isPopOrder2(pushA, popB)

    println("$popB 结果:$ret")
}