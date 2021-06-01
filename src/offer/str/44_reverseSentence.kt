package offer.str

import java.lang.StringBuilder
import java.util.*

/*
*
* 44、反转单词序列
*
* 输入 student. a am I
* 输出 I am a student.
*
* fixme: 类似"左旋转字符串"，反转多次，这里需要注意对单词的判断
* */

fun reverseSentence(str:String):String {
    if (str.isEmpty()) return ""
    var array = str.split(' ')
    var stack = Stack<String>()

    for (s in array) {
        stack.push(s)
    }
    var len = stack.size
    var sb = StringBuilder()
    // 解法二： 从后向前遍历
    for (i in stack.indices) {
        sb.append(stack.pop())
        // 注意：不能用"i != stack.size-1"，因为 "stack.size"在不断变化
        if (i != len-1) {
            sb.append(" ")
        }
    }
    return sb.toString()
}

fun reverseSentence2(str:String):String {
    if (str.isEmpty()) return ""
    var array = str.toCharArray()
    reverse(array, 0, str.length-1)
    var begin = 0
    var end = 0
    // 两个指针，来判断一个单词的起始位置
    while (end <= str.length) {
        if (end == str.length || array[end] == ' ') {
            reverse(array, begin, end-1)
            begin = end+1
        }
        end++
    }
    return String(array)
}



fun main() {
    var ret = reverseSentence("nowcoder. a am I")
    println("ret:$ret")
    ret = reverseSentence2("nowcoder. a am I")
    println("ret:$ret")
}