import java.util.*

//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 
// 👍 449 👎 0


class P_295_FindMedianFromDataStream {
    //leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder() {

    /** initialize your data structure here. */
    var maxHeap:PriorityQueue<Int>
    var minHeap:PriorityQueue<Int>
    var count = 0

    init {
        maxHeap = PriorityQueue(kotlin.Comparator{o1, o2 -> o2 - o1})
        minHeap = PriorityQueue()
    }

    // 1. 比较简洁，但有多次不必要的入队、出队操作！
    fun addNum(num: Int) {
        count++
        maxHeap.add(num)
        minHeap.add(maxHeap.poll())
        if (count % 2 != 0) {
            maxHeap.add(minHeap.poll())
        }
    }

    /* 2. 这个方法比较基础！！
    fun addNum(num: Int) {
        if (count % 2 == 0) {
            var value = num
            if (minHeap.isNotEmpty() && minHeap.peek() < num) {
                minHeap.add(num)
                value = minHeap.poll()
            }
            maxHeap.add(value)
        } else {
            var value = num
            if (maxHeap.isNotEmpty() && maxHeap.peek() > num) {
                maxHeap.add(num)
                value = maxHeap.poll()
            }
            minHeap.add(value)
        }
        count++
    }
    */

    fun findMedian(): Double {
        // 3. 这里选择大顶堆多一个数，实际小顶堆也可以！
        if (count % 2 == 0) {
            return (minHeap.peek() + maxHeap.peek()).toDouble() / 2
        } else {
            return maxHeap.peek().toDouble()
        }
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
//leetcode submit region end(Prohibit modification and deletion)

}