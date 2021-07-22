package offer.online

import java.lang.StringBuilder

/**
 * 整数转换英文表示
 *
 * 对于数字 1234567890，我们将它从低位开始每三个分成一组，得到 1,234,567,890，
 * 它的英文表示为 1 Billion 234 Million 567 Thousand 890。这样我们就将原问
 * 题分解成若干个三位整数转换为英文表示的问题了。
 *
 */

val THOUSAND = arrayOf("", "Thousand", "Million", "Billion")
val LESS_THAN_TWENTY = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
val HUNDRED = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

fun numberToWords(_num: Int): String {
    if (_num == 0) {
        return "Zero"
    }
    var num = _num
    var sb = StringBuilder()
    var index = 0
    while (num > 0) {
        if (num % 1000 != 0) {
            var tmp = StringBuilder()
            helper(num % 1000, tmp)
            sb.insert(0, tmp.append(THOUSAND[index]).append(" "))
        }
        num /= 1000
        index++
    }
    return sb.toString().trim()
}

fun helper(num:Int, sb:StringBuilder) {
    if (num == 0) return
    when {
        num < 20 -> {
            sb.append(LESS_THAN_TWENTY[num]).append(" ")
        }
        num < 100 -> {
            sb.append(HUNDRED[num / 10]).append(" ")
            helper(num % 10, sb)
        }
        else -> {
            sb.append(LESS_THAN_TWENTY[num / 100]).append(" Hundred ")
            helper(num % 100, sb)
        }
    }
}

fun main() {
    var ret = numberToWords(50868)
    println(ret)
}