import java.lang.StringBuilder

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1410 👎 0


class P_17_LetterCombinationsOfAPhoneNumber {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 回溯 + 递归
         */
        fun letterCombinations(digits: String): List<String> {
            if (digits.isEmpty()) return emptyList()
            var combination = ArrayList<String>()
            var map = mapOf<Char, String>(
                    '2' to "abc",
                    '3' to "def",
                    '4' to "ghi",
                    '5' to "jkl",
                    '6' to "mno",
                    '7' to "pqrs",
                    '8' to "tuv",
                    '9' to "wxyz"
            )
            backTrack(combination, map, digits, 0, StringBuilder())
            return combination
        }

        /**
         * combination : 最终结果
         * map: 数字->字符串 映射
         * digits：输入数字
         * index: 当前第几位
         * sb: 临时结果
         */
        fun backTrack(combination:MutableList<String>, map:Map<Char, String>, digits: String, index:Int, sb:StringBuilder) {
            if (index == digits.length) {
                combination.add(sb.toString())
            } else {
                var c = digits[index]
                var numStr = map[c]
                for (str in numStr!!.toCharArray()) {
                    sb.append(str.toString())
                    backTrack(combination, map, digits, index+1, sb)
                    sb.deleteCharAt(index)
                }
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
