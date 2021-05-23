package offer.array

/*
*
* 37、数字在排序数组中出现的次数
*
*  统计一个数字在排序数组中出现的次数。例如，输入排序数组{1,2,3,3,3,3,4,5}和数字3，由于数字3在该数组中出现了4次，所以函数返回4。
*
* 思路：
* 常规的遍历，就能求解，但是时间复杂度较高: o(n)
*
* 或者先找到一个k，再前后搜索，但是如果k很多，也会退化成o(n)
*
* 因此，此题本质要求的是复杂度较低的解法，那就只有o(logn)了，即二分查找
*
* 常规的二分查找，找到k就完事了，此题稍有变动，要找到多个k，即在low、high的边界上调整
*
* fixme: 二分查找，可以是找重复数字！不是找到一个就完事了
*
* */

fun getNumberOfK(array: IntArray, k:Int):Int {
    if (array.isEmpty()) return 0
    var first = findFirstKIndex(array, k)
    var last = findLastKIndex(array, k)
    println("first:$first")
    println("last:$last")
    return (last - first + 1)
}

fun findFirstKIndex(array: IntArray, k:Int):Int {
    if (array.isEmpty()) return -1

    var low = 0
    var high = array.size - 1
    while (low <= high) {
        var mid = low + (high - low) / 2
        if (array[mid] > k) {
            high = mid - 1
        } else if (array[mid] < k) {
            low = mid + 1
        } else {
            mid -= 1
            if (mid < low || array[mid] != k) {
                return mid + 1
            } else {
                high = mid
            }
        }
    }
    return -1
}

fun findLastKIndex(array: IntArray, k:Int):Int {
    if (array.isEmpty()) return -1

    var low = 0
    var high = array.size - 1
    while (low <= high) {
        var mid = low + (high - low) / 2
        // fixme: 易错！下面是错误的示例！var mid = (low + high) / 2 + 1
        if (array[mid] > k) {
            high = mid - 1
        } else if (array[mid] < k) {
            low = mid + 1
        } else {
            mid += 1
            if (mid > high || array[mid] != k) {
                return mid - 1
            } else {
                low = mid
            }
        }
    }
    return -1
}

fun main() {
    var data = intArrayOf(1,2,3,3,3,3,4,5)
    var num = getNumberOfK(data, 3)
    println("3 的重复次数是: $num")
}