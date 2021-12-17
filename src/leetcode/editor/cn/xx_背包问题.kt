package leetcode.editor.cn

import kotlin.math.max

/**
 *
 *  n件物品，它们装入背包所占的容量分别为w1、w2……wn;它们所拥有的价值分别为v1、v2 ……vn；
 *
    有一个总容量为C的背包；

    在装满背包的情况下，如何使得包内的总价值最大？

    该问题的特点是：每个物品仅有一个，可以选择放或者不放，也就是说每个物品只能使用一次。
 *
 */

/**
 *
 * index:
 *      index... 物品自由选择
 *
 * rest:
 *      剩下的空间
 *
 * return:
 *
 *      返回能够获得的最大价值
 *
 */
fun process(weight:IntArray, value:IntArray, index:Int, rest:Int):Int {
    if (rest < 0) {
        return -1
    }
    // rest >= 0
    if (index == weight.size) {
        return 0
    }

    var p1 = process(weight, value, index+1, rest)

    var p2 = -1

    var p2Next = process(weight, value, index+1, rest - weight[index])
    if (p2Next != -1) {
        p2 = p2Next + value[index]
    }
    return Math.max(p1, p2)
}

/**
 *
 * 判断有没有重复解？
 *
 * 2,1,3,5,4
 *
 * 求f(0,20) ，第0位置，剩余空间20
 *
 * 选、选、不选   -> f(3,17)
 * 不选，不选，选 -> f(3,17) 重复解！
 *
 *
 */

fun dpWays(weight:IntArray, value:IntArray, bag:Int):Int {

    var N = weight.size

    var dp = Array(N + 1){IntArray(bag + 1)}

//    dp[weight.size][0] = 0
    for (index in weight.size-1 downTo 0) {
        for (rest in 0..bag) {
            var p1 = dp[index+1][rest]
            var p2 = -1
            if (rest - weight[index] >= 0) {
                p2 = dp[index+1][rest-weight[index]] + value[index]
            }
            dp[index][rest] = max(p1, p2)
        }
    }
    return dp[0][bag]
}



fun main() {
    var weight = intArrayOf(2,4,5,6,7)
    var value = intArrayOf(3,4,5,6,7)
    var bagSize = 20

    var ans = process(weight, value, 0, bagSize)
    println("暴力递归: $ans")

    ans = dpWays(weight, value, bagSize)
    println("动态规划: $ans")

}