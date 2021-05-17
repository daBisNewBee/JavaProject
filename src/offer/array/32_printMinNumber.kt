package offer.array

import kotlin.Comparator

/*
*
* 32、把数组排成最小的数
*
* 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
* 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
*
*
* 本质希望找到一个"数组的排序规则"
*
* 两个数字m和n能拼接成mn和nm，如果mn<nm，那m应该在前；
*
* 如果nm<mn，那么n应该在前。因此，我们得到的排序规则如下：

    若mn>nm，则m大于n
    若mn<nm，则m小于n
    若mn=nm，则m等于n
*
* */

class MyComparator :Comparator<String> {

    override fun compare(m: String, n: String): Int {
        // 敲黑板：转成字符串再比较
        var mn = m + n
        var nm = n + m
        return mn.compareTo(nm)
        //mn小于nm返回-1，等于返回0，大于返回1
    }

}

fun printMinNumber(numbers:List<Int>){
    if (numbers.isEmpty()) return

    val strList = mutableListOf<String>()

    numbers.forEach {
        strList.add(it.toString())
    }

    strList.sortWith(MyComparator())

    var result = ""
    strList.forEach {
        result += it
    }

    println(result)
}

fun main(){
    val data = listOf(3,32,321)
    printMinNumber(data)
}