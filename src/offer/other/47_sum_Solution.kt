package offer.other

/**
 *
 * 47、求1+2+3+4+···+n
 *
 * 求1+2+3+...+n，要求不能使用乘除法、
 * for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */


// 递归：其实也是循环的一种表现形式！
fun sum_Solution(n:Int):Int {
    var sum = n
    // 逻辑与的短路特性实现递归终止。因为不能使用if
    var asn = n > 1 && sum_Solution(n -1).let { sum += it; sum } > 0
    // 这么写会报错
//    var ans = (n > 0) && ((sum += sum_Solution(n-1)) > 0)
    return sum
}

// 幂级数代替乘法，位移代替除法，(1+n)*n/2 ,(n+n*n)/2
fun sum_Solution2(n:Int):Int {
    var sum = (Math.pow(n.toDouble(), 2.0)).toInt() + n
    return sum shr 1
}

// 不能用if
fun Sum_Solution(n: Int): Int {
    return if (n == 1) n else n + Sum_Solution(n - 1)
}

fun main(){
    var ret = sum_Solution(10)
    println("求和: $ret")

    ret = sum_Solution2(10)
    println("求和: $ret")
}