import kotlin.math.max

//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3947 👎 0


class P_5_LongestPalindromicSubstring {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun longestPalindrome(s: String): String {
        if (s.isEmpty() || s.length < 2) return s

        var n = s.length

        // 二维数组，表示dp[i][j] 表示第i开始，第j结束的字符串是否回文
        var dp = Array(n){BooleanArray(n)}

        var array = s.toCharArray()

        var maxLen = 1// 这里默认是1，不是0！！

        var beginIndex = 0

        // 初始化，所有长度为1的字符串都是回文
        for (i in 0 until n) {
            dp[i][i] = true
        }

        // 递推开始，ps：递推和递归的区别？
        // https://blog.csdn.net/weixin_44143702/article/details/86551826

        // 先枚举子串长度，不容易想到
        for (L in 2 .. n) {

            // 枚举左边界
            for (i in 0 until n) {

                // j 即有边界
                var j = i + L - 1

                if (j >= n) break

                if (array[i] != array[j]) {

                    dp[i][j] = false

                } else {
                    if (j - i < 3) {
                        dp[i][j] = true
                    } else {
                        // fixme: 这是关键!!不容易想到：如果"ababa"是回文，取决于"bab"是回文，且首尾都是'a'
                        dp[i][j] = dp[i+1][j-1]
                        // 比如这里,dp[0][4] = dp[1][3]
                    }
                }

                // 发现回文，记录长度和位置
                if (dp[i][j] && j-i+1 > maxLen) {
                    maxLen = j-i+1
                    beginIndex = i
                }
                // 一个L下，即使前面已经找到匹配字符串，也要继续完成循环，
                // 因为，对各个dp[i+1][j-1]赋值，方便后面更大长度dp[i][j]的计算
            }
        }
        return s.substring(beginIndex, beginIndex + maxLen)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_5_LongestPalindromicSubstring.Solution().longestPalindrome("ababa")
}