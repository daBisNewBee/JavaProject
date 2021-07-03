package offer.other

import java.util.*

/**
 *
 * 33、丑数
 *
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数
 * 。求按从小到大的顺序的第N个丑数。
 *
 */


// 此题的难点就在于如何有序的放在合适的位置?
fun getUglyNumber_Solution(index:Int):Int {
    if (index <= 0) return 0

    var result = IntArray(index)

    var p2 = 0

    var p3 = 0

    var p5 = 0

    result[0] = 1

    for (i in 1 until index) {

        var min = getMin(result[p2]*2, result[p3]*3, result[p5]*5)

        result[i] = min

        if (result[p2]*2 == min) p2++

        if (result[p3]*3 == min) p3++

        if (result[p5]*5 == min) p5++
    }

    println(Arrays.toString(result))
    return result[index-1]
}

fun getMin(a:Int, b:Int, c:Int):Int{
    return Math.min(a, Math.min(b,c))
}

fun main(){
    var ret = getUglyNumber_Solution(20)
    println(ret)
}