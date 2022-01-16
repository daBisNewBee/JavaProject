import java.util.*


//给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
//请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//输出：13
//解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[-5]], k = 1
//输出：-5
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length 
// n == matrix[i].length 
// 1 <= n <= 300 
// -109 <= matrix[i][j] <= 109 
// 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列 
// 1 <= k <= n2 
// 
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 
// 👍 741 👎 0


class P_378_KthSmallestElementInASortedMatrix {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun kthSmallest(matrix: Array<IntArray>, _k: Int): Int {
        var queue = PriorityQueue<IntArray>(kotlin.Comparator { o1, o2 -> o1[0] - o2[0]})
        var n = matrix.size
        var k = _k
        for (i in 0 until n) {
            // 当前行首最小值，坐标 x,y
            queue.offer(intArrayOf(matrix[i][0], i, 0))
        }
        while (k > 0) {
            // 每次取出一个最小值
            var curRow = queue.poll()
            k--
            if (k == 0) {
                return curRow[0]
            }
            if (curRow[2] != n-1) {
                // 向右移动，最小值改变。
                var index = curRow[2] + 1
                queue.offer(intArrayOf(matrix[curRow[1]][index], curRow[1], index))
            }
        }
        return -1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var matrix = Array(3){IntArray(3)}
    matrix[0] = intArrayOf(1,5,9)
    matrix[1] = intArrayOf(10,11,13)
    matrix[2] = intArrayOf(12,13,15)

    println(P_378_KthSmallestElementInASortedMatrix.Solution().kthSmallest(matrix, 8))
}