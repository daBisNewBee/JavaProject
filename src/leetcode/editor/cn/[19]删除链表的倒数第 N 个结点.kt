import offer.ListNode

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 1765 👎 0


class P_19_RemoveNthNodeFromEndOfList {
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
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // 哨兵节点sentinel 、哑对象dummy，使得链表的每一个节点都有前驱节点了，方便统一处理
        var dummy = ListNode().apply { next = head }
        var low:ListNode? = dummy
        var fast = head
        repeat(n) {
            fast = fast?.next
        }
        while (fast != null) {
            low = low?.next
            fast = fast?.next
        }
        low?.next = low?.next?.next
        return dummy.next
    }

    // 自己写的，虽然过了，但是有点挫
    fun removeNthFromEnd_fuza(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        if (n == 1 && head.next == null) return null
        var ans = head

        var low :ListNode ?= ListNode().apply { next = head }
        var fast = low

        var _n = n
        while (_n > 0 && fast != null) {
            fast = fast.next
            _n--
        }
        if (fast == null) return ans
        while (fast?.next != null) {
            fast = fast.next
            low = low?.next
        }
        if (n == 1) {
            low?.next = null
        } else {
            if (low?.next == head) {
                ans = head.next
            }
            low?.next = low?.next?.next
        }

        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var head = ListNode()
    head.value = 1

    var second = ListNode()
    second.value = 2

    var third = ListNode()
    third.value = 3

    head.next = second
    second.next = third


    var cur:ListNode? = P_19_RemoveNthNodeFromEndOfList.Solution().removeNthFromEnd(head, 3)
    while (cur != null) {
        print(cur.value)
        cur = cur.next
    }
}