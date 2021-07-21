package offer.str

/*
*
* 49、把字符串转换成整数
*
* 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，
* 但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。
* 数值为0或者字符串不是一个合法的数值则返回0。
*
* 输入一个字符串，包括字母、数字、符号，可以为空。
* 如果是合法的数值表达则返回该数字，否则返回0。
*
* 输入 "+2147483647"
* 输出 2147483647
*
* 这道题难就难在边界的考察！！
*
* */

// 注意各种边界的判断
fun strToInt(_str:String):Int{
    if (_str.isEmpty()) return 0

    var str = _str

    var c = str[0]

    var flag: Int

    if (c == '-') {
        flag = -1
    } else if (c == '+') {
        flag = 1
    } else if (c in '0'..'9') {
        str = "+$str"
        flag = 1
    } else {
        //不是数
        return 0
    }
    var len = str.length
    // 有效整数最大10位十进制
    if (len > 11) {
       return 0
    }
    var res:Long = 0 // 要用long，因为int可能会溢出
    for (i in 1 until len) {
        if (str[i] < '0' || str[i] > '9') {
            return 0
        }
        // fixme: 字母如何转换为整数？比较关键，牢记！
        res = res * 10 + (str[i].toInt() - '0'.toInt()) // '0'的ascII为48
    }
    // 正数、负数有效范围需要分开判断，因为绝对值不一样！
    if (flag ==1 && res <= Int.MAX_VALUE) {
        return res.toInt()
    }
    if (flag == -1 && (res*-1) >= Int.MIN_VALUE) {
        return (res * -1).toInt()
    }
    return 0
}

// 思路比较巧妙，实际效率低，很慢
fun strToInt2(str:String):Int {
    if (str.isEmpty()) return 0
//    if(!str.matches("[+,-]?\\d+")) return 0
    var len = str.length
    var i = len - 1
    var res:Long = 0
    while (i > 0 && str[i] >= '0' && str[i] <= '9') {
        res = Math.pow(10.toDouble(), (10-1-i).toDouble()).toLong() * (str[i] - '0')
    }
    res = if (str[i] == '-') (-1 * res) else res
    if (res >= Int.MIN_VALUE && res <= Int.MAX_VALUE) return res.toInt()
    return 0
}

fun myAtoi(str: String): Int {
    val len = str.length
    // str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组
    val charArray = str.toCharArray()
    // 1、去除前导空格
    var index = 0
    while (index < len && charArray[index] == ' ') {
        index++
    }
    // 2、如果已经遍历完成（针对极端用例 "      "）
    if (index == len) {
        return 0
    }
    // 3、如果出现符号字符，仅第 1 个有效，并记录正负
    var sign = 1
    val firstChar = charArray[index]
    if (firstChar == '+') {
        index++
    } else if (firstChar == '-') {
        index++
        sign = -1
    }
    // 4、将后续出现的数字字符进行转换
    // 不能使用 long 类型，这是题目说的
    var res = 0
    while (index < len) {
        val currChar = charArray[index]
        // 4.1 先判断不合法的情况
        if (currChar > '9' || currChar < '0') {
            break
        }
        // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
        if (res > Int.MAX_VALUE / 10 || res == Int.MAX_VALUE / 10 && currChar - '0' > Int.MAX_VALUE % 10) {
            return Int.MAX_VALUE
        }
        if (res < Int.MIN_VALUE / 10 || res == Int.MIN_VALUE / 10 && currChar - '0' > -(Int.MIN_VALUE % 10)) {
            return Int.MIN_VALUE
        }
        // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
        res = res * 10 + sign * (currChar - '0')
        index++
    }
    return res
}


fun main(){
//    var wrong:Int = 12345678901 // 超过10位，报错了
    var ret = strToInt("-1234567890")
    println(ret)
    ret = strToInt("-1234567890")
    println(ret)
}