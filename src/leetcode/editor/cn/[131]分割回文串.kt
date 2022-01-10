//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 953 👎 0


class P_131_PalindromePartitioning {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    var result = mutableListOf<MutableList<String>>()
    var ans = mutableListOf<String>()

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    fun isHuiWen(s: String, i:Int, j:Int, cache:Array<IntArray>):Int {
        if (cache[i][j] != 0) {
            return cache[i][j]
        }
        if (i >= j) {
            cache[i][j] = 1
        } else {
            cache[i][j] = if (s[i] == s[j] && isHuiWen(s, i+1, j-1, cache) == 1) 1 else -1
        }
        return cache[i][j]
    }

    // 当来到了第index个字符， 前0...index-1 都已经处理好回文，且放到ans中了。
    // process 作用:寻找第index开始的回文串
    fun process(s: String, index:Int, cache:Array<IntArray>) {
        if (index == s.length) {
            result.add(ArrayList(ans))
            return
        }
        for (j in index until s.length) {
            // index..j 是回文串
            if (isHuiWen(s, index, j, cache) == 1) {
                ans.add(s.substring(index, j+1))
                // 从第j+1 开始查找
                process(s, j+1, cache)
                ans.removeAt(ans.size-1) // 回溯
            }
        }

    }

    fun partition(s: String): List<List<String>> {
        var cache = Array(s.length){IntArray(s.length)}
        process(s, 0, cache)
        return result
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_131_PalindromePartitioning.Solution().partition("aab")
}