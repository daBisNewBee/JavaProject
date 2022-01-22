import offer.online.ListNode

//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 105] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 
// 👍 1238 👎 0


class P_234_PalindromeLinkedList {
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
    fun isPalindrome(head: ListNode?): Boolean {
        head ?: return false

        var dummy = ListNode(-1)
        dummy.next = head

        var low:ListNode? = dummy
        var fast:ListNode? = dummy

        while (fast?.next != null) {
            low = low?.next
            fast = fast.next?.next
        }

        var tail = reverseList(low?.next)

        var p1 = dummy.next
        var p2 = tail

        while (p1 != null && p2 != null) {
            if (p1.`val` != p2.`val`) {
                return false
            }
            p1 = p1.next
            p2 = p2.next
        }

        // 还原链表:不做要求，有了更好！毕竟这样不会修改输入参数
        low?.next = reverseList(tail)
        return true
    }

    fun reverseList(head: ListNode?):ListNode? {
        var pre:ListNode? = null
        var cur = head
        var next:ListNode? = null

        while (cur != null) {
            next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        }
        return pre
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}