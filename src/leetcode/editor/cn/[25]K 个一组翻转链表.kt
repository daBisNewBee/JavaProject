import offer.online.ListNode

//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 
// 👍 1207 👎 0


class P_25_ReverseNodesInKGroup {
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
    fun reverseKGroup(_head: ListNode?, k: Int): ListNode? {
        if (_head == null) return null
        var head = _head
        // hari的定义是关键！可以少很多特判
        var hair = ListNode(0)
        hair.next = head
        var pre:ListNode ?= hair

        while (head != null) {
            var tail = pre
            // 查看剩余部分长度是否大于等于 k
            repeat(k) {
                tail = tail?.next
                if (tail == null) {
                    // 注意这里直接返回
                    return hair.next
                }
            }
            var next = tail?.next
            var ret = reverseList(head, tail)
            // 重新建立联系
            pre?.next = ret[0]
            ret[1].next = next
            pre = ret[1]
            head = next
        }
        return hair.next
    }

    fun reverseList(head: ListNode?, tail:ListNode?):Array<ListNode> {
        var pre:ListNode ?= null
        var cur = head
        var next:ListNode ?= null
        // 这里的结束条件
        while (pre != tail) {
            next = cur?.next
            cur?.next = pre
            pre = cur
            cur = next
        }
        return arrayOf(tail!!, head!!)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var head = ListNode(0)
    var cur:ListNode? = head
    for (i in 1 until 6) {
        var node = ListNode(i)
        cur?.next = node
        cur = cur?.next
    }

    cur = P_25_ReverseNodesInKGroup.Solution().reverseKGroup(head.next, 2)
    while (cur != null) {
        print(cur.`val`)
        cur = cur.next
    }
}