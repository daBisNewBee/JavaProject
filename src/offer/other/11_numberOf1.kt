package offer.other

/**
 *
 * 11、二进制中1的个数
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 */

// fixme: 错误！这个方法负数不行！逐位计算, 移动数字n本身；类似"除2取模法"也不行！
// 负数，移位后高位补1
fun numberOf1(n:Int):Int {
    var count = 0
    var flag = n
    while (flag != 0) {
        if (flag and 1 == 1) {
            count++
        }
        flag = flag shr 1
    }
    return count
}

//

// 方法二：逐位计算, 移动flag
fun numberOf2(n:Int):Int {
    var count = 0
    var flag = 1
    while (flag != 0) {
        if (flag and n != 0) {
            count++
        }
        flag = flag shl 1
    }
    return count
}

/**
 *
 * 技巧法：
 *
 * 现考虑二进制数：val :1101000, val-1: 1100111 那么val & （val-1） : 1100000
 *
 * 即，做一次&，n的1的位数减1，这时n的值也变了，因此一直&到n变为0，
 * 我们即可得出n的1的个数。
 */
fun numberOf3(_n:Int):Int {
    var count = 0
    var n = _n
    while (n != 0) {
        n = n and (n-1)
        count++
    }
    return count
}


fun main(){
    var data = listOf(3,7,8,63,64,255,Int.MAX_VALUE, Int.MIN_VALUE)
    data.forEach {
//        var ret = numberOf1(it)
        var ret2 = numberOf2(it)
        var ret3= numberOf3(it)
        println("num: $it count2: $ret2 count3: $ret3")
    }

    var min = Int.MIN_VALUE
    var max = Int.MAX_VALUE
    println("min:$min max:$max")
    // min:-2147483648 max:2147483647
}