package leetcode.editor.cn

import kotlin.math.max
import kotlin.math.min

/**
 *
 * 给定一个整型数组arr,代表数值不同的纸牌排成一条线
 *
 * 玩家A和玩家B依次拿走没张纸牌
 *
 * 规定玩家A先拿，玩家B后拿
 *
 * 但是每个玩家每次只能拿走最左或者最右的纸牌
 *
 * 玩家A和B都绝顶聪明。请返回获胜者的分数
 *
 */


/**
 *
 * "范围内的尝试模型"
 *
 * 特征？为什么说是范围内？
 *
 * 1. L、R 不会超过 array size
 *
 * 2. 问题规模在缩小。
 *    问题求解f(L,R) 依赖 s(L+1,R) 或者s(L,R-1)
 *
 */

// 在[L、R]范围先手，返回最大的值
fun f(array: IntArray, L:Int, R:Int):Int {
    if (L == R) {
        return array[L]
    }
    return max(
            s(array, L+1, R) + array[L],
            s(array, L, R-1) + array[R])
}

// 在[L、R]范围后手，返回最大的值
fun s(array: IntArray, L:Int, R:Int):Int {
    if (L >= R) {
        return 0
    }
    return min(
            f(array, L+1, R),
            f(array, L, R-1))
}

fun dpWays(array: IntArray):Int {

    var N = array.size

    //  两张正方形的表
    var f = Array(N){IntArray(N)}

    var s = Array(N){IntArray(N)}


    for (i in 0 until N) {
        f[i][i] = array[i]
    }
    // s[i][i] = 0 默认值

    /**
     *
     * 画出表，可以得出的规律：
     *
     * 1. L > R，左边界不可能大于有边界。因此其值不存在
     *
     * 2. 两张表的对角线值互相递推。
     *   因此，在遍历时，按照对角线方向进行
     *
     *
     */
    for (i in 1 until N) {
        var row = 0
        var col = i
        while (row < N && col < N) {
            f[row][col] = max(
                    s[row+1][col] + array[row],
                    s[row][col-1] + array[col])

            s[row][col] = min(
                    f[row+1][col],
                    f[row][col-1])
            row++
            col++
        }
    }
    return max(f[0][N-1], s[0][N-1])
}

fun main() {
    var array = intArrayOf(4,7,9,5,19,29,80,4)
    var N = array.size

    var ans = max(f(array, 0, N-1), s(array, 0, N-1))
    println(ans)

    ans = dpWays(array)
    println(ans)
}