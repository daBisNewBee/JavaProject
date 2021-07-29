import offer.online.ListNode

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
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
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 
// 👍 1862 👎 0


class P_206_ReverseLinkedList {
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
    // 时间o(n)，空间o(1)
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return null

        var pre :ListNode? = null

        var cur = head // 注意：head赋值到cur，pre、next只是辅助指针！

        var next :ListNode?= null

        while (cur != null) {

            // 备份后一个节点的引用
            next = cur.next

            // 核心：更改指向关系
            cur.next = pre

            // 事先存储前一个节点的引用，方便后面赋值
            pre = cur

            // 技巧：从后往前写
            cur = next
        }
        return pre
    }

    /**
     * 简洁写法，但是空间o(n):
     * public ListNode reverseList(ListNode head) {
            ListNode ans = null;
            for (ListNode x = head; x != null; x = x.next) {
                ans = new ListNode(x.val,ans);
            }
            return ans;
        }
     *
     * 用栈反转的空间也是要o(n)
     *
     */
}
//leetcode submit region end(Prohibit modification and deletion)

}