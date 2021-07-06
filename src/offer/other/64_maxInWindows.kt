package offer.other

import java.util.*
import kotlin.collections.ArrayList


/**
 * 64、滑动窗口的最大值
 *
 */


// 思路：双端队列，时间复杂度：O(n),
fun maxInWindows(array: IntArray, size:Int):MutableList<Int> {

    if (array.isEmpty() || size < 0
            || size > array.size) return ArrayList()

    // 队列保存数据索引，用于超出范围元素的剔除，注意只是索引，在实际比较大小时，需要结合array
    var queue = LinkedList<Int>()
    var res = ArrayList<Int>()

    for (i in array.indices) {
        while (queue.isNotEmpty()
                && queue.peek() < i - size + 1) {
            // 超出范围的去掉
            queue.poll()
        }
        while (queue.isNotEmpty()
                && array[i] >= array[queue.last]) {
            // 当前值大于之前的值，之前的不可能是最大值，可以删掉
            queue.removeLast()
        }
        queue.add(i)
        if (i >= size-1) {
            // 此时开始是第一个滑动窗口
            res.add(array[queue.peek()])
        }
    }
    return res
}

// 大顶堆，思路可以了解一下，时间效率很低，不建议。
// https://blog.nowcoder.net/n/b609519ac87b47d49a42e03603da27e4?f=comment
fun maxInWindows2(array: IntArray, size:Int):MutableList<Int> {
    if (array.isEmpty() || size < 0
            || size > array.size) return ArrayList()
    var priorityQueue = PriorityQueue<Int>{o1, o2 -> o2 - o1}
    var res = ArrayList<Int>()
    var count = 0
    for (i in 0 until size) {
        priorityQueue.add(array[count++])
    }
    while (count < array.size) {
        res.add(priorityQueue.peek())
        priorityQueue.remove(array[count-size])
        priorityQueue.add(array[count])
        count++
    }
    res.add(priorityQueue.peek())
    return res
}


fun main(){
    var data = intArrayOf(2,3,4,2,6,2,5,1)

    println(maxInWindows(data,3))

    println(maxInWindows2(data,3))
}