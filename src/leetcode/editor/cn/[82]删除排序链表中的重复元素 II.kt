import offer.online.ListNode

//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
// Related Topics 链表 双指针 
// 👍 804 👎 0


class P_82_RemoveDuplicatesFromSortedListIi {
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
    fun deleteDuplicates(head: ListNode?): ListNode? {
        head ?: return null

        var dummy = ListNode(-1)
        dummy.next = head

        // 其实已经是重复节点的前驱节点，所以不用再定义"pre"了
        var cur = dummy

        // dummy,1,2,3,3,4,4,5
        while (cur.next != null && cur.next!!.next != null) {
            if (cur.next!!.`val` == cur.next!!.next!!.`val`) {
                var x = cur.next!!.`val`
                while (cur.next != null && cur.next!!.`val` == x) {
                    cur.next = cur.next!!.next
                }
            } else {
                cur = cur.next!!
            }
        }

        return dummy.next
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}