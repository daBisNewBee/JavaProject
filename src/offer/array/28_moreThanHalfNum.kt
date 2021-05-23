package offer.array

/*
*
* 28、数组中出现次数超过一半的数字
*
*  数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
   例如：输入如下所示的一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
   由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
*
*
* 常规的map法，不推荐，用O(n)的空间复杂度换来的快
*
* 候选法：比较巧妙，需要找到规律
*
* 排序法：容易想到，排序后可以减少数据的复杂度，处理起来更容易！
*
* */

/*
* 候选法（最优解）
*
* 假如数组中存在众数，那么众数一定大于数组的长度的一半。
* 思想就是：如果两个数不相等，就消去这两个数，最坏情况下，
* 每次消去一个众数和一个非众数，那么如果存在众数，最后留下的数肯定是众数。
*
* */
fun moreThanHalfNum(array: List<Int>):Int {
    if (array.isEmpty()) return 0
    var result = array[0]
    var times = 1
    array.forEach {
        if (times == 0) {
            result = it
            times = 1
        } else {
            if (result == it) {
                times++
            } else {
                times--
            }
        }
    }
    times = 0
    array.forEach {
        if (it == result) times++
    }
    return if (times > (array.size / 2)) {
        result
    } else {
        0
    }
}

/*
*
* 排序法
*
* 可以先将数组排序，然后可能的众数肯定在数组中间，然后判断一下。
*
* 时间复杂度：O(nlogn) （这取决于排序的时间复杂度）
* */
fun moreThanHalfNum2(array: List<Int>):Int {
    if (array.isEmpty()) return 0

    /*
    * ps.排序几种用法：
    *
    * mutableList:
    *   sortBy 升序
    *   sortByDescending 降序
    *
    * List:(结果在返回的新数组，因为原来list不可变)
    *   sortedBy 升序
    *   sortedBytDescending 降序
    *
    * */
    var newList = array.sortedBy { it -> return@sortedBy (it) }

    newList.forEach { print("$it,") }

    var halfNum = newList[array.size / 2]
    println("halfNum: $halfNum")

    var times = 0
    newList.forEach {
        if (it == halfNum) times++
    }
    return if (times > newList.size/2) halfNum else 0
}

inline fun test() {
    println("test")
}

fun main() {

    var data = listOf(1,2,3,2,2,2,5,4,2)

    var ret = moreThanHalfNum(data)

    println("候选法. 数组中出现次数超过一半的数字 ：$ret")

    ret = moreThanHalfNum2(data)
    println("排序法. 数组中出现次数超过一半的数字 ：$ret")
    test()
}