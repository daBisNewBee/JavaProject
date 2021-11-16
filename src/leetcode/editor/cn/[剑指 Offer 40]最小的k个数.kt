import java.util.*

//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 326 👎 0


class P_Offer_40_ZuiXiaoDeKgeShuLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         *
         * 重点注意下"PriorityQueue"的结构：
         *
         * 默认是小顶堆/小根堆，即root的是最小节点，
         *
         * 原理可以看"siftUpComparable"，默认的比较器：子节点小于父节点就上移
         *
         *      if (comparator.compare(x, (E) e) >= 0)
                break;

                即：

                if (comparator.compare(子, (E) 父) >= 0)
                break;
         *
         * 显然不满足这里的需求：最小的k个数。
         *
         * 即要求堆上的根节点最大，即大顶堆，才能保证子节点都小于root，即最小的k个数
         *
         * 所以这里自定义比较器.
         *
         * 比较巧妙的一段代码：
         *
         */
    fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
        if (k <= 0 || arr.isEmpty()) return IntArray(0)
        if (arr.size == 1) return arr
        var queue = PriorityQueue<Int>(Comparator { o1, o2 -> o2 - o1 })
        for (i in 0 until k) {
            queue.offer(arr[i])
        }
        for (i in k until arr.size) {
            if (arr[i] < queue.peek()) {
                queue.poll()
                queue.offer(arr[i])
            }
        }
        var retArray = IntArray(k)
        for (i in 0 until queue.size) {
            retArray[i] = queue.poll()
        }
        return retArray
    }

        /**
         * 比较巧妙的一段代码：
         *
         * 如何取到父节点？
         * 子节点小于父节点时，如何上浮？
         */
        /*
        private fun siftUpUsingComparator(k: Int, x: E) {
            var k = k
            while (k > 0) {
                val parent = k - 1 ushr 1
                val e: Any = queue.get(parent)
                if (comparator.compare(x, e as E) >= 0) break
                queue.get(k) = e
                k = parent
            }
            queue.get(k) = x
        }
        */
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ret = P_Offer_40_ZuiXiaoDeKgeShuLcof.Solution().getLeastNumbers(intArrayOf(3,2,1), 2)
    println(ret.contentToString())
}