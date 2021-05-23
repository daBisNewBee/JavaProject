package offer.array

import java.util.*


/*
*
* 40、数组中只出现一次的数字
*
* 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
* 请写程序找出这两个只出现一次的数字。要求时间复杂度为O(n)，空间复杂度为O(1)。
*
* 思路:
* 1. 容易忽视的异或的几个特点：
* 0 xor X = X (0异或任何数都等于那个数)
* X xor X = 0 (任何一个数字异或它自己都等于0)
*
* 2. 如果数组里只有一个出现一次的数字，那问题到这里已经解决了，但是又两个，怎么办？
*
* 3. 拆分成两个子数组：各个子数组只包括一个一次数字
*
* 4. 如果确保两个出现一次数字分别拆分到分别子数组？
*
* 5. 找到两个数字的区别：找到其第一个为1的位
*
* */

fun findNumsAppearOnce(array: IntArray?, num1: IntArray, num2: IntArray) {
    /*
    思路：数组中的元素先依次异或，相同为0，则得到的是两个只出现一次的数的异或结果
    对于得到的异或结果，找到其第一个为1的位
    该位为1，说明两个只出现一次的数该位不同，所以按照该位是0还是1将数组分成两部分
    这样，出现两次的数字都会分到同一个部分，而两个只出现一次的数正好被分开，再各自异或可得结果
    */
    if (array == null || array.size < 2) return
    var res = 0
    for (num in array)  //数组中的元素先依次异或，相同为0，则得到的是两个只出现一次的数的异或结果
        res = res xor num
    var index = 0 //找到其第一个为1的位
    while (index < 32) { // TODO: 为什么是32？
        if (res shr index and 1 == 1) break
        index++
    }
    num1[0] = 0
    num2[0] = 0
    for (num in array) { //按照该位是0还是1将数组分成两部分,分别异或
        if (num shr index and 1 == 1)
            num1[0] = num1[0] xor num
        else
            num2[0] = num2[0] xor num
    }
}

// Set或map的唯一性
fun findNumsAppearOnce2(array: IntArray?) {

    var set: MutableSet<Int> = TreeSet()

    var arrayList :MutableList<Int> = array!!.toMutableList()

    for (index in arrayList.size - 1 downTo 0) {
        if (set.contains(arrayList[index])) {
            set.remove(arrayList[index])
        } else {
            set.add(arrayList[index])
        }
    }

    set.forEach {
        println("$it")
    }

}

fun main() {
    var data = intArrayOf(2,4,3,6,3,2,5,5)

    var ret1 = intArrayOf(0)

    var ret2 = intArrayOf(0)

    findNumsAppearOnce(data, ret1, ret2)

    println("ret1 : ${ret1[0]}")

    println("ret2 : ${ret2[0]}")

    findNumsAppearOnce2(data)

}