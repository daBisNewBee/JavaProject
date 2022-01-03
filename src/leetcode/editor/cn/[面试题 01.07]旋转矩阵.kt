//给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。 
//
// 不占用额外内存空间能否做到？ 
//
// 
//
// 示例 1: 
//
// 
//给定 matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// 示例 2: 
//
// 
//给定 matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//原地旋转输入矩阵，使其变为:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
//
// 注意：本题与主站 48 题相同：https://leetcode-cn.com/problems/rotate-image/ 
// Related Topics 数组 数学 矩阵 
// 👍 205 👎 0


class P_01_07_RotateMatrixLcci {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun rotate(matrix: Array<IntArray>): Unit {
        var a = 0
        var b = 0
        var c = matrix.size - 1
        var d = matrix[0].size - 1
        while (a < c) {
            rotateMatrix(matrix, a++, b++, c--, d--)
        }
    }

        private fun rotateMatrix(matrix: Array<IntArray>, a: Int, b: Int, c: Int, d: Int) {
            // 组的数量d-b；组的编号:0~d-b
            for (i in 0 until d - b) {
                // 找到每个小组的各个点，相互赋值
                var tmp = matrix[a][b+i]
                matrix[a][b+i] = matrix[c-i][b]
                matrix[c-i][b] = matrix[c][d-i]
                matrix[c][d-i] = matrix[a+i][d]
                matrix[a+i][d] = tmp
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var array = Array(4){IntArray(4)}
    array[0] = intArrayOf(1,2,3,4)
    array[1] = intArrayOf(4,5,6,5)
    array[2] = intArrayOf(7,8,9,7)
    array[3] = intArrayOf(11,12,13,14)
    P_01_07_RotateMatrixLcci.Solution().rotate(array)
}