//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "rat", t = "car"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, t.length <= 5 * 104 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 哈希表 字符串 排序 
// 👍 479 👎 0


class P_242_ValidAnagram {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.isNullOrEmpty() || t.isNullOrEmpty()) return false

        if (s.length != t.length) return false

        var array = IntArray(26)

        s.toCharArray().forEach {
            array[(it-'a')]++
        }

        t.toCharArray().forEach {
            array[(it-'a')]--
            if (array[it-'a'] < 0) {
                return false
            }
        }
        return true
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}