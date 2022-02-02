import offer.online.ListNode

//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//
// 不允许修改 链表。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
//
// 
//
// 进阶：你是否可以使用 O(1) 空间解决此题？ 
// Related Topics 哈希表 链表 双指针 
// 👍 1352 👎 0


class P_142_LinkedListCycleIi {
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

    /**
     *
     * "为什么一圈内能追上？"
     *
     * 之所以慢指针跑一圈的过程中，快指针一定能追上是因为：（极限思想） 假设他们都是从入环点开始跑的，
     * 那么当慢指针刚好跑完一圈时，快指针刚好跑完两圈，也是在一圈的末尾追上慢指针。
     * 而现实情况往往是在慢指针到入环处时快指针已经入环并走了了，所以实际情况一定会在第一圈之内相遇。
     *
     */
    fun detectCycle(head: ListNode?): ListNode? {
        head ?: return null

        var low = head
        var fast = head
        var p1 :ListNode? = null

        while (fast != null) {
            low = low?.next
            fast = fast.next?.next
            if (low != null && low == fast) {
                p1 = low // 相遇点
                break
            }
        }

        /*
        var count = 0

        do {
            low = low?.next
            fast = fast?.next?.next
            count++
        } while (low != fast)

        println("环中节点个数: $count")
        */

        low = head

        while (low != p1) {
            low = low?.next
            p1 = p1?.next
        }

        return low
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}