//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1750 👎 0


class P_46_Permutations {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        fun process(nums: IntArray, visited:BooleanArray, index:Int, ans:MutableList<List<Int>>, cur:MutableList<Int>) {
            if (index == nums.size) {
                ans.add(ArrayList(cur))
                return
            }
            for (i in nums.indices) {
                if (visited[i]) continue
                visited[i] = true
                cur.add(nums[i])
                process(nums, visited, index+1, ans, cur)
                cur.removeAt(cur.size-1)
                visited[i] = false
            }
        }

    fun permute(nums: IntArray): List<List<Int>> {
        var ans = mutableListOf<List<Int>>()
        process(nums, BooleanArray(nums.size), 0, ans, mutableListOf())
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}