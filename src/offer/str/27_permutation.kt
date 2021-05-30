package offer.str

/*
*
* 27、字符串的排列
*
* 即：全排列算法
*
* 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
*
* 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
*
* 递归：
*
* 可以看成两步：
* 第一步：求所有可能出现在第一个位置的字符，即把第一个字符与后面的字符依次交换。
* 第二步：固定一个字符，求后面所有字符的排列。
*
* */

private var res = mutableSetOf<String>()

fun permutation(str:String):Set<String> {
    if (str.isEmpty()) return res
    perm(str.toCharArray(), 0)
    return res
}

/*
*
* strArr:Array<Char>, str:CharArray
*
* decompiled:
*
* Character[] strArr, @NotNull char[] str
*
* fixme: 因此，优先用"CharArray"！,因为iterator的时候不用autoBoxing
*
* */

fun perm(str:CharArray, begin:Int) {
    if (begin + 1 == str.size) {
        // 递归终止条件：当pos+1 == s.length()的时候，终止，
        // 表示对最后一个字符进行固定，也就说明，完成了一次全排列
        var s = String(str)
        res.add(s)
        return
    }
    // for循环和swap的含义：对于“ABC”，
    // 第一次'A' 与 'A'交换，字符串为"ABC", pos为0， 相当于固定'A'
    // 第二次'A' 与 'B'交换，字符串为"BAC", pos为0， 相当于固定'B'
    // 第三次'A' 与 'C'交换，字符串为"CBA", pos为0， 相当于固定'C'
    for (i in begin until  str.size) {
        swap(str, begin, i)
        perm(str, begin+1) // 注意：这里不是"i+1"！
        swap(str, begin, i)
        //  // 回溯的原因：比如第二次交换后是"BAC"，需要回溯到"ABC"
        // 然后进行第三次交换，才能得到"CBA"
    }
}

fun swap(str:CharArray, a:Int, b:Int) {
    var tmp = str[a]
    str[a] = str[b]
    str[b] = tmp
}


fun main(){
    var data = "abc"
    var ret = permutation(data)
    for (s in ret) {
        println(s)
    }

}