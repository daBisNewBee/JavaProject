package offer.str

import java.util.regex.Pattern


/*
*
* 53、表示数值的字符串
*
* 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
* 例如，字符串"+100", "5e2", "-123"," 3.1416" 和 "-1E-16" 都表示数值。
* 但是 "12e", "1a3.14", "1.2.3", "+-5" 和 "12e+4.3" 都不是。
*
*
*
* 表示数值的字符串遵循共同的模式：
 * A[.[B]][e|EC]或者.B[e|EC]
 *
 * A为数值的整数部分，B为跟在小数点之后的小数部分，C为跟在e或者E之后的指数部分。
 * 其中，A部分可以没有，比如小数.123代表0.123。如果一个数没有整数部分，那么小数部分必须有。
*
* */

/*
    思路：表示数字的字符串遵循模式：A[.[B]][e|EC]或者.B[e|EC]
    A为整数部分，B为小数部分，C为指数部分
    开头可能有正负号
    两种方法：逐位判断、正则表达式
 */
fun isNumeric(str:String):Boolean {

    if (str.isEmpty()) return false

    var hasSign = false

    var hasDecimal = false

    var hasE = false

    for (i in str.toCharArray().indices) {

        if ((str[i] == 'e' || str[i] == 'E')) {

            // E不能是最后一位，后面必须跟指数
            if (i + 1 == str.length) return false

            // E只能出现一次
            if (hasE) return false

            hasE = true

        } else if (str[i] == '.') {

            if (i + 1 == str.length) return false

            // 指数不能有.小数点只能出现一次
            if (hasDecimal || hasE) return false

        } else if (str[i] == '+' || str[i] == '-') {

            // 第一次出现，开头或者e之后
            if (!hasSign && i != 0 && str[i-1] != 'e' && str[i-1] != 'E')
                return false

            // 第二次出现，E之后
            if (hasSign && str[i-1] != 'e' && str[i-1] != 'E')
                return false

            hasSign = true

        } else if (str[i] > '9' || str[i] < '0') {

            return false
        }
    }
    return true
}

/*
*
* 正则表达式
*
* + 代表出现一次或多次
* * 代表出现0次或多次
* ？代表出现0次或者一次
* TODO: 还有些问题
* */
fun isNumeric2(str:String):Boolean {
    val p = "^[+-]?\\d*(?:\\.\\d*)?(?:[eE][+-]?\\d+)?$"
    var pp = "([+-]?\\.[0-9]*)?[0-9]*([eE][+-]?[0-9]+)?"
    // var pp = "[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?"
    return Pattern.matches(pp,str)

}

fun main(){
    var data = "123.45e-2"
    var ret = isNumeric(data)
    println("$ret")

    ret = isNumeric2("-.45e+6")
    println("$ret")
}