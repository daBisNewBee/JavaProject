//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 948 👎 0

/**
 *
 * 矩阵处理技巧/矩阵特殊轨迹问题：
 *
 * 1. zigzag打印矩阵
 *
 * 2. 转圈打印矩阵
 *
 * 3. 原地旋转正方形打印矩阵
 *
 *
 * 核心技巧：
 *  "找到coding上的宏观调度，不要限制于局部位置怎么变上"
 *
 */

class P_54_SpiralMatrix {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         * 1. 定义两个点规定一个框：左上点(ar，ac)，右下点(br，bc)
         * 2. 两个点的运动方向：左上角点右下移动，右下角点左上移动
         *
         */
        fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var ar = 0
        var ac = 0
        var br = matrix.size - 1
        var bc = matrix[0].size - 1
        var ans = mutableListOf<Int>()
        while (ar <= br && ac <= bc) {
            printMatrix(matrix, ar++, ac++, br--, bc--, ans)
        }
        return ans
    }

    private fun printMatrix(matrix: Array<IntArray>,
                            _ar:Int, _ac:Int, _br:Int, _bc:Int,
                            ans:MutableList<Int>) {
        var ar = _ar
        var ac = _ac
        var br = _br
        var bc = _bc
        if (ar == br) {
            for (i in br .. bc) {
                ans.add(matrix[ar][i])
            }
        } else if (ac == bc) {
            for (i in ar .. br) {
                ans.add(matrix[i][ac])
            }
        } else {
            while (ac != _bc) {
                ans.add(matrix[_ar][ac++])
            }
            while (ar != _br) {
                ans.add(matrix[ar++][_bc])
            }
            while (bc != _ac) {
                ans.add(matrix[_br][bc--])
            }
            while (br != _ar) {
                ans.add(matrix[br--][_ac])
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var array = Array(3){IntArray(3)}
    array[0] = intArrayOf(1,2,3)
    array[1] = intArrayOf(4,5,6)
    array[2] = intArrayOf(7,8,9)
    var ans = P_54_SpiralMatrix.Solution().spiralOrder(array)
    println(ans)
}