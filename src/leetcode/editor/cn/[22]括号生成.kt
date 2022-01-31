import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1942 👎 0


class P_22_GenerateParentheses {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun process(n:Int, index:Int, ans: MutableList<String>, sb:StringBuilder, leftCount:Int) {
        if (index == n shl 1) {
            ans.add(sb.toString())
            return
        }
        var rightCount = sb.length - leftCount

        // 只能加左括号
        if (leftCount == 0 || leftCount == rightCount) {
            process(n, index+1, ans, sb.append("("), leftCount+1)
            sb.deleteCharAt(sb.length-1)
        } else {
            // 可以左括号
            if (leftCount < n) {
                process(n, index+1, ans, sb.append("("), leftCount+1)
                sb.deleteCharAt(sb.length-1)
            }
            // 可以右括号
            process(n, index+1, ans, sb.append(")"), leftCount)
            sb.deleteCharAt(sb.length-1)
        }
    }

    fun generateParenthesis(n: Int): List<String> {
        var sb = StringBuilder(n shl 1)
        var ans = mutableListOf<String>()
        process(n, 0, ans, sb, 0)
        return ans
    }

        // 回溯法
    fun helper(n:Int, open:Int, close:Int, sb:StringBuilder, ans:MutableList<String>) {
        if (sb.length == n * 2) {
            ans.add(sb.toString())
            return
        }
        // 条件1：左括号数量不小于n
        if (open < n) {
            sb.append('(')
            helper(n, open + 1, close, sb, ans)
            sb.deleteCharAt(sb.length - 1)
        }
        // 这个条件约束了"("一定在")"之前！在易错点：这里不是"close < n"!!
        // 条件2：右括号数量小于左括号数量(不容易想到！！)
        if (close < open) {
            sb.append(')')
            helper(n, open, close + 1, sb, ans)
            sb.deleteCharAt(sb.length - 1)
        }
    }

    fun generateParenthesis2(n: Int): List<String> {
        var sb = StringBuilder(n * 2)
        var ans = ArrayList<String>()
        helper(n, 0, 0, sb, ans)
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_22_GenerateParentheses.Solution().generateParenthesis(3))
}