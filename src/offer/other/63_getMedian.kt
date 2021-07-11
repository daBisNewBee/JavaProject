package offer.other

import java.util.*


/**
 *
 * 63、数据流中的中位数
 *
 * 1 2 3 4 5 6 7 8定为最终的数据流
 *
 * 大顶堆 + 小顶堆
 *
 * 4是前半部分最大的值，肯定是维系在大顶堆
 *
 * 5是后半部分的最小值，肯定是维系在小顶堆
 *
 *
 * 堆结构PriorityQueue比较好的一个解释:
 * https://blog.csdn.net/qq_35326718/article/details/72866180
 *
 */

// 堆结构的优秀实现类
var minHeap = PriorityQueue<Int>()

var maxHeap = PriorityQueue(Comparator<Int> { o1, o2 -> o2 - o1 })

var count = 0

// 最大堆和最小堆。插入时间时间：o(logn)，查询o(1)，
// TODO: 想想有没有其他数据结构？数组、链表、二叉搜索树、AVL树，这些为什么不如大小堆？
// "一个数据结构可以O(1)返回最小值的，其实就是小根堆，
// O(1)返回最大值的，其实就是大根堆。
// 并且每次插入到堆中的时间复杂度为O(logn)"
fun insert(_num:Int) {
    count++
    var num = _num
    // 偶数，插入最小堆
    if (count % 2 == 0) {
        if (maxHeap.isNotEmpty()
                && maxHeap.peek() > num) {
            maxHeap.add(num)
            num = maxHeap.poll()
        }
        // 注意先后顺序：2. 偶数，再插入小顶堆
        minHeap.add(num)
    } else {
        // 奇数，插入最大堆
        if (minHeap.isNotEmpty()
                && minHeap.peek() < num) {
            minHeap.add(num)
            num = minHeap.poll()
        }
        // 注意先后顺序：1. 奇数，先插入大顶堆
        maxHeap.add(num)
    }
}

fun getMedian():Double {
    if (maxHeap.size == minHeap.size) {
        return (maxHeap.peek() + minHeap.peek()) / 2.0
    } else if (maxHeap.size > minHeap.size) {
        return maxHeap.peek().toDouble()
    } else {
        return minHeap.peek().toDouble()
    }
}

fun main(){

}