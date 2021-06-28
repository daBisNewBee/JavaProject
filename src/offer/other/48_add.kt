package offer.other

/**
 *
 * 48、不用加减乘除做加法
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 *  解释：https://blog.nowcoder.net/n/e6eecb2263154c39a2f360f60b0e92ce?f=comment
无进位和运算就是按位异或结果，进位就是与运算结果但是需要左移一位，
因为进位影响下一位的运算。 所以s = a + b,其实就是无进位和+进位的结果。
    即，拥有了两个基本表达式：
    执行加法 x ^ y
    进位操作 ( x & y ) << 1
 */


fun add(_num1:Int, _num2:Int):Int {
    println("num1:$_num1 num2:$_num2")

    var num1 = _num1

    var num2 = _num2

    while (num2 > 0) {
        // 左移一位表示，表示是否有进位
        var c = num1 and num2 shl 1
        num1  = num1 xor num2
        num2 = c
    }

    return num1
}

// 上述的递归实现
fun add2(_num1:Int, _num2:Int):Int {
    if (_num2 == 0) return _num1

    var num1 = _num1

    var num2 = _num2

    var c = num1 and num2 shl 1

    num1 = num1 xor num2

    num2 = c

    return add2(num1, num2)
}

fun main(){

    var data = listOf(2,4,5,10,11,12,45,99,100,259,199,255)

    for (i in 0..data.size/2) {
        var ret = add(data[i], data[i+data.size/2])
        println("$ret")

        ret = add2(data[i], data[i+data.size/2])
        println("$ret")
    }
}