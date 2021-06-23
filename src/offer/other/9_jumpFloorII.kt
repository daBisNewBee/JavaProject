package offer.other

/*
*
* 9、变态跳台阶
*
*
* 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
* 求该青蛙跳上一个n级的台阶总共有多少种跳法。
*
* f(n) = f(n-1)+f(n-2)+···+f(n-n) = f(0)+f(1)+···+f(n-1)，
*
* 同理，f(n-1) = f(0)+f(1)+···+f(n-2)
*
* 因此，f(n) = f(n-1) + f(n-1)
*
* 递推得：
*
* fn = fn-1 * 2
*
* */

fun jumpFloor2(n:Int):Int{
    if (n == 0) return 0
    if (n == 1) return 1
    var res = 1
    for (i in 2..n) {
        res *= 2
    }
    return res
}

fun main(){
    var ret = jumpFloor2(10)
    println
}