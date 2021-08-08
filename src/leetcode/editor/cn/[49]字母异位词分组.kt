import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词指字母相同，但排列不同的字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2: 
//
// 
//输入: strs = [""]
//输出: [[""]]
// 
//
// 示例 3: 
//
// 
//输入: strs = ["a"]
//输出: [["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 104 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 
// 👍 812 👎 0


class P_49_GroupAnagrams {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // 排序。时间：O(nklogk)。
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        var ans = ArrayList<ArrayList<String>>()
        var map = HashMap<String, ArrayList<String>>()
        for (str in strs) {
            var arrays = str.toCharArray()
            Arrays.sort(arrays)
            var res = map.getOrDefault(String(arrays), ArrayList())
            res.add(str)
            map[String(arrays)] = res
        }
        map.values.forEach {
            ans.add(it)
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_49_GroupAnagrams.Solution().groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat"))
}