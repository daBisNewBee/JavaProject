import java.util.*

//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 递归 数学 字符串 
// 👍 608 👎 0


class P_224_BasicCalculator {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 其实还有点懵逼的
    fun calculate(s: String): Int {
        var sign = 1
        var ops = Stack<Int>()
        ops.push(1)
        var n = s.length
        var i = 0
        var ans = 0

        while (i < n) {
            if (s[i] == ' ') {
                i++
                continue
            } else if (s[i] == '+') {
                sign = ops.peek()
                i++
            } else if (s[i] == '-') {
                // "每当遇到一个以 -− 号开头的括号，则意味着此后的符号都要被「翻转」"
                sign = -ops.peek()
                i++
            } else if (s[i] == '(') {
                ops.push(sign)
                i++
            } else if (s[i] == ')') {
                ops.pop()
                i++
            } else {
                var num = 0
                // 这里可能是多位整数的情况
                while ( i < n && Character.isDigit(s[i])) {
                    num = num * 10 + (s[i] - '0')
                    i++
                }

                ans += sign * num
            }
        }
        return ans
    }

        /**
         *
         * 不能直接忽略括号！是错的！
         *
         * 	解答失败:
            测试用例:"- (3 + (4 + 5))"
            测试结果:6
            期望结果:-12
         */
    fun calculate2(s: String): Int {
        var array = s.toCharArray()
        var ans = 0
        var isPlus = true
        var num = 0
        for (i in array.indices) {
            var c = array[i]
            if (c == '+') {
                isPlus = true
            } else if (c == '-') {
                isPlus = false
            } else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0')
                if (i < array.size - 1
                        && Character.isDigit(array[i+1])) {
                    continue
                }
                if (isPlus) {
                    ans += num
                } else {
                    ans -= num
                }
                num = 0
            }
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_224_BasicCalculator.Solution().calculate2(Int.MAX_VALUE.toString()))
}