import offer.online.ListNode

//输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
//
// 示例1： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4 
//
// 限制： 
//
// 0 <= 链表长度 <= 1000 
//
// 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
// Related Topics 递归 链表 
// 👍 184 👎 0


class P_Offer_25_HeBingLiangGePaiXuDeLianBiaoLcof {
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
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var p1:ListNode ?= l1
        var p2:ListNode ?= l2

        var head = ListNode(-1)
        var cur:ListNode ?= head

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
        cur?.next = p1 ?: p2  // 这一步放在最后处理，不用在循环内
        return head.next
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}