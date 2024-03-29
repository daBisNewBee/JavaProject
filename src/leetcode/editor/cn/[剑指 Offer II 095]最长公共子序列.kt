import kotlin.math.max

//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
//
// 
//
// 注意：本题与主站 1143 题相同： https://leetcode-cn.com/problems/longest-common-subsequenc
//e/ 
// Related Topics 字符串 动态规划 
// 👍 30 👎 0


class P_Offer_095_QJnOS7 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 先设计dp[i][j]，再赋予其意义：
         *
         * str1 从0...i, str2从0...j的最长公共子序列
         *
         * 其值可以拆分为：
         * dp[i-1][j] :         最长子序列包括str1的第i字符
         * dp[i][j-1] :         最长子序列包括str2的第j字符
         * dp[i-1][j-1] :       最长子序列不包括str1的第i、str2的第j字符
         * dp[i-1][j-1] + 1 :   最长子序列包括str1的第i、str2的第j字符
         *
         * 即，求4个的max
         *
         * 优化：
         * 实际在计算时，
         * 可以忽略dp[i-1][j-1]
         * 因为，dp[i-1][j]、dp[i][j-1]已经包括了dp[i-1][j-1]
         *
         */
    fun longestCommonSubsequence(text1: String, text2: String): Int {

        if (text1.isNullOrEmpty() || text2.isNullOrEmpty()) return 0

        var dp = Array(text1.length){IntArray(text2.length)}

        dp[0][0] = if (text1[0] == text2[0]) 1 else 0

        for (i in 1 until text1.length) {
            dp[i][0] = Math.max(dp[i-1][0], if (text2[0] == text1[i]) 1 else 0)
        }

        for (i in 1 until text2.length) {
            dp[0][i] = Math.max(dp[0][i-1], if (text2[i] == text1[0]) 1 else 0 )
        }

        for (i in 1 until text1.length) {
            for (j in 1 until text2.length) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
                if (text1[i] == text2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1)
                }
            }
        }
        return dp[text1.length-1][text2.length-1]
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_Offer_095_QJnOS7.Solution().longestCommonSubsequence("abcde", "ace")
    println(ans)
}