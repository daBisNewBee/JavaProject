//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1333 👎 0


class P_76_MinimumWindowSubstring {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun minWindow(s: String, t: String): String {

        // 统计字符串t中每个字符的出现次数
        var tSeq = IntArray(128)

        for (c in t.toCharArray()) {
            tSeq[c.toInt()]++
        }

        var minWindowLen = s.length + 1

        var start = 0

        var end = -1

        var sameCount = 0

        var len = s.length

        // 用于记录窗口中每个字符的出现次数
        var winSeq = IntArray(128)

        var startIndex = -1

        while (start < len) {

            if (end + 1 < len && sameCount < t.length) {

                // 窗口右移，end向右滑动，直到找到匹配t的字符串为止
                winSeq[s[end+1].toInt()]++

                // 当前字符在 t 内
                if (winSeq[s[end+1].toInt()] <= tSeq[s[end+1].toInt()]) {
                    sameCount++
                }
                end++

            } else {
                // start 向右移动，开始找到最小的子字符串长度
                if (sameCount == t.length && (end-start+1) < minWindowLen) {
                    // 字符串t中的字符在窗口中全部出现
                    minWindowLen = end - start + 1
                    startIndex = start
                    println(s.substring(startIndex, startIndex + minWindowLen))
                }
                // 窗口左移
                winSeq[s[start].toInt()]--
                if (winSeq[s[start].toInt()] < tSeq[s[start].toInt()]) {
                    sameCount--
                }
                start++
            }
        }
        return if (startIndex != -1) s.substring(startIndex, startIndex+minWindowLen) else ""
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
//    var ret = P_76_MinimumWindowSubstring.Solution().minWindow("a", "a")
    var ret = P_76_MinimumWindowSubstring.Solution().minWindow("ab", "a")
//    var ret = P_76_MinimumWindowSubstring.Solution().minWindow("ADOBECODEBANC", "ABC")
    println(ret)
}