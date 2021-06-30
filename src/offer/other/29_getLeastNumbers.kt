package offer.other

/**
 * 29、最小的K个数
 *
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 *
 *
 *
 */

// 类似于快速排序的思想，基于Partition函数来解决这个问题
fun getLeastNumbers(array: MutableList<Int>, k: Int): MutableList<Int>? {

    if (array.isEmpty() || k > array.size) return null

    var low = 0

    var high = array.size - 1

    var index = -1

    while (low < high && index != k) {

        index = partition(array, low, high)

        if (index > k) {

            high = index - 1

        } else {

            low = index + 1
        }
    }

    var ret = ArrayList<Int>()
    for (i in 0 until k) {
        ret.add(array[i])
    }
    return ret
}

fun partition(array: MutableList<Int>, low: Int, high: Int): Int {

//    if (low >= high) return -1

    var tmp = array[low] // TODO:基准的选择：选其他行不行？

    var i = low

    var j = high

    while (i < j) {
        while (i < j && array[j] >= tmp)
            j--

        if (i < j) {
            array[i] = array[j]
            i++
        }

        while (i < j && array[i] <= tmp)
            i++

        if (i < j) {
            array[j] = array[i]
            j--
        }
    }
    array[i] = tmp
    return i
}

fun main() {
    var data = mutableListOf(4, 5, 1, 6, 2, 7, 3, 8)

    var ret = getLeastNumbers(data, 4)

    println(ret)

//    data = mutableListOf(100, {it->it})
}