import offer.online.ListNode

//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
//
// 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 1->3->5->2->4->NULL
// 
//
// 示例 2: 
//
// 输入: 2->1->3->5->6->4->7->NULL 
//输出: 2->3->6->7->1->5->4->NULL 
//
// 说明: 
//
// 
// 应当保持奇数节点和偶数节点的相对顺序。 
// 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。 
// 
// Related Topics 链表 
// 👍 523 👎 0


class P_328_OddEvenLinkedList {
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

    // 这个看起来简单点
    fun oddEvenList(head: ListNode?): ListNode? {
        head ?: return null
        var oddHead = head
        var evenHead = head.next

        var odd = oddHead
        var even = evenHead
        while (even?.next != null) {
            odd?.next =even.next
            odd = odd?.next
            even.next = odd?.next
            even = even.next
        }
        odd?.next = evenHead
        return head
    }

    // 自己写的，一遍过
    fun oddEvenList_mine(head: ListNode?): ListNode? {
        head ?: return null

        var jiHead = head
        var ouHead = head.next

        var preJi :ListNode?= null
        var preOu :ListNode?= null

        var cur = head
        while (cur != null) {

            preOu?.next = cur.next
            preJi?.next = cur

            var nextJi = cur.next?.next

            preOu = cur.next

            cur.next = nextJi

            preJi = cur

            cur = nextJi
        }
        preJi?.next = ouHead
        return jiHead
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {

    var arrays = intArrayOf(1,2,3,4,5)
    var cur :ListNode ?
    var head :ListNode ?= ListNode(arrays[0])
    cur = head
    for (i in 1 until arrays.size) {
        var newNode = ListNode(arrays[i])
        cur?.next = newNode
        cur = cur?.next
    }

    cur = P_328_OddEvenLinkedList.Solution().oddEvenList(head)
    while (cur != null) {
        println(cur.`val`)
        cur = cur.next
    }
}