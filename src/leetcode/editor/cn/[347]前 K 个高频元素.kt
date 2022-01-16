import java.util.*

//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 
// 👍 981 👎 0


class P_347_TopKFrequentElements {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        var map = mutableMapOf<Int, Int>()
        nums.forEach {
            map.set(it, map.getOrDefault(it, 0)+1)
        }
        // 小根堆
        var queue = PriorityQueue<Pair<Int, Int>>(kotlin.Comparator { o1, o2 -> o1.second - o2.second })
        map.entries.forEach {
            if (queue.size < k) {
                queue.offer(Pair(it.key, it.value))
            } else if (queue.size == k) {
                if (it.value > queue.peek().second) {
                    queue.poll()
                    queue.offer(Pair(it.key, it.value))
                }
            }
        }
        var ans = IntArray(k)
        for (i in k-1 downTo 0) {
            ans[i] = queue.poll().first
        }

        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
   var ans = P_347_TopKFrequentElements.Solution().topKFrequent(intArrayOf(4,1,-1,2,-1,2,3), 2)
    ans.forEach { println(it) }

}