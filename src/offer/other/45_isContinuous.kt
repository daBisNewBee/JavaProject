package offer.other

import java.util.*

/**
 *
 * 45、扑克牌顺子
 *
 * 题目抽象：给定一个长度为5（排除空vector），包含0-13的数组，判断公差是否为1.
 *
 */

fun isContinuous(array: IntArray):Boolean {
    if (array.isEmpty() || array.size < 5) return false

    // 1）数组排序
    Arrays.sort(array)

    var len = array.size

    //（2）统计0的数量
    var numberOf0 = 0
    for (i in array.indices) {
        if (array[i] != 0) {
            numberOf0 = i
            break
        }
    }

    //（3）统计相邻数字间间隔的总数
    if (array[len-1] - array[numberOf0] > len-1) { //max-min>=5,直接返回false
        return false
    }
    var count = 0
    for (i in 0 until len-1) {
        //对子，这个要注意
        if (array[i] == array[i+1]) return false
        //相邻数字间间隔的总数
        count += array[i+1] - array[i] - 1
    }
    if (count > numberOf0) {
        return false
    }
    return true
}

// 推荐！set法，比较简单：
// 可以这么理解，简单来说就是要是5个数字，最大和最小差值在5以内，并且没有重复数值。
// 用一个set来填充数据，0不要放进去。set的大小加上0的个数必须为5个。此外set中数值差值在5以内。
fun isContinuous2(array: IntArray):Boolean {
    if (array.isEmpty() || array.size != 5) return false
    var set = TreeSet<Int>()
    var num = 0
    for (one in array) {
        if (one == 0) {
            num++
        } else {
            set.add(one)
        }
    }
    // 有重复
    if (num + set.size < 5) return false

    if (set.last() - set.first() < 5) return true

    return false
}

fun main(){

    var data = intArrayOf(0,1,2,3,5)

    println(isContinuous(data))

    println(isContinuous2(data))
}