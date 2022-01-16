import java.util.*
import kotlin.Comparator

//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 
//输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 贪心 字符串 排序 
// 👍 859 👎 0


class P_179_LargestNumber {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class MyComp:Comparator<String>{
        override fun compare(o1: String?, o2: String?): Int {
            var s1 = o1 + o2
            var s2 = o2 + o1
            return s2.compareTo(s1)
        }
    }

    fun largestNumber(nums: IntArray): String {
        var list = mutableListOf<String>()
        nums.forEach { list.add(it.toString()) }
        list.sortWith(Comparator { o1: String, o2: String -> (o2 + o1).compareTo(o1 + o2) })
//        Collections.sort(list, MyComp())
        var ans = ""
        list.forEach { ans += it }
        if (ans.startsWith("0")) return "0"
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}