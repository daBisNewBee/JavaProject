import offer.online.ListNode
import java.util.*

//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 栈 递归 链表 双指针 
// 👍 201 👎 0


class P_Offer_06_CongWeiDaoTouDaYinLianBiaoLcof {
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
    fun reversePrint(head: ListNode?): IntArray {
        var stack = Stack<Int>()
        var cur = head
        while (cur != null) {
            stack.push(cur.`val`)
            cur = cur.next
        }
        var array = IntArray(stack.size)
        for (i in 0 until stack.size) {
            array[i] = stack.pop()
        }
        return array
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}