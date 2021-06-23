package offer.other

/*
*
* 7、斐波那契数列
*
* 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。假设n<=39。
*
* 斐波那契数列：0,1,1,2,3,5,8........ 总结起来就是：第一项是0，第二项是1，后续第n项为第n-1项和第n-2项之和。
*
* fixme: 注意区别：！！
* 递归是：从上往下，然后再从下往上回溯的，最后回溯的时候来合并子树从而求得答案
* 动态规划是：从下往上，直接从子树求得答案
*
* */

// 递归法。有很多重复计算
fun fibonacci(n:Int):Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fibonacci(n - 1) + fibonacci(n - 2)
}

// 不会重复计算，但有递归栈空间
fun fibonacci2(n:Int):Int {
    if (n == 0) return 0
    if (n == 1) return 1
    if (array[n] > 0) return array[n]
    array[n] = fibonacci2(n-1) + fibonacci2(n-2)
    return array[n]
}

// 不会有递归栈空间
fun fibonacci3(n:Int):Int {
    if (n == 0) return 0
    if (n == 1) return 1

    var first = 0
    var second = 1
    var res = 0

    // 动态规划：dp[i] = dp[i-1] + dp[i-2];的变体，但是和
    for (i in 2..n) {
        res = first + second
        first = second
        second = res
    }
    return res
}

var array = IntArray(100)


// 比较巧妙
fun fibonacci4(n:Int):Int {
    if (n == 0) return 0
    if (n == 1) return 1
    var sum = 1
    var one = 0
    for (i in 2..n) {
        sum = sum + one
        one = sum - one
    }
    return sum
}

fun main() {
    var data = listOf(2,5,8,10,12,30,50)

    data.forEach {
        var start = System.currentTimeMillis()
        var res = fibonacci(it)
        var cost = System.currentTimeMillis() - start
        println("递归法: $res cost: $cost ms")

        start = System.currentTimeMillis()
        res = fibonacci2(it)
        cost = System.currentTimeMillis() - start
        println("改进的递归法: $res cost: $cost ms")

        res = fibonacci3(it)
        println("循环法: $res")

        res = fibonacci4(it)
        println("改进的循环法: $res")
    }
}