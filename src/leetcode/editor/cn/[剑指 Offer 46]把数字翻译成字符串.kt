//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 231 
// 
// Related Topics 字符串 动态规划
// 👍 334 👎 0


class P_Offer_46_BaShuZiFanYiChengZiFuChuanLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * "从左到右"的尝试模型.
         *
         * 拿到题目按照模型套;
         * 不够参数就补，比如先写出来index，再补result
         *
         * index: 当前要处理的是第"index"位字符
         *        前0..index-1 字符都已经处理好了
         *
         * 该提分支较多.
         *
         * 类似题型：
         * 1. 字符串的有多少子串
         *      某一路径下的字符，要或者不要
         *
         * 2. 字符串有多少子序列
         *      固定i之前的字符，即0~i-1，尝试后面i、j交换
         *      优化：分支限界：第一个位置a尝试过了，后面再有a来到这个位置，就不再尝试了
         *
         * 3. 汉诺塔
         *
         */
    fun translate(array: CharArray, index:Int, result:MutableSet<String>, path:String) {
        if (index == array.size) {
            result.add(path)
            return
        }
        if (array[index] < '0' || array[index] > '9') {
            return
        }
        if (array[index] == '0') {
            translate(array, index + 1, result, path + "a")
        }
        else if (array[index] == '1') {
            translate(array, index+1, result, path + "b")
            if (index + 1 < array.size) {
                var num = String(array, index, 2)
                var c = (Integer.valueOf(num) + 'a'.toInt()).toChar()
                translate(array, index + 2, result, path + c)
            }
        }
        else if (array[index] == '2') {
            translate(array, index+1, result, path + "c")
            if (index + 1 < array.size && array[index+1] >= '0' && array[index+1] <= '5') {
                var num = String(array, index, 2)
                var c = (Integer.valueOf(num) + 'a'.toInt()).toChar()
                translate(array, index + 2, result, path + c)
            }
        }
        else {
            var c = (Integer.valueOf(array[index].toString()) + 'a'.toInt()).toChar()
            translate(array, index + 1, result, path + c)
        }
    }

        /**
         *
         * 动态规划直接改写暴力尝试
         *
         * 想想为什么可以直接改写？一些思路！！！
         *
         * 1. 一维数组。
         *    因为只有一个变量:index
         *
         * 2. 一维数组长度 array.size + 1。
         *    因为存在判断"index == array.size"，index最大可以取到 "N"
         *
         * 3. 给dp某些元素赋默认值。
         *    dp[N] = 1
         *    因为
         *      if (index == array.size) {
                    result.add(path)
                    return
                }
              表示当 index == N时，存在一个解
         *
         * 3. 遍历方向：从右往左
         *    因为要求"translate(...0....)" ,即要求dp[0]
         *    已知dp[N] = 1
         *    且dp[i] = dp[i+1] + dp[i+2]，当前值依赖更高阶的值
         *    所以不难得出，方向为从右到左
         *
         * 4. 直接复制translate的逻辑，并改写。
         *    一个"translate(i)"就是一个dp[i] 的求解过程
         *
         */
    fun dpWays(array: CharArray, dp:IntArray):Int {
        dp[array.size] = 1
        for (i in array.size-1 downTo 0) {
            if (array[i] < '0' || array[i] > '9') {
                continue
            }
            when(array[i]) {
                '0' -> {
                    dp[i] = dp[i+1]
                }
                '1' -> {
                    dp[i] = dp[i+1]
                    if (i+1 < array.size) {
                        dp[i] += dp[i+2]
                    }
                }
                '2' -> {
                    dp[i] = dp[i+1]
                    if (i+1 < array.size && array[i+1] >= '0' && array[i+1] <= '5') {
                        dp[i] += dp[i+2]
                    }
                }
                else -> {
                    dp[i] = dp[i+1]
                }
            }
        }

        return dp[0]
    }

    fun translateNum(num: Int): Int {
        var result = mutableSetOf<String>()
        translate(num.toString().toCharArray(), 0, result, "")

        var array = num.toString().toCharArray()
        var dp = IntArray(array.size + 1)
        var ans = dpWays(num.toString().toCharArray(), dp)

        println("暴力结果：${result.size} 动态规划结果：$ans")
        return ans
//        return result.size
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_Offer_46_BaShuZiFanYiChengZiFuChuanLcof.Solution().translateNum(18822)
}