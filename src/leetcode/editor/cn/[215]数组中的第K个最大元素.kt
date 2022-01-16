import java.util.*

//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 1448 👎 0


class P_215_KthLargestElementInAnArray {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 一般套路：
         *  求第前k个大：
         *    小根堆，维持堆的 size 为 k，最后堆顶为结果
         *
         *  也可以转换下，即为求"第n-k+1个小"：
         *    大根堆，维持堆的 size 为 n-k+1，最后堆顶为结果
         *
         */
        fun findKthLargest(nums: IntArray, k: Int): Int {
            var queue = PriorityQueue<Int>(kotlin.Comparator { o1, o2 -> o1 - o2 })
            nums.forEach {
                if (queue.size < k) {
                    queue.offer(it)
                } else if (queue.size == k) {
                    if (it > queue.peek()) {
                        queue.poll()
                        queue.offer(it)
                    }
                }
            }
            return queue.peek()
        }

    fun findKthLargest_bigDui(nums: IntArray, k: Int): Int {
        // 第k个最大，即：第(n-k+1)个最小数，即：求体积(n-k+1)大根堆的根节点

        var queue = PriorityQueue<Int>(Comparator { o1, o2 -> o2 - o1 })

        var N = nums.size
        // 先放n-k+1个数字进去
        for (i in 0  until N - k + 1) {
            queue.offer(nums[i])
        }

        for (i in N-k+1 until N) {
            if (nums[i] < queue.peek()) {
                queue.poll()
                queue.offer(nums[i])
            }
        }

        return queue.peek()
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}