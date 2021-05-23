package offer.array

/*
* 50、数组中重复的数字
*
*  在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 *
 * 思路：
 * 1. 先排序，再查找。
 *    因此。时间复杂度取决于排序算法，一般为O(nlogn)
 *
 * 2. 哈希法
 *    空间换时间，时间复杂度是O(n)
 *
 * 3. 数组重排(不容易想到)
 *    时间复杂度还是O(n)，但不需要额外空间
 *    缺点：对原数组有改变
 *
* */

fun duplicate(numbers: IntArray?, length: Int): Boolean {
    if (numbers == null || length < 1) return false
    //三种方法：排序后查找、哈希表
    //第三种：数组重排
    for (i in 0 until length) {
        while (numbers[i] != i) {
            //每个元素最多被交换"两次"就可以找到自己的位置，依次复杂度是O（n）
            if (numbers[numbers[i]] == numbers[i]) {
                println("找到重复元素: ${numbers[i]}")
                return true
            } else {
                val temp = numbers[numbers[i]] //交换
                numbers[numbers[i]] = numbers[i] //将numbers[i]放到属于他的位置上
                numbers[i] = temp
            }
        }
    }
    return false
}

fun main(){
    var data = intArrayOf(2,3,1,0,2,5,3)

    duplicate(data, data.size)

}

// fixme: 错误示范！想想为什么不能用这种方式! 因为在交换中，value已经被改变！
fun duplicate2(array: IntArray):Boolean {
    if (array.isEmpty()) return false
    array.forEachIndexed { index, value ->
        println("找到重复元素: $index $value")
        while (index != value) {
            if (array[index] == array[value]) {
                println("找到重复元素: ${array[index]}")
                return true
            } else {
                var tmp = array[index]
                array[index] = array[value]
                array[value] = tmp
            }
        }
    }
    return false
}