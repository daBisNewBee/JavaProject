//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" ，将消息分组为 (1 1 10 6) 
// "KJF" ，将消息分组为 (11 10 6) 
// 
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。 
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 
//
// 示例 4： 
//
// 
//输入：s = "06"
//输出：0
//解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 1046 👎 0


class P_91_DecodeWays {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        fun isValid2Num(c1:Char, c2:Char):Boolean {
            return c1 == '1' && c2 in '0'..'9' || c1 == '2' && c2 in '0'..'6'
        }

        // 继续干！学无止境：动态方程
        fun dpWay3(s:String):Int {
            var N = s.length
            var dp = IntArray(N+1)
            // dp[i] ： 长度为i的字符串解码数

            dp[0] = 1 // 空串解码出来空字符串
            for (i in 1 .. N) {
                // 最后一位按照单个字符解码
                if (s[i-1] != '0') {
                    dp[i] = dp[i-1]
                }
                // 最后一位按照两个字符解码
                if (i-1 > 0 && isValid2Num(s[i-2], s[i-1])) {
                    dp[i] += dp[i-2] // fixme：容易错！这里要用dp[i-2]的值，不能简单加1
                }
            }

            return dp[N]
        }

        // 再写一遍
        fun dpWay2(s:String):Int {
            var dp = IntArray(s.length+1)
            dp[s.length] = 1
            dp[s.length-1] = if (s[s.length-1] == '0') 0 else 1

            for (i in s.length-2 downTo 0) {
                if (s[i] == '0') {
                    dp[i] = 0
                    continue
                }
                dp[i] = dp[i+1]
                if (s[i] == '1') {
                    if (s[i+1] in '0'..'9') {
                        dp[i] += dp[i+2]
                    }
                } else if (s[i] == '2') {
                    if (s[i+1] in '0'..'6') {
                        dp[i] += dp[i+2]
                    }
                }
            }
            return dp[0]
        }

        // 本质还是对暴力递归的改写
        fun dpWay(s:String):Int {
            if (s[0] == '0') return 0
            var dp = IntArray(s.length+1)
            dp[s.length] = 1
            dp[s.length-1] = if (s[s.length-1] == '0') 0 else 1

            for (i in s.length - 2 downTo 0) {
                if (s[i] == '0') {
                    dp[i] = 0
                    continue
                }
                dp[i] = dp[i+1]
                if (s[i] == '1') {
                    // 注意两位的时候，后一位边界判断不同！
                    if (s[i+1] in '0'..'9') {
                        dp[i] += dp[i+2]
                    }
                }
                else if (s[i] == '2') {
                    // 注意两位的时候，后一位边界判断不同！
                    if (s[i+1] in '0'..'6') {
                        dp[i] += dp[i+2]
                    }
                }
            }
            return dp[0]
        }

        fun process(s:String, index:Int):Int {
            if (index >= s.length) {
                return 1
            }
            if (index == s.length - 1) {
                return if (s[s.length-1] == '0') 0 else 1
            }
            if (s[index] == '0') return 0
            var ans = 0
            // 选中一位
            ans = process(s, index + 1)

            // 选择两位
            if (s[index] == '1') {
                if (index + 1 < s.length && s[index+1] in '0'..'9') {
                    ans += process(s, index+2)
                }
            }
            else if (s[index] == '2') {
                if (index + 1 < s.length && s[index+1] in '0'..'6') {
                    ans += process(s, index+2)
                }
            }

            return ans
        }

    fun numDecodings(s: String): Int {
        if (s.isNullOrEmpty()) return 0
        if (s[0] == '0') return 0
        return dpWay3(s)
//        return dpWay2(s)
//        return process(s, 0)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_91_DecodeWays.Solution().numDecodings("1123"))
}