//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 988 👎 0


class P_79_WordSearch {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 自己写出来的！我好🐂！
         *
         * 回溯法，联系"200岛屿数量"，类似
         *
         */
    fun isExist(word: String, index:Int, i:Int, j:Int, board: Array<CharArray>, visited:Array<BooleanArray>):Boolean {
        if (index >= word.length) return true
        if (board[i][j] != word[index]) return false

        var row = board.size
        var col = board[0].size
        var ans = false
        if (j + 1 < col && board[i][j+1] == word[index] && !visited[i][j+1]) {
            visited[i][j+1] = true
            ans = isExist(word, index + 1, i, j+1, board, visited)
            visited[i][j+1] = false
        }
        if (ans) return true
        if (j - 1 >= 0 && board[i][j-1] == word[index] && !visited[i][j-1]) {
            visited[i][j-1] = true
            ans = isExist(word, index + 1, i, j-1, board, visited)
            visited[i][j-1] = false
        }
        if (ans) return true
        if (i + 1 < row && board[i+1][j] == word[index] && !visited[i+1][j]) {
            visited[i+1][j] = true
            ans = isExist(word, index + 1, i+1, j, board, visited)
            visited[i+1][j] = false
        }
        if (ans) return true
        if (i - 1 >= 0 && board[i-1][j] == word[index] && !visited[i-1][j]) {
            visited[i-1][j] = true
            ans = isExist(word, index + 1, i-1, j, board, visited)
            visited[i-1][j] = false
        }
        return ans
    }

    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (word.isEmpty()) return false

        var row = board.size
        var col = board[0].size
        // 标记已访问过的位置，防止重复访问！比如case "ABCB"
        var visited = Array(row){BooleanArray(col)}
        for (i in 0 until row) {
            for (j in 0 until col) {
                if (isExist(word, 0, i, j, board, visited)) {
                    return true
                }
            }
        }
        return false
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var data = Array(3){CharArray(4)}
    data[0] = charArrayOf('A','B','C','E')
    data[1] = charArrayOf('S','F','C','S')
    data[2] = charArrayOf('A','D','E','E')

    println(P_79_WordSearch.Solution().exist(data, "ABCB")) // false
//    println(P_79_WordSearch.Solution().exist(data, "ABCCED")) // true
}