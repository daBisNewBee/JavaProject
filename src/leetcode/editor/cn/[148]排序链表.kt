import offer.online.ListNode

//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// Related Topics 链表 双指针 分治 排序 归并排序 
// 👍 1434 👎 0


class P_148_SortList {
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {

    fun merge(array: IntArray, left: Int, mid:Int, right: Int, tmp: IntArray) {
        var i = left
        var j = mid+1
        var t = 0

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                tmp[t++] = array[i++]
            } else {
                tmp[t++] = array[j++]
            }
        }

        while (i <= mid) {
            tmp[t++] = array[i++]
        }

        while (j <= right) {
            tmp[t++] = array[j++]
        }

        t = 0
        i = left

        while (i <= right) {
            array[i++] = tmp[t++]
        }
    }

    fun mergeSort(array: IntArray, left:Int, right:Int, tmp:IntArray) {
        if (left < right) {
            var mid = left + (right-left) / 2
            mergeSort(array, left, mid, tmp)
            mergeSort(array, mid+1, right, tmp)
            merge(array, left, mid, right, tmp)
        }
    }

    fun qucikSort(array: IntArray, start:Int, end:Int) {

        if (start >= end) {
            return
        }
        var i = start
        var j = end
        var tmp = array[i]
        while (i < j) {
            while (i < j && array[j] >= tmp) {
                j--
            }
            array[i] = array[j]
            while (i < j && array[i] <= tmp) {
                i++
            }
            array[j] = array[i]
        }
        array[i] = tmp
        var mid = i
        qucikSort(array, start, mid-1)
        qucikSort(array, mid+1, end)
    }

    fun sortList(head: ListNode?): ListNode? {
        if (head == null) return null

        var list = mutableListOf<Int>()
        var cur = head
        while (cur != null) {
            list.add(cur.`val`)
            cur = cur.next
        }
        var array = list.toIntArray()
//        qucikSort(array, 0, array.size-1)
        var tmp = IntArray(array.size)
        mergeSort(array, 0, array.size-1, tmp)

        var ans :ListNode ?= null
        for (i in array) {
            var new = ListNode(i)
            if (cur != null) {
                cur.next = new
            }
            if (ans == null) {
                ans = new
            }
            cur = new
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {

}