//给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。 
//
// 请返回所有可行解 s 中最长长度。 
//
// 
//
// 示例 1： 
//
// 输入：arr = ["un","iq","ue"]
//输出：4
//解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
// 
//
// 示例 2： 
//
// 输入：arr = ["cha","r","act","ers"]
//输出：6
//解释：可能的解答有 "chaers" 和 "acters"。
// 
//
// 示例 3： 
//
// 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
//输出：26
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 16 
// 1 <= arr[i].length <= 26 
// arr[i] 中只含有小写英文字母 
// 
// Related Topics 位运算 数组 字符串 回溯 
// 👍 174 👎 0


class P_1239_MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    var ans = 0

    fun backTrack(masks:List<Int>, pos:Int, mask:Int) {
        if (pos == masks.size) {
            // "bitCount" ：十进制中有多少个"1"，"1"代表一个字符，因此说明有多少个字符
            ans = Math.max(Integer.bitCount(mask), ans)
            return
        }
        // 下面这两句还是要多理解下！
        // mask 和 masks[pos] 无公共元素
        if (mask and masks[pos] == 0) {
            backTrack(masks, pos+1, mask xor masks[pos])
        }
        backTrack(masks, pos+1, mask)
    }

    fun maxLength(arr: List<String>): Int {
        var masks = ArrayList<Int>()
        for (s in arr) {
            var mask = 0
            for (c in s.toCharArray()) {
                var ch:Int = c - 'a'
                if ((mask shr ch) and 1 != 0) {
                    // 过滤一个str中，若有重复的字符，这样的str直接丢弃
                    mask = 0
                    break
                }
                // "mask"相当于一个位图，记录一个str中各字符的分布情况
                mask = mask xor (1 shl ch)
            }
            if (mask > 0) {
                masks.add(mask)
            }
        }
        backTrack(masks, 0, 0)
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_1239_MaximumLengthOfAConcatenatedStringWithUniqueCharacters.
            Solution().maxLength(listOf("un","iq","ue")))
}