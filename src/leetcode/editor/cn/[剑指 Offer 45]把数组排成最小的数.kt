import com.sun.xml.internal.fastinfoset.util.StringArray
import java.lang.StringBuilder

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 
// 👍 333 👎 0


class P_Offer_45_BaShuZuPaiChengZuiXiaoDeShuLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class C :Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            var o1o2 = (o1.toString() + o2.toString()).toLong()
            var o2o1 = (o2.toString() + o1.toString()).toLong()
            if (o1o2 - o2o1 < 0) {
                return -1
            } else if (o1o2 - o2o1 > 0) {
                return 1
            } else {
                return 0
            }
            /**
             * 这样写不对！溢出
             *
             * return (o1.toString() + o2.toString()).toInt() - (o2.toString() + o1.toString()).toInt()
             *
             * 报错：
             *
                Line 5: Exception in thread "main" java.lang.NumberFormatException:
                For input string: "999999997999999998"
             */
        }
    }

    // 这个更简单
    class S:Comparator<String> {
        override fun compare(o1: String?, o2: String?): Int {
            return (o1 + o2).compareTo((o2 + o1))
        }
    }

    fun minNumber(nums: IntArray): String {
        if (nums.isEmpty()) return ""

        var strList = mutableListOf<String>()
        for (num in nums) {
            strList.add(num.toString())
        }
        strList.sortWith(S())

//        var c = C()
//        var ans = nums.sortedWith(c)
        var sb = StringBuilder()
        for (an in strList) {
            sb.append(an)
        }
        return sb.toString()
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}