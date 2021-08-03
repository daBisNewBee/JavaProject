import offer.online.ListNode

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1825 👎 0


class P_21_MergeTwoSortedLists {
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
    // 自己写出来的，我好牛逼
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1

        var head = ListNode(0)
        var cur:ListNode? = head
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
        // 边界判断放到循环外就可以了
        if (p1 != null) {
            cur?.next = p1
        }
        if (p2 != null) {
            cur?.next = p2
        }
        return head.next
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}