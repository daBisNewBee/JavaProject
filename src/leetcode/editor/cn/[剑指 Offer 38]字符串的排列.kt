import java.lang.StringBuilder

//输入一个字符串，打印出该字符串中字符的所有排列。
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 字符串 回溯 
// 👍 403 👎 0


class P_Offer_38_ZiFuChuanDePaiLieLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    fun swap(array: CharArray, p1:Int, p2:Int) {
        var tmp = array[p1]
        array[p1] = array[p2]
        array[p2] = tmp
    }

    fun perm(array: CharArray, begin:Int, ans:MutableSet<String>) {
        if (begin == array.size) {
            ans.add(String(array))
            return
        }
        // 固定一个begin，遍历后面的每一个i与begin互换
        for (i in begin until array.size) {
            swap(array, i, begin)
            perm(array, begin+1, ans)
            swap(array, i, begin)
        }
    }

    fun permutation(s: String): Array<String> {

        if (s.isEmpty()) return emptyArray()

        var ans = mutableSetOf<String>()

        perm(s.toCharArray(), 0, ans)

        return ans.toTypedArray()
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}