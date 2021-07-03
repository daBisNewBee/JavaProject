package offer.other

/**
 *
 * 41、和为S的连续正数序列
 *
 * "滑动窗口"的思想，使用双指针定位滑动窗口的上下边界，用两个数low和high分别指向当前序列中的最大和最小值，
 * 初始low为1，high为2。
 *
 * 如果从low到high的序列的和大于给定的S，那么说明可以去掉一个比较小的值，即增大low的值（相当于去掉了一个最小值，窗口收缩）。
 * 反之，如果从low到high的序列和小于给定的S，则应该增加一个值，即增大high（相当于窗口扩张，让这个窗口包含更多的值）
 *
 *
 * "滑动窗口"的特点：
 *
 * 1. 左边界i和右边界j来唯一表示一个窗口
 * 2. 滑动代表，窗口始终从左往右移动，
 *    这也表明左边界i和右边界j始终会往后移动，而不会往左移动。
 *
 */

// 滑动窗口法：O(N)，时间最快
fun findContinuousSequence(n:Int):MutableList<MutableList<Int>>{
    var result :MutableList<MutableList<Int>> = ArrayList()
    var low = 1
    var high = 2
    var curSum = low + high
    // 注意这里的结束条件：左指针追上右指针；或者左指针超过一半停止
    while (low < high && low < (curSum+1)/2) {
        when {
            curSum == n -> {
                var curResult = ArrayList<Int>()
                for (i in low .. high) {
                    curResult.add(i)
                }
                result.add(curResult)
                // 这里要缩小窗口，继续滑动窗口比较
                curSum-=low
                low++
            }
            curSum < n -> {
                high++
                curSum+=high
            }
            else -> {
                curSum-=low
                low++
            }
        }
    }
    return result
}

// 前缀法：
// sum[i]表示前i个数的和。
// 比如sum[1] = 1,表示前一个数的和为1，sum[2] = 3, 表示前2个数的和为3.
// 现在我们要求区间[2,4]表示求第2,3,4个数的和，就等于sum[4] - sum[1] = 9
// 时间:o(n2), 比较慢，这个思路还是值得了解下
fun findContinuousSequence2(n:Int):MutableList<MutableList<Int>>{
    var result :MutableList<MutableList<Int>> = ArrayList()
    var tmp = 0
    for (i in 1..n/2) {
        for(j in i..n) {
            tmp+=j
            if (tmp == n) {
                var curResult = ArrayList<Int>()
                for (one in i .. j) {
                    curResult.add(one)
                }
                result.add(curResult)
            } else if (tmp > n){
                tmp = 0
                break
            }
        }
    }
    return result
}

fun main(){
    println(findContinuousSequence(100))
    println(findContinuousSequence2(100))
}