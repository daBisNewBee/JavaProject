//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都 互不相同 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1734 👎 0


class P_39_CombinationSum {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 这么解就没有重复解，想想为什么？避免了重复解！！
         *
         */
        fun dfs(candidates: IntArray, rest: Int, index:Int, ans:MutableList<List<Int>>, cur:MutableList<Int>) {
            if (rest == 0) {
                ans.add(ArrayList(cur))
                return
            }
            if (index == candidates.size) {
                return
            }
            dfs(candidates, rest, index+1, ans, cur)
            if (rest - candidates[index] >= 0) {
                cur.add(candidates[index])
                dfs(candidates, rest-candidates[index], index, ans, cur)  // 注意这里不是"index+1"!!因为下一个仍能从该位置枚举
                cur.removeAt(cur.size-1)
            }
        }

        /**
         *
         * 不行！有重复解
         *
         * 2,3,6,7:
         *
         *  2 2 3
            2 3 2
            3 2 2
            7
         *
         */
    fun process(candidates: IntArray, rest: Int, ans:MutableList<List<Int>>, cur:MutableList<Int>) {
        if (rest == 0) {
            ans.add(ArrayList(cur))
            return
        }
        if (rest < 0) {
            return
        }
        for (i in candidates.indices) {
            cur.add(candidates[i])
            process(candidates, rest-candidates[i], ans, cur)
            cur.removeAt(cur.size-1)
        }
    }

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        var ans = mutableListOf<List<Int>>()
//        process(candidates, target, ans, mutableListOf())
        dfs(candidates, target, 0, ans, mutableListOf())
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_39_CombinationSum.Solution().combinationSum(intArrayOf(2,3,6,7), 7)
    ans.forEach {
        it.forEach { num -> print("$num ") }
        println()
    }
}