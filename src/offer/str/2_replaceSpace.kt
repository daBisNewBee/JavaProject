package offer.str


/*
* 2、替换空格
*
* 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
* 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
*
* */

//第一种情况：创建新的字符串实现.
fun replaceSpace(sb:String):String {
    var newSb = StringBuffer()
    for (index in sb.indices) {
        if (sb[index] == ' ') {
            newSb.append("%20")
        } else {
            newSb.append(sb[index])
        }
    }
    return newSb.toString()
}


//第二种情况：原地替换，O(n)的解法
fun replaceSpace2(sb:StringBuffer):String? {
    if (sb.isEmpty()) return null

    var spaceCount = 0

    for (i in sb.indices) {
        if (sb[i] == ' ') spaceCount++
    }

    var newLen = sb.length + (spaceCount shl 1)

    var oldIndex = sb.length - 1

    sb.setLength(newLen)

    var newIndex = newLen - 1

    while (newIndex > oldIndex && oldIndex >= 0) {
        if (sb[oldIndex] == ' ') {
            sb.setCharAt(newIndex--, '0')
            sb.setCharAt(newIndex--, '2')
            sb.setCharAt(newIndex--, '%')
        } else {
            sb.setCharAt(newIndex--, sb[oldIndex])
        }
        oldIndex--
    }
    return sb.toString()
}

fun main() {
    var data = " I am a new bee man."
    var ret:String? = replaceSpace(data)
    println("替换后: $ret")

    ret = replaceSpace2(StringBuffer(data))
    println("替换后: $ret")

    data = " I am a new bee man."

    // 方法3： split
    var elements = data.split(' ')
    var sb = StringBuffer()

    for (i in elements.indices) {
        sb.append(elements[i])
        if (i != elements.size - 1)
            sb.append("%20")
    }

    println("替换后: $sb")
}