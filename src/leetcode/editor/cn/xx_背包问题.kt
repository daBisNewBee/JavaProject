package leetcode.editor.cn

import kotlin.math.max

/**
 *
 * 思路：
 *
 * 题 -> 暴力递归写法(尝试) -> (有重复解/可变参数、不讲究子问题的组织) -> 记忆化搜索
 *
 * -> 精细化组织 -> 经典动态规划
 *
 *
 * 所以，"记忆化搜索"是什么？要不要继续改写"经典动态规划"？
 *
 * 什么是记忆化搜索？
 * 拿不到缓存就计算值并保存，拿到缓存就返回。
 * 自顶向下的动态规划，就是普通的递归，不需要考虑子问题的组织方式，原来怎么递归就怎么递归
 *
 * 不要改经典：
 * "任何一个状态，只依赖有限个子状态，不需要枚举"。那就不需要改写成"经典动态规划"了，因此时间复杂度一样。
 *
 * 比如
 * 机器人、背包、纸牌获胜、
 *
 * 要改经典：
 * 如果表中某个位置的计算，有枚举行为，那就要改经典动态规划
 * 因为，动态转移后，可以化简枚举行为，减少复杂度，有用
 *
 *
 * 可变参数找全，就不会有后效性
 * 有后效性，可能是可变参数没找全
 * 可变参数选对了，就是无后效性
 *
 * 什么是无后效性？
 *
 * ex. "我们不必关心一个状态i，它是从i+1取一个石子变化而来还是从i+2取两个石子变化而来。"
 *
 * 就是说现在状态是一个分割点，过去到现在的过程不影响现在到未来的走向，未来的走向只和现在有关
 *
 * 哈哈 ！你后面能娶多少老婆，只取决于你现在有多少钱，至于你现在的钱怎么来的，偷的、抢的、赚的，不影响你未来娶多少老婆
 *
 *
 *
 *
 */



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
 * 时间复杂度比较：
 *
 * 暴力递归：
 * 2的N次幂
 *
 * dp：()
 * N * bag : 二维表遍历一次，整张表有多少格子，就会遍历多少次
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