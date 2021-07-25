import kotlin.math.max

//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matix[i][j] <= 109 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 分治 矩阵 
// 👍 673 👎 0


class P_240_SearchA2dMatrixIi {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 时间复杂度：O(n+m)
     * 从左下或者右上开始遍历，每次去除一行或者一列，缩减问题
     */
class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false
        val rows = matrix.size
        val columns = matrix[0].size
        for (i in 0 until rows) {
            for (j in columns - 1 downTo 0) {
                if (matrix[i][j] == target) {
                    return true
                } else if (matrix[i][j] < target) {
                    break
                } else {
                    continue
                }
            }
        }
        return false
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}