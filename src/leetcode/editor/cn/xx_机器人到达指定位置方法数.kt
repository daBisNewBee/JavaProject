package leetcode.editor.cn

/**
 *
 * 假设有排成一行的N个位置，记为1~N，N一定大于等于2.开始时机器人在其中的M位置上，
 * 机器人可以往左走，也可以往右走，如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置，规定机器人必须走K步，
 * 最终能来到P位置的方法有多少种？
 *
 *
 * "P50 暴力递归到动态规划2"
 * 左程云
 *
 */


// 暴力递归
fun walk(N:Int, M:Int, K:Int, P:Int):Int {
    if (K == 0) {
        return if (M == P) 1 else 0
    }
    if (M == 0) {
        return walk(N, 1, K-1, P)
    }
    if (M == N) {
        return walk(N, N-1, K-1, P)
    }
    return walk(N, M-1, K-1, P) + walk(N, M+1, K-1, P)
}

/**
 *
 * 动态规划：
 *      把“参数组合”玩成“一个结构化的缓存”
 *
 *  参数组合？
 *      M、K，变量只有这两个
 *
 *  结构化缓存？
 *      dp
 *
 *  为什么是二维数组？
 *      M、K，变量有两个
 *
 *  要解决的本质问题？
 *      暴力递归存在重复解
 *
 *
 */
fun walk2(N:Int, M:Int, K:Int, P:Int, dp:Array<IntArray>):Int {
    if (K == 0) {
        dp[M][K] = if (M == P) 1 else 0
        return dp[M][K]
    }
    if (M == 0) {
        dp[M][K] = walk(N, 1, K-1, P)
        return dp[M][K]
    }
    if (M == N) {
        dp[M][K] = walk(N, N-1, K-1, P)
        return dp[M][K]
    }
    dp[M][K] = walk(N, M-1, K-1, P) + walk(N, M+1, K-1, P)
    return dp[M][K]
}

fun main() {
    // 暴力递归方法
    var N = 50 // 总数
    var M = 30  // 当前位置
    var K = 30  // 剩余步数
    var P = 32  // 目标位置

    var start = System.currentTimeMillis()
    var ans = walk(N, M, K, P)
    println("暴力递归方法: $ans ${System.currentTimeMillis() - start}ms")

    var array = Array(M+1){IntArray(K+1)}
    for (i in 1..M) {
        for (j in 0..K) {
            array[i][j] = -1
        }
    }
    start = System.currentTimeMillis()
    ans = walk2(N, M, K, P, array)
    println("动态规划方法: $ans ${System.currentTimeMillis() - start}ms")

    /**
     * 暴力递归方法: 145422675 3491ms
       动态规划方法: 145422675 2581ms
     */

}