//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1502 👎 0


class P_200_NumberOfIslands {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun dfs(grid: Array<CharArray>, i:Int, j:Int) {
        var n = grid.size
        var m = grid[0].size

        // 标记很重要！防止重复遍历
        grid[i][j] = '0'
        if (i-1 >= 0 && grid[i-1][j] == '1') dfs(grid, i-1, j)
        if (i+1 < n && grid[i+1][j] == '1') dfs(grid, i+1, j)
        if (j-1 >= 0 && grid[i][j-1] == '1') dfs(grid, i, j-1)
        if (j+1 < m && grid[i][j+1] == '1') dfs(grid, i, j+1)
    }

    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j)
                    count++
                }
            }
        }

        return count
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}