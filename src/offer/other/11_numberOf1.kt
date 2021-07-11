package offer.other

/**
 *
 * 11、二进制中1的个数
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 */
// 方法一：逐位计算, 移动数字本身，注意要无符号移动ushr，不能shr，因为高位负数会补1
fun numberOf1(n:Int):Int {
    var count = 0
    var flag = n
    while (flag != 0) {
        if (flag and 1 == 1) {
            count++
        }
        flag = flag ushr 1
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
        var ret = numberOf1(it)
        var ret2 = numberOf2(it)
        var ret3= numberOf3(it)
        println("num: $it count1:$ret  count2: $ret2 count3: $ret3")
    }

    var min = Int.MIN_VALUE
    var max = Int.MAX_VALUE
    println("min:$min max:$max")
    // min:-2147483648 max:2147483647

    /**
     * 负数的取值范围为什么比正数多一位？
     *
     * ex. -128 ~ 127
     *
     * 8位可以存储256个状态，但-127~127只用了255个，还剩下一个"1000 0000"
     *
     * 原因：
     *
     * 1. 人为规定。为了区别"正零:0000 0000"和"负零:1000 0000"，避免浪费
     *
     * 2. 方便记忆，自认为："1000 0001" 表示"-127"，那-1后"1000 0000"正好就是"-128"
     *
     * ps:
     * 补码=（正数）原码取反（=反码）+1
     *
     */
}