import offer.online.ListNode

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。） 
// Related Topics 递归 链表 
// 👍 1000 👎 0


class P_24_SwapNodesInPairs {
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

    // 递归，比较简洁
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        var newHead = head.next
        head.next = swapPairs(newHead?.next)
        newHead?.next = head
        return newHead
    }

    // 自己写的，虽然正确，其实有点复杂，联系"[25]K 个一组翻转链表"
    fun swapPairs2(_head: ListNode?): ListNode? {
        if (_head?.next == null) return _head

        var next:ListNode ?= null

        var hair = ListNode(0)

        hair.next = _head

        var head:ListNode ?= _head
        var tail:ListNode ?= null

        var pre:ListNode ?= hair

        while (head != null) {
            tail = head.next
            if (tail == null) {
                return hair.next
            }
            next = tail.next
            tail.next = head

            pre?.next = tail
            head.next = next

            pre = head
            head = next
        }
        return hair.next
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var data = intArrayOf(1,2,3)
//    var data = intArrayOf(1,2,3,4)
    var head = ListNode(0)
    var cur:ListNode? = head
    data.forEach {
        var node = ListNode(it)
        cur?.next = node
        cur = node
    }
    var ret = P_24_SwapNodesInPairs.Solution().swapPairs(head.next)
    cur = ret
    while (cur != null) {
        println(cur!!.`val`)
        cur = cur!!.next
    }
}