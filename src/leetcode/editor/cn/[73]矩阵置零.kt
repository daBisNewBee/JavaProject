//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。 
//
// 进阶： 
//
// 
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个仅使用常量空间的解决方案吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics 数组 哈希表 矩阵 
// 👍 531 👎 0


class P_73_SetMatrixZeroes {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // 背景：需要长度分别为m、  n 的两个数组，空间o(m+n)，记录每一行或每一列是否有零出现
        // 这里优化：第一行和第一列代替方法一中的两个标记数组，以达到 O(1)的额外空间
    fun setZeroes(matrix: Array<IntArray>): Unit {
        if (matrix.isEmpty()) return
        var flagCol0 = false
        var flagRow0 = false
        var m = matrix.size
        var n = matrix[0].size
            // 第一列是否有零
        for (i in 0 until m) {
            if (matrix[i][0] == 0) {
                flagCol0 = true
                break
            }
        }
            // 第一行是否有零
        for (j in 0 until n) {
            if (matrix[0][j] == 0) {
                flagRow0 = true
                break
            }
        }
            // 把第一行第一列作为标志位
        for (i in 1 until m) {// 这里从1开始
           for (j in 1 until n) {
               if (matrix[i][j] == 0) {
                   matrix[i][0] = 0
                   matrix[0][j] = 0
               }
           }
        }
        // 置0
        for (i in 1 until m) { // 这里从1开始
            for (j in 1 until n) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }
        if (flagCol0) {
            for (i in 0 until m) {
                matrix[i][0] = 0
            }
        }
        if (flagRow0) {
            for (j in 0 until n) {
                matrix[0][j] = 0
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var array = Array(1){IntArray(2)}
    array[0][0] = 1
    array[0][1] = 0
    P_73_SetMatrixZeroes.Solution().setZeroes(array)
}