package offer.other

/**
 * 29、最小的K个数
 *
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 *
 */

/**
 *
 * 快速排序，快排
 * 几个面试注意点：
 *
 * 1. 有简便法:smaller、bigger，需要额外空间；原址排序：比较基本
 *
 * 2. 正常时间：o(nlogn)
 *
 * 3. 最差时间：o(n2)，什么时候触发？
 *    每次主元都选到极值；
 *    其实每层需要时间都是o(n)，只是递归树变深，需要递归的次数增多到n次
 *
 * 4. 因此，避免时间退化，主元的选择要慎重！避免选择极值
 *
 * 5. 优化实现：TODO
 *  面试官：手写一个快速排序，并对其改进
 *  https://zhuanlan.zhihu.com/p/82671667
 *
 *
 * 快速排序的效率取决于"数组划分是否平衡"，即依赖于主元（pivot）的选择。
 *
 * 最好的情况，假设主元每次都恰好是待排序数组的中位数，
 *
 * 能够把待排序数组平衡地划分为两个相近长度的子数组。
 *
 * 面试中的快速排序：
 * https://zhuanlan.zhihu.com/p/267133203
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

// 原址排序
fun quickSort(array: MutableList<Int>, left:Int, right:Int) {
    var i = left
    var j = right
    if (i >= j) return
//    println("left:$left right:$right")

    var tmp = array[i]

    while (i != j) {
        // 找到右边第一个比基准小的
        while (i < j && array[j] >= tmp)
            j--
        array[i] = array[j]

        // 找到左边第一个比基准大的
        while (i<j && array[i] <= tmp)
            i++
        array[j] = array[i]
    }

    array[j] = tmp

    quickSort(array,left, i-1)

    quickSort(array, j+1, right)
}


// 快排：更容易记忆！！缺点：使用额外的空间
fun quickSort2(array: MutableList<Int>):MutableList<Int> {
    if (array.isEmpty()) return ArrayList()
    var pivot = array[0]

    // 使用额外的空间来存储大于或者小于等于 pivot 的元素
    var smaller = ArrayList<Int>()
    var bigger = ArrayList<Int>()
    for (i in 1 until array.size) {
        if (array[i] > pivot) {
            bigger.add(array[i])
        } else {
            smaller.add(array[i])
        }
    }
    var result = ArrayList<Int>()
    result.addAll(quickSort2(smaller))
    result.add(pivot)
    result.addAll(quickSort2(bigger))
    return result
}


fun main() {
    var data = mutableListOf(4, 5, 1, 6, 2, 7, 3, 8)

    var ret = getLeastNumbers(data, 4)

    println(ret)

    data = mutableListOf(5, 4, 1, 6, 9, 2, 7, 3, 8)

    // 方法二：快排：先排序，再取最小值
    quickSort(data, 0, data.size-1)

    println(data)

    data = mutableListOf(5, 4, 1, 6, 9, 2, 7, 3, 8)

    // 方法三：快排：先排序，再取最小值
    ret = quickSort2(data)

    println(ret)
}