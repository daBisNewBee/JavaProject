package leetcode.editor.cn

/**
 *
 * 硬币数
 *
 * arr[7,3,100,50....]
 *
 * 数组每个位置存在面值不同的硬币，每次可以选取0...无数张，
 *
 * 请问凑满面额ani有多少种方法？
 *
 */

fun way1(array: IntArray, ani:Int):Int {
    if (array.isEmpty() || ani <= 0) {
        return 0
    }
    return process(array, 0, ani)
}

// 自由选择index...的面值，组成rest这么多钱，返回方法数
fun process(array: IntArray, index:Int, rest:Int):Int {

    if (index == array.size) {
        return if (rest == 0) 1 else 0
    }

    if (rest < 0) {
        return 0
    }

    var ways = 0
    var i = 0
    while (rest - array[index] * i >= 0) {
        ways += process(array, index+1, rest - array[index] * i)
        i++
    }
    return ways
}

fun way2(array: IntArray, ani:Int):Int {
    if (array.isEmpty() || ani <= 0) {
        return 0
    }
    var N = array.size

    var dp = Array(N+1){IntArray(ani+1)}
    for (i in 0 until N+1) {
        for (j in 0 until ani+1) {
            dp[i][j] = -1
        }
    }

    return process2(array, 0, ani, dp)
}

fun process2(array: IntArray,index:Int, rest:Int, dp:Array<IntArray>):Int {
    if (dp[index][rest] != -1) {
        return dp[index][rest]
    }
    if (index == array.size) {
        dp[index][rest] = if (rest == 0) 1 else 0
        return dp[index][rest]
    }
    var ways = 0
    var i = 0
    while (rest - array[index] * i >= 0) {
        ways += process2(array, index+1, rest - array[index] * i, dp)
//        ways += dp[index+1][rest - array[index] * i]  想想，这里为什么不是dp[][]？
//        求解的是index+1...，process符合用意，且自顶向下，这里dp[index+1][]实际还没有值！
        i++
    }
    dp[index][rest] = ways
    return dp[index][rest]
}

/**
 *
 * 如果这里没有枚举行为，自顶向下的记忆化搜索和这里从简单状态推到复杂状态本质上是一样的
 * 有了枚举行为，就存在可以优化空间
 *
 */
fun way3(array: IntArray, ani:Int):Int {
    if (array.isEmpty() || ani <= 0) {
        return 0
    }
    var N = array.size
    var dp = Array(N+1){IntArray(ani+1)}
    dp[N][0] = 1
//    dp[N][1...ani] = 0

    // 整体顺序为何从下往上？从"process"中可以得出：f(index)依赖f(index+1)，即可以从f(index+1)推出f(index)
    for (index in N-1 downTo 0) {
        for (rest in 0..ani) {
            var ways = 0
            var i = 0
            while (rest - array[index] * i >= 0) {
                ways += dp[index+1][rest - array[index] * i]
                 // 注意，这里是dp了!不是上面的Process，因为遍历方向已经是组织过，不再是传统的自顶向下
                i++
            }
            dp[index][rest] = ways
        }
    }

    return dp[0][ani]
}

fun way4(array: IntArray, ani:Int):Int {
    if (array.isEmpty() || ani <= 0) {
        return 0
    }
    var N = array.size
    var dp = Array(N+1){IntArray(ani+1)}
    dp[N][0] = 1
//    dp[N][1...ani] = 0

    // 整体顺序为何从下往上？从"process"中可以得出：f(index)依赖f(index+1)，即可以从f(index+1)推出f(index)
    for (index in N-1 downTo 0) {
        for (rest in 0..ani) {
            // 每一个行的值等于下一行值+左边列的值
            dp[index][rest] = dp[index+1][rest]
            if (rest - array[index] >= 0) {
                dp[index][rest] += dp[index][rest-array[index]]
            }
        }
    }

    return dp[0][ani]
}



fun main() {
    var array = intArrayOf(5, 10, 50, 100)
    var anim = 1000
    var ans = way1(array, anim)
    println("暴力递归：$ans")
    ans = way2(array, anim)
    println("动态规划-记忆化搜索：$ans")
    ans = way3(array, anim)
    println("动态规划-经典-第一版(与记忆化搜索等效，存在枚举)：$ans")
    ans = way4(array, anim)
    println("动态规划-经典-第二版：$ans")
}