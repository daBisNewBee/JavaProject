package offer.str

/*
*
* 52、正则表达式匹配 TODO: 还是有点懵逼
*
* 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
* 而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
* 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
*
* TODO:使用动态规划求解
*
* */
fun match(str: CharArray, pattern: CharArray): Boolean {
        /*
        思路：比较前两个字符，递归比较
        */
    return match(str, 0, pattern, 0)
}

fun match(str: CharArray, i: Int, pattern: CharArray, j: Int): Boolean {
    if (i == str.size && j == pattern.size) //都为空
        return true
    if (i < str.size && j == pattern.size) //模式串为空
        return false
    //以下j一定是<len
    return if (j + 1 < pattern.size && pattern[j + 1] == '*')
    {   //第二个字符是*
        if (i < str.size && (str[i] == pattern[j] || pattern[j] == '.')) //第一个字符相等，有三种情况
            match(str, i, pattern, j + 2) || match(str, i + 1, pattern, j + 2) || match(str, i + 1, pattern, j)
        else  //第一个字符不等
            match(str, i, pattern, j + 2)
    } else {
        //第二个字符不是*
        if (i < str.size && (pattern[j] == str[i] || pattern[j] == '.'))
            match(str, i + 1, pattern, j + 1)
        else
            false
    }
}


fun main(){
    var patters = listOf("ab*ac*a", "a.a", "aa.a", "ab*a")

    for (s in patters) {
        var ret = match("aaa".toCharArray(), s.toCharArray())
        println("patter: $s ret:$ret")
    }

}