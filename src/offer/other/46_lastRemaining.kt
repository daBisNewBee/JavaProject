package offer.other


/**
 *
 * 46、圆圈中最后剩下的数
 *
 * 题目抽象：给定一个由[0...n-1]构成的数组，第一次从0开始数m个数，然后删除，
 * 以后每次都从删除的数下一个位置开始数m个数，然后删除，直到剩余一个数字，找出那个数字。
 */

// 约瑟夫环
fun lastRemaining(n:Int, m:Int):Int {
    println("n:$n m:$m")
    if (n < 1 || m < 1) return -1
    var last = 0
    for (i in 2.. n) {
        last = (last + m) % n
        println("last:$last")
    }
    return last
}

fun main(){
    var data = intArrayOf(3,4)

    data.forEach {
        var ret = lastRemaining(5, it)
        println("$ret")
    }
}