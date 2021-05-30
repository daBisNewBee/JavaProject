package offer.str

/*
*
* 34、第一个只出现一次的字符
*
* 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
* 并返回它的位置, 如果没有则返回 -1（需要区分大小写）。
* */

fun firstNotRepeatingChar(s:String):Int {
    if (s.isEmpty()) return -1
    var map = LinkedHashMap<Char, Int>()
    for (i in s.indices) {
        map[s[i]] = map.getOrDefault(s[i], 0) + 1
    }
    map.forEach { (t, u) -> if (u == 1) {
        return@firstNotRepeatingChar s.indexOfFirst{it == t}
    }}
    return -1
}

//方法二：数组代替哈希表
fun firstNotRepeatingChar2(s:String):Int {
    if (s.isEmpty()) return -1
    // A-Z对应的ASCII码为65-90，a-z对应的ASCII码值为97-122
    var array = IntArray(122-65+1) // 注意这里的容量不能小于该值
    for (i in s.indices) {
        array[s[i] -'A']++
    }
    for (i in s.indices) {
        if (array[s[i] -'A'] == 1) {
            return i
        }
    }
    return -1
}

//方法三：模式匹配,从前（indexOf）和从后（lastIndexOf）匹配每一个字符,相等即为唯一
fun firstNotRepeatingChar3(s:String):Int {
    if (s.isEmpty()) return -1
    for (i in s.indices) {
        var firstIndex = s.indexOfFirst { it == s[i] }
        var lastIndex = s.lastIndexOf(s[i])
        if (firstIndex == lastIndex)
            return i
    }
    return -1
}

fun main() {
    var data = "abccffgeba"
    var ret = firstNotRepeatingChar(data)
    println(ret)
    ret = firstNotRepeatingChar2(data)
    println(ret)
    ret = firstNotRepeatingChar3(data)
    println(ret)
}