import java.util.*
import kotlin.collections.ArrayList


//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 
// 👍 921 👎 0


class P_47_PermutationsIi {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun process(nums: IntArray, ans:MutableList<List<Int>>, index:Int,
                cur:MutableList<Int>, visited:BooleanArray) {
        if (index >= nums.size) {
            ans.add(ArrayList(cur))
            return
        }

        for (i in nums.indices) {
            // 这一步可以去重
            if (visited[i] || (i > 0 && nums[i-1] == nums[i] && !visited[i-1])) {
                continue
            }
            visited[i] = true
            cur.add(nums[i])
            process(nums, ans, index+1, cur, visited)
            visited[i] = false
            cur.removeAt(cur.size-1)
        }
    }


    fun permuteUnique(nums: IntArray): List<List<Int>> {

        Arrays.sort(nums)

        var ans = mutableListOf<List<Int>>()
        var visited = BooleanArray(nums.size)
        process(nums, ans, 0, mutableListOf(), visited)
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    P_47_PermutationsIi.Solution().permuteUnique(intArrayOf(1,1,2))
}