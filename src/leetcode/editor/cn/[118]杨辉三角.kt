//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
// Related Topics 数组 动态规划 
// 👍 663 👎 0


class P_118_PascalsTriangle {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun generate(numRows: Int): List<List<Int>> {
        if (numRows <= 0) return emptyList()
        var ans = mutableListOf<MutableList<Int>>()
        for (i in 0 until numRows) {
            var curList = mutableListOf<Int>()
            if (i == 0) {
                curList.add(1)
            } else {
                for (j in 0 until i+1) {
                    var cur = 0
                    if (j <= ans[i-1].size - 1) {
                        cur = ans[i-1][j]
                    }
                    if (j-1 >= 0) {
                        cur += ans[i-1][j-1]
                    }
                    curList.add(cur)
                }
            }
            ans.add(curList)
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var ans = P_118_PascalsTriangle.Solution().generate(5)
    println(ans)
}