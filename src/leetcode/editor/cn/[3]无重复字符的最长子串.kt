//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 5918 👎 0


class P_3_LongestSubstringWithoutRepeatingCharacters {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // 再写一遍
        fun lengthOfLongestSubstring(s:String):Int {
            if (s.isEmpty()) return 0

            var rk = -1
            var ans = Int.MIN_VALUE
            var set = mutableSetOf<Char>()
            for (i in s.indices) {
                while (rk+1 < s.length
                        && set.contains(s[rk+1]).not()) {
                    rk++
                    set.add(s[rk])
                }
                ans = Math.max(ans, rk-i+1)
                set.remove(s[i])
            }
            return ans
        }

        /**
         * 为什么可以用双指针法？
         *
         * 第k个字符作为起始位置时，找到了不重复字符串的结束位置rk，
         * 因此左指针右移一位，从第k+1开始也是不重复的，由于去掉了
         * 第k个字符，因此可以右指针可以继续右移
         *
         */
    fun lengthOfLongestSubstring2(s: String): Int {
        if (s.isEmpty()) return 0

        // -1开始，0会错误！影响首位字符的add
        var rk = -1

        var set = HashSet<Char>()

        var ans = 0

        var n = s.length

        for (i in s.indices) {
            while (rk+1 < n && !set.contains(s[rk+1])) {
                set.add(s[rk+1])
                rk++
            }
            ans = Math.max(ans, rk - i + 1)
            set.remove(s[i])
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_3_LongestSubstringWithoutRepeatingCharacters.Solution().
            lengthOfLongestSubstring("abcabcbb"))
}