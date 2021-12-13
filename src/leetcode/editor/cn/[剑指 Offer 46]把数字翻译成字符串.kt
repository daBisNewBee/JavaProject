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
            return
        }
        if (array[index] == '1') {
            translate(array, index+1, result, path + "b")
            if (index + 1 < array.size) {
                var num = String(array, index, 2)
                var c = (Integer.valueOf(num) + 'a'.toInt()).toChar()
                translate(array, index + 2, result, path + c)
            }
        }
        if (array[index] == '2') {
            translate(array, index+1, result, path + "c")
            if (index + 1 < array.size && array[index+1] >= '0' && array[index+1] <= '5') {
                var num = String(array, index, 2)
                var c = (Integer.valueOf(num) + 'a'.toInt()).toChar()
                translate(array, index + 2, result, path + c)
            }
        }

        var c = (Integer.valueOf(array[index].toString()) + 'a'.toInt()).toChar()
        translate(array, index + 1, result, path + c)
    }

    fun translateNum(num: Int): Int {
        var result = mutableSetOf<String>()
        translate(num.toString().toCharArray(), 0, result, "")
        return result.size
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_Offer_46_BaShuZiFanYiChengZiFuChuanLcof.Solution().translateNum(18822)
}