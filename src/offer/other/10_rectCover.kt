package offer.other

/*
*
* 10、矩形覆盖
*
* 我们可以用2 X 1的小矩形横着或者竖着去覆盖更大的矩形。
* 请问用n个2 X 1的小矩形无重叠地覆盖一个2 X n的大矩形，总共有多少种方法？
*
* fixme: 想如何把问题抽象成公式？
* f(8)=f(7)+f(6)，不难看出这仍然是斐波那契数列
*
* */

fun rectCover(n:Int):Int {
    if (n <= 2) return n
    var first = 1
    var second = 2
    var res = 0
    for (i in 3..n) {
        res = first + second
        first = second
        second = res
    }
    return res
}

fun main(){
    var data = listOf(2,5,8,10,12)
    data.forEach {
        var ret = rectCover(it)
        println("$ret")
    }
}