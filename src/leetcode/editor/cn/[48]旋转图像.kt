//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：[[1]]
// 
//
// 示例 4： 
//
// 
//输入：matrix = [[1,2],[3,4]]
//输出：[[3,1],[4,2]]
// 
//
// 
//
// 提示： 
//
// 
// matrix.length == n 
// matrix[i].length == n 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
// Related Topics 数组 数学 矩阵 
// 👍 954 👎 0


class P_48_RotateImage {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        fun process(matrix: Array<IntArray>, ar:Int, ac:Int, br:Int, bc:Int){
            for (i in 0 until br-ar) {
                var tmp = matrix[ar][ac+i]
                matrix[ar][ac+i] = matrix[br-i][ac]
                matrix[br-i][ac] = matrix[br][bc-i]
                matrix[br][bc-i] = matrix[ar+i][bc]
                matrix[ar+i][bc] = tmp
            }
        }

        fun rotate(matrix: Array<IntArray>):Unit {
            var n = matrix.size
            for (i in 0 until n / 2) {
                process(matrix, i, i, n-i-1, n-i-1)
            }
        }


        // 翻转两次
    fun rotate2(matrix: Array<IntArray>): Unit {
        var n = matrix.size
        // 1. 水平翻转
        for (i in 0 until n/2) {
            for (j in 0 until n) {
                var tmp = matrix[i][j]
                matrix[i][j] = matrix[n-i-1][j]
                matrix[n-i-1][j] = tmp
            }
        }
        // 2. 对角线翻转
        for (i in 0 until n) {
            for (j in 0 until i) {
                var tmp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = tmp
            }
        }
        // 还有一种思路需要了解下：第i行j列的元素，旋转后是第j行倒数第i列(放弃，写等式下标有点不好记忆！)
        // 即：matrix[row][col] -> matrix[col][n−row−1]
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}