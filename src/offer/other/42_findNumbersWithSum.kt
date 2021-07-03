package offer.other

/**
 *
 * 	42. 和为S的两个数字
 *
 * 	给定一个数组，返回两个数字和为sun且乘积最小的两个数字。
 *
 */

// 双指针法：指针i指向数组首， 指针j指向数组尾部, o(n)
fun findNumbersWithSum(array: IntArray, sum:Int):MutableList<Int>{
    var result = ArrayList<Int>()
    var resultMultiply = Int.MAX_VALUE
    var low = 0
    var high = array.size - 1
    while (low < high) {
        var tmp = array[low] + array[high]
        if (tmp == sum) {
            var cur = array[low] * array[high]
            println("找到: low: ${array[low]}, high: ${array[high]}")
            if (cur < resultMultiply) {
                resultMultiply = cur
                result.clear()
                result.add(array[low])
                result.add(array[high])
                // 这里其实可以break了。最外层的肯定最小
                // 公式推导: xy = (c-d)(c+d)/4 = (c2-d2)/4，d越大，xy越小，第一轮遍历的差值d最大
            }
            low++
            high--
        } else if (tmp > sum) {
            high--
        } else {
            low++
        }
    }
    return result
}

fun main(){
    var data = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    println(findNumbersWithSum(data,10))
}