import offer.online.ListNode

//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。 
//
// 
//
// 示例： 
//
// 
//给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5. 
// Related Topics 链表 双指针 
// 👍 293 👎 0


class P_Offer_22_LianBiaoZhongDaoShuDiKgeJieDianLcof {
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
    fun getKthFromEnd(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return null
        }
        var low = head
        var fast = head
        repeat(k-1) { // 这里是(k-1)，不是K
            fast = fast?.next
        }
        if (fast == null) {
            return null
        }
        while (fast != null) {
            if (fast!!.next == null) break
            fast = fast?.next
            low = low?.next
        }
        return low
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}