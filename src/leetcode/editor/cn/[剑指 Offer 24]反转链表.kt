import offer.online.ListNode

//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
// 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/ 
// Related Topics 递归 链表 
// 👍 284 👎 0


class P_Offer_24_FanZhuanLianBiaoLcof {
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

    // 递归写法：在"归" 的时候，修改head.next指针，"递"的时候拆解子问题
    fun reverseList(head: ListNode?):ListNode? {
        // 递归终止条件
        if (head?.next == null) return head
        var ans = reverseList(head.next)
        head.next?.next = head
        head.next = null
        return ans // 返回最后一个节点
    }

    // 迭代写法
    fun reverseList2(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }
        var pre:ListNode ?= null
        var cur = head
        var next:ListNode ?= null
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