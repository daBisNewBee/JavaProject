package offer.array


/*
*
* 6、旋转数组的最小数字
*
* 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
*
*
* 输入 3 4 5 1 2  返回 1
*
* 时间复杂度：二分，所以为O(logN)， 但是如果是[1, 1, 1, 1],会退化到O(n)
*
* */
fun minNumberInRotateArray(array:List<Int>):Int {
    var low = 0
    var high = array.size - 1

    if (array[low] < array[high]) return array[low] // 特殊情况：相当于没有旋转，数组整体有序


    while (low < high) {
        var mid = (low + high)/2
        if (array[low] == array[mid]
                && array[mid] == array[high]) {
            // 10111 这种特殊情况
            return minNum(array)
        }
        if (array[mid] >= array[low]) {
            low = mid
        }
        if (array[mid] <= array[high]) {
            high = mid
        }
        if (low == high-1) {
            return array[high]
        }
    }
    return -1
}

fun minNum(array: List<Int>):Int {
    var min = array[0]
    array.forEach {
        if (it < min) {
            min = it
        }
    }
    return min
}

/**
 *
 * 搜索旋转排序数组
 *
 */
fun search(nums: IntArray, target: Int): Int {
    if(nums.isEmpty()) return -1
    if (nums.size == 1) {
        return if (nums[0] == target) 0 else -1
    }
    var low = 0
    var high = nums.size-1
    while(low <= high) {
        var mid = (low+high) / 2
        if(nums[mid] == target) {
            return mid
        }
        if (nums[0] <= nums[mid]) {
            if (nums[0] <= target && target < nums[mid]) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        } else {
            if (nums[mid] < target && target <= nums[nums.size-1]) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
    }
    return -1
}

fun main(args:Array<String>) {
    var data = listOf(3,4,5,6,7,8,1,2)
    var minNum = minNumberInRotateArray(data)
    println("最小数字为: $minNum")

    /*
    * 首尾指针指向的数字和中间元素三者都相等时，无法判断中间元素位于哪个子数组，无法缩小问题规模。
    * 此时，只能退而求其次，进行顺序查找。
    * */
    data = listOf(1,0,1,1,1)
    minNum = minNumberInRotateArray(data)
    println("最小数字为: $minNum")

    var data1 = intArrayOf(4,5,6,7,0,1,2)
    var ret = search(data1, 0)
    println(ret)

}