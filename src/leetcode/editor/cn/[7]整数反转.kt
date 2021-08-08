//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2984 👎 0


class P_7_ReverseInteger {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun reverse(_x: Int): Int {
        if (_x == 0) return 0
        var x = _x
        var rev = 0
        while (x != 0) {
            // 提前判断！注意："环境不允许存储 64 位整数"
            // 输入x是个有效整数，表示了其不会超过"2147483647"，即首位最大为2
            if (rev < Int.MIN_VALUE / 10 || rev > Int.MAX_VALUE / 10)
                return 0
            var digit = x % 10
            rev = rev * 10 + digit
            x /= 10
        }
        return rev
    }
        // 为什么不用关注最小位？
        /*
        * 重点在于2^31和2^31-1最大位为2，也就是说如果数字小于10位时不用去考虑越界问题。
        * 等于10位时，只用比较反转后的数字reverse前9位与最大最小值的前9位即可。
        * 因为输入数字为10位时，最高位不可能超过2，就是说反转之后reverse的最低位最大为2，
        * 不可能超过7，所以前9位不超过最大最小值前9位时，第10位（最低位）不存在超过最大
        * 最小值最低位的情况，也就自然不用关注最低位了。
        * */
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_7_ReverseInteger.Solution().reverse(1534236469))
}