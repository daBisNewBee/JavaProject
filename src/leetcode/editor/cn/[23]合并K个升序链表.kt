import offer.online.ListNode

//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1745 👎 0


class P_23_MergeKSortedLists {
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

    // 这个合并两个有序链表。但是这里不能用递归解法了，会"StackOverflowError"
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        l1 ?: return l2
        l2 ?: return l1
        var dummy = ListNode(-1)
        var cur :ListNode? = dummy

        var p1 = l1
        var p2 = l2

        while (p1 != null && p2 != null) {
            if (p1.`val` <= p2.`val`) {
                cur?.next = p1
                p1 = p1.next
            } else {
                cur?.next = p2
                p2 = p2.next
            }
            cur = cur?.next
        }
        cur?.next = p1 ?: p2

        return dummy.next
    }

    // 合并[l,r] 范围内的链表为一个
    fun merge(lists: Array<ListNode?>, l:Int, r:Int):ListNode? {
        if (l == r) {
            return lists[l]
        }
        if (l > r) {
            return null
        }
        var mid = (l+r) shr 1
        // 先递归左边、右边链表，再合并
        var leftList = merge(lists, l, mid)
        var rightList = merge(lists, mid+1, r)
        return mergeTwoLists(leftList, rightList)
    }

    // 联系下"归并排序"！是不是很相似？先递归左边、右边数组，再合并
    fun sort(a: IntArray?, left: Int, right: Int, tmp: IntArray?): Unit {
        if (left < right) { // 递归深度为log2n
            val mid = (left + right) / 2
            sort(a, left, mid, tmp)
            sort(a, mid + 1, right, tmp)
//            merge(a, left, mid, right, tmp)
        }
    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null
        return merge(lists, 0, lists.size-1)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}