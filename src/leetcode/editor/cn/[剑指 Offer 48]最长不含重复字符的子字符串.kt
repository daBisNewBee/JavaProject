import kotlin.math.max

//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
//
// 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// s.length <= 40000 
// 
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-rep
//eating-characters/ 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 323 👎 0


class P_Offer_ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


        /**
         *
         * 主要思路：
         * 遍历，一旦遇到重复字符串j，需要从上一个重复i的下一个索引i+1开始，计算新长度！
         * 因为[i+1, j]必然不重复！fixme :相减可以取得最大长度主要原因！
         *
         * 比如：
         * abcabcbb
         *
         * a 时 长度1
         * b 时 长度2
         * c 时 长度3
         *
         * 第二个
         * a 时，找到前一个a的位置"0"，
         *
         * 3 - 0 就是当前不重复的字符串长度
         *
         */
    fun lengthOfLongestSubstring(s: String): Int {
        // 各字符最后一次出现的位置
        var map = mutableMapOf<Char, Int>()
        var ans = 0
        // 为什么是 -1？
        // 左开右闭，表示窗口(start,j]，所以这里start初始值为-1，不是0
        var start = -1
        for (j in s.indices) {
            var c = s[j]
            if (map.containsKey(c)) {
                start = Math.max(start, map[c]!!)
            }
            map[c] = j
            ans = Math.max(ans, j-start)
        }
        return ans
    }

        // 自己写出来可以跑！就是略显复杂！
   fun lengthOfLongestSubstring_Mine(s: String): Int {
        if (s.isNullOrEmpty()) return 0
        var maxLen = 0
        var low = 0
        var fast = low
        var res = IntArray(128)
        while (fast < s.length) {
            // 右移窗口，扩大区域
            while (fast < s.length && res[s[fast].toInt()] == 0) {
                maxLen = Math.max(maxLen, fast - low + 1) // +1 就是闭合窗口
                res[s[fast].toInt()]++
                fast++
            }
            if (fast == s.length) {
                break
            }
            // 左移窗口，去重。遇到了相同字母
            while (low <= fast && s[low] != s[fast]) {
                res[s[low].toInt()] = 0
                low++
            }
            low++
            fast++
        }
        return maxLen
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
//    var ret = P_Offer_ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof.Solution().lengthOfLongestSubstring(" ")
    var ret = P_Offer_ZuiChangBuHanZhongFuZiFuDeZiZiFuChuanLcof.Solution().lengthOfLongestSubstring("abcabcbb")
    println(ret)
}