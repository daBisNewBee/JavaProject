package offer.str

import java.util.*

/*
*
* 43、左旋转字符串
*
* 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
* 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
* 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
* 要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
*
* 输入：
    "abcXYZdef",3
 返回值：
    "XYZdefabc"
*
* */

/*
*
* 反转三次，时间复杂度o(n)
*
*
* fixme: 巧妙！本质是把字符串看为两个小整体A、B，为了不破坏A、B内顺序，事先对A、B反转一次，最后反转整体AB
*
* */
fun leftRotateString(str:String, _n:Int):String? {
    if (str.isEmpty()) return null
    var n = _n
    n %= str.length
    if (n <= 0) return null

    var array = str.toCharArray()
    reverse(array, 0, n-1)
    reverse(array, n, str.length-1)
    reverse(array, 0, str.length-1)
    return String(array)
}

fun reverse(array:CharArray, _begin:Int, _end:Int) {
    var begin = _begin
    var end = _end
    while (begin < end) {
        var tmp = array[begin]
        array[begin] = array[end]
        array[end] = tmp
        begin++
        end--
    }
}

// 队列
fun leftRotateString2(str:String, _n:Int):String? {
    if (str.isEmpty()) return null
    var n = _n
    n %= str.length
    if (n<=0) return null
    var array = str.toCharArray()
    var queue = ArrayDeque<Char>()
    for (c in array) {
        queue.offer(c)
    }
    while (n-- > 0) {
        var cur = queue.poll()
        queue.offer(cur)
    }
    array = CharArray(queue.size)
    for (i in 0 until queue.size) {
        array[i] = queue.poll()
    }

    return String(array)
}

fun leftRotateString3(str:String, _n:Int):String? {
    if (str.isEmpty()) return null
    var n = _n
    n %= str.length
    if (n<=0) return null
    return str.substring(n, str.length) + str.substring(0, n)
}

fun main() {
    var data = "abcdefg"
    var ret = leftRotateString(data, 2)
    println("ret:$ret")
    ret = leftRotateString2(data, 2)
    println("ret:$ret")
    ret = leftRotateString3(data, 2)
    println("ret:$ret")
}