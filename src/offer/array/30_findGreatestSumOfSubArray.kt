package offer.array

import kotlin.math.max

/*
*
* 30、连续子数组的最大和
*
* 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。
*
* 求所有子数组的和的最大值。要求时间复杂度为 O(n).
*
* Dynamic Programming DP
*
* 注意点：
*
* 1.有关键字”最大“，是一道最优问题，可能是一道DP问题
*
*
* 一个求解过程：
* https://www.zhihu.com/question/23995189
*
* */

/*
*
* 动态规划：（状态转移方程）
* res[i] = max(res[i-1] + data[i], data[i])
*
* res[i]: 第i个元素结尾的子数组的最大和
*
* */
fun findGreatestSumOfSubArray(list: List<Int>):Int {
    if (list.isEmpty()) return Int.MIN_VALUE

    var sumI = list[0]

    var maxResult = sumI

    for (i in 1 until list.size) {
        sumI = if ((sumI + list[i]) > list[i])
            sumI + list[i] else sumI
        if (sumI > maxResult) {
            maxResult = sumI
        }
    }

    return maxResult
}


/*
* fixme: 推荐 ！！也是动态规划，更容易理解！
*
* dp[n]代表以当前元素为截止点的连续子序列的最大和，
*
* 如果dp[n-1]>0，dp[n]=dp[n]+dp[n-1]，因为当前数字加上一个正数一定会变大；
*
* 如果dp[n-1]<0，dp[n]不变，因为当前数字加上一个负数一定会变小。
*
* */
fun findGreatestSumOfSubArray2(list: MutableList<Int>):Int {
    if (list.isEmpty()) return Int.MIN_VALUE

    var maxResult = list[0]

    for (i in 1 until list.size) {
        list[i] += if (list[i-1] > 0) list[i-1] else 0
        maxResult = max(list[i], maxResult)
    }
    return maxResult
}

fun main() {
    var data = mutableListOf(6,-3,-2,7,-15,1,2,2)

    var ret = findGreatestSumOfSubArray(data)

    println("结果: $ret")

    ret = findGreatestSumOfSubArray2(data)

    println("结果: $ret")
}