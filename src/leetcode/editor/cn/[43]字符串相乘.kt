//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 模拟 
// 👍 693 👎 0


class P_43_MultiplyStrings {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        fun multiply(num1: String, num2: String): String {
            if (num1 == "0" || num2 == "0") return "0"

            var ans = "0"
            for (i in num2.length-1 downTo 0) {
                var tmp = StringBuilder()
                repeat(num2.length - 1 - i) {
                    tmp.append(0)
                }
                var carry = 0
                var n2 = num2[i] - '0'

                var j = num1.length-1
                while (j >= 0 || carry != 0) {
                    var n1 = if (j >= 0) num1[j] - '0' else 0
                    var product = n1 * n2 + carry
                    tmp.append(product % 10)
                    carry = product / 10
                    j--
                }
                ans = addString(ans, tmp.reverse().toString())
            }
            return ans
        }

        fun addString(num1:String, num2:String):String {
            var i = num1.length-1
            var j = num2.length-1
            var carry = 0
            var ans = StringBuilder()
            while (i>=0 || j>=0 || carry != 0) {
                var n1 = if (i >= 0) num1[i]-'0' else 0
                var n2 = if (j >= 0) num2[j]-'0' else 0
                var sum = n1 + n2 + carry
                ans.append(sum % 10)
                carry = sum / 10
                i--
                j--
            }
            return ans.reverse().toString()
        }

        /**
         *
         * 优化竖式法：
         *
         * 几个规律点：
         * 1. num1(m位) * num2(n位) = res
         * res 的长度为 m + n 位
         *
         * 2. num1的第i位 * num2的第j位 的结果tmp
         * 的两位分别位于 res 的res[i+j]、res[i+j+1]
         *
         */
    fun multiply2(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") {
            return "0"
        }
        var res = IntArray(num1.length + num2.length)
        for (i in num1.length - 1 downTo 0) {
            for (j in num2.length - 1 downTo 0) {
                var n1 = num1[i] - '0'
                var n2 = num2[j] - '0'
                var sum = res[i+j+1] + n1 * n2
                res[i+j+1] = sum % 10
                res[i+j] += sum / 10
            }
        }
        var sb = StringBuilder()
        for (i in res.indices) {
            if (i == 0 && res[i] == 0) {
                continue
            }
            sb.append(res[i])
        }
        return sb.toString()
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_43_MultiplyStrings.Solution().multiply("123", "456"))
}