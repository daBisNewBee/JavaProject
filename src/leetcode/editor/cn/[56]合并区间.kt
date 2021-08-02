import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1043 👎 0


class P_56_MergeIntervals {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.isEmpty()) return emptyArray()
        var result = ArrayList<IntArray>()

        Arrays.sort(intervals, { o1, o2 ->  o1[0] - o2[0]})

        for (one in intervals) {
            var L = one[0]
            var R = one[1]
            if (result.isEmpty() || result[result.size-1][1] < L) {
                result.add(intArrayOf(L, R))
            } else {
                result[result.size-1][1] = Math.max(R, result[result.size-1][1] )
            }
        }
        return result.toTypedArray()
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var data = Array(2){IntArray(2)}
    data[0] = intArrayOf(1,4)
    data[1] = intArrayOf(2,3)
//    data[2] = intArrayOf(8,10)
//    data[3] = intArrayOf(15,18)
    var ret = P_56_MergeIntervals.Solution().merge(data)
    for (ints in ret) {
        println("${ints[0]}, ${ints[1]}")
    }
}