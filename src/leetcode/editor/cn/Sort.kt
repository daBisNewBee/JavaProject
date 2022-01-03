package leetcode.editor.cn

import kotlin.random.Random


/**
 *
 * 优化：
 * 1. 主元的选择。尽量中位数，避免极值;或者随机一个
 * 2. 随机化。避免最坏时间复杂度
 * 3. 混合排序。配合插入排序，当数组小于一定长度时
 * 4. 双主元排序。数组划分成三部分，不是两部分
 *
 */
fun quickSort(array: IntArray, start:Int, end:Int) {
    if (start >= end) return // 容易忘记！！

    if (start > end - 60) {
        /**
         *  综合排序：
         *
         *  大样本量，采用快排的调度
         *  小样本时<60，采用插入排序，常数时间低的优势，n2,更快 TODO: 为什么更快呢
         */
        return
    }

    // pivot 主元的选择：理想情况，每次都是数组的中位数，效率最高
    var tmpIndex = Random.nextInt(start, end) // 优化：随机取一个，避免待排序数据部分有序，降低效率
    var tmp = array[tmpIndex]
    println("start:$start end:$end tmpIndex:$tmpIndex tmp:$tmp")
//            array[start]
    var i = start
    var j = end

    while (i != j) {
        // j > j 不要忘了！安全条件
        while (j > i && array[j] >= tmp) {
            j--
        }
        array[i] = array[j]
        while (j > i && array[i] <= tmp) {
            i++
        }
        array[j] = array[i]
    }
    array[j] = tmp
    quickSort(array, start, j - 1)
    quickSort(array, j + 1, end)
}

fun insert(array: IntArray){
    for (i in 1 until array.size) {
        for (j in i downTo 1) {
            // 两两比较，找到属于位置
            if (array[j] < array[j-1]) {
                var tmp = array[j]
                array[j] = array[j-1]
                array[j-1] = tmp
            } else {
                // 说明已经找到位置了
                break
            }
        }
    }
}

/**
 * 插入排序的效率比冒泡排序要好，尤其是数据量大的时候，差距更明显。为什么？

   冒泡排序移动数据的操作更多，只要是小于后一个元素，就移动一次。所以它的效率低。

   看测试结果，八万的时候，就是数量级的差距了。

    数组长度值为：80000
    插入排序用时(单位毫秒)：2385
    冒泡排序用时(单位毫秒)：11067
    选择排序用时(单位毫秒)：1163
 *
 */
fun bubble(array: IntArray) {
    for (i in array.indices) {
        var flag = true
        for (j in array.size - 1 downTo i + 1) {
            if (array[j-1] > array[j]) {
                var tmp = array[j-1]
                array[j-1] = array[j]
                array[j] = tmp
                flag = false
            }
        }
        if (flag) {
            break
        }
    }

    // 这不是冒泡？
//    for (i in array.indices) {
//        for (j in i + 1 until array.size) {
//            if (array[i] > array[j]) {
//                var tmp = array[j]
//                array[j] = array[i]
//                array[i] = tmp
//            }
//        }
//    }
}

fun main() {
    val A = intArrayOf(4, 2, 1, 7, 8, 5, 6, 3)
    //
    println(A.contentToString())
    insert(A)
//    bubble(A)
//    quickSort(A, 0, A.size-1)
    println(A.contentToString())
}