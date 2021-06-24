package offer.other

/*
* 2、数值的整数次方
*
* 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
*
* */

// 递归
fun power(base:Double, exponent:Int):Double{
    if (exponent == 0)
        return 1.0
    if (exponent == 1)
        return base
    if (exponent == -1)
        return 1.0/base
    var half = power(base, exponent / 2)
    // //注意奇偶不同，奇数多乘一个base
    return half * half * power(base, exponent % 2)
}

fun main(){
    var ret = power(2.0, -2)
    println("$ret")
}