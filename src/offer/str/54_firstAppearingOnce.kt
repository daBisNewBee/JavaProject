package offer.str

import java.util.*

/*
*
* 54、字符流中第一个不重复的字符
*
* 请实现一个函数用来找出字符流中第一个只出现一次的字符。
* 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
* 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
*
* */

var str:String = ""
var array = IntArray(128) // ASCII 共有128个字符

/*
* 缺点：
* str保存了所有的字符，其实有用的只是不重复字符，重复字符不需要保存，然后再遍历
* */
fun firstAppearingOnce(ch:Char):Char{
    str+=ch
    println("当前: $str")
    array[ch.toInt()]+=1

    for (c in str) {
        if (array[c.toInt()] == 1) {
            return c
        }
    }
    return '#'
}

var array2 = IntArray(128)
// 为了维护字符出现的顺序，我们使用队列（先进先出）这一结构，先出现的不重复字符先输出
var queue = LinkedList<Char>()

// fixme: 解决了方法1"保存很多无用重复字符的缺点"
fun firstAppearingOnce2(ch:Char):Char {
    if (array2[ch.toInt()]++ == 0) {
        queue.offer(ch)
    }
    // 有循环，但是复杂度只是常数级o(128)，因为循环（出队）的最大次数其实就是队列的长度，而队列的长度最大为128
    while (queue.isNotEmpty()) {
        var cur = queue.peek()
        if (cur != null) {
            /*
            * 出队的时候为什还要判断？存在队列中的字符应该都是不重复的，取出队列头的字符就可以吧?
            * 答：TODO： 没太明白
            *  1. 因为这是一个字符流，它的长度是可以随时增长的，假设现在的队头不重复，是字符"K"，
            *     那么，下次增长时就把“K”加到流后面去，如此，队头就变得重复了
            *  2. 队列只阻止了非单身字符的第2~n次入队，没有阻止第一次，出队时才能判断是否真的是单身字符
            *
            * */
            if (array2[cur.toInt()] == 1) {
                return cur
            } else {
                queue.pop()
            }
        }
    }
    return '#'
}

fun main(){
    var data = "google"
    for (c in data) {
        var ret = firstAppearingOnce(c)
        println(ret)
    }

    for (c in data) {
        var ret = firstAppearingOnce2(c)
        println(ret)
    }
}