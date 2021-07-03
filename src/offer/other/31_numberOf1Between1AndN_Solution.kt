package offer.other

/**
 * 31、从1到n整数中1出现的次数
 *
 * 求出1-13的整数中1出现的次数,并算出100-1300的整数中1出现的次数？
 * 为此他特别数了一下1-13中包含1的数字有1、10、11、12、13因此共出现6次,
 * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
 * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 *
 */

// TODO: 不太熟练，还要多写几遍
fun umberOf1Between1AndN_Solution(n: Int): Int {
    var count = 0
    var weight = 0 //位值
    var base = 1 //权重
    var former = 0
    var round = n

    while (round != 0) {

        weight = round % 10

        round = round / 10

        former = n % base

        if (weight > 1)
            count += round * base + base
        else if (weight == 1)
            count += round * base + former + 1
        else
            count += round * base

        base *= 10
    }
    return count
}

// 这种方法时间复杂高，只是作为参考，面试不要写！
fun numberOf1Between1AndN_Solution2(_n:Int):Int {
    var n = _n
    var count = 0
    while (n > 0) {
       var str =  n.toString()
        for (i in str.indices) {
            if (str[i] == '1') {
                count++
            }
        }
        n--
    }
    return count
}

fun main(){
    var ret = umberOf1Between1AndN_Solution(534)
    println("$ret")
}