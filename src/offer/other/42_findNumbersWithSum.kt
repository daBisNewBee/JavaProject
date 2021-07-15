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

/**
 *
 * "两数之和S"
 *
 *给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

 注意和上题的区别：
 1. 数组无序
 2. 只有一组解

 用map 空间换时间，否则要遍历，时间o(n2)
 时间、空间都是o(n)
 *
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    if(nums.isEmpty()) return IntArray(0)
    var map = HashMap<Int, Int>(2)
    for(i in nums.indices) {
        if(map.containsKey((target-nums[i]))){
            return intArrayOf(map[target-nums[i]]!!, i)
        } else {
            map[nums[i]] = i
        }
    }
    return IntArray(0)
}

fun main(){
    var data = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    println(findNumbersWithSum(data,10))
}