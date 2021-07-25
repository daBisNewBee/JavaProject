package offer.online

/**
 * 寻找两个正序数组的中位数
 */

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    if(nums1.size == 0 && nums2.size == 0)  return 0.0
    var m = nums1.size
    var n = nums2.size
    var len = m + n
    var start1 = 0
    var start2 = 0
    var left = 0
    var right = 0
    for(i in 0..len/2) {
        left = right
        if(start1 < m && (start2 >= n || nums1[start1] < nums2[start2])) {
            right = nums1[start1++]
        } else {
            right = nums2[start2++]
        }
    }
    if(len % 2 == 0){
        return (left+right).toDouble() / 2
    } else {
        return right.toDouble()
    }
}

fun main() {
    println(findMedianSortedArrays(intArrayOf(), intArrayOf(2,3)))
}