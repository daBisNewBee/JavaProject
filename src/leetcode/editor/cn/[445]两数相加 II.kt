import offer.online.ListNode
import java.util.*

//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。 
// Related Topics 栈 链表 数学 
// 👍 410 👎 0


class P_445_AddTwoNumbersIi {
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
    // 思路：栈！自己写出来的，我好牛逼
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var stack1 = Stack<Int>()
        var stack2 = Stack<Int>()
        var cur = l1
        while (cur != null) {
            stack1.push(cur.`val`)
            cur = cur.next
        }
        cur = l2
        while (cur != null) {
            stack2.push(cur.`val`)
            cur = cur.next
        }
        var carry = 0
        var ans :ListNode? = null
        while (stack1.isNotEmpty()
                || stack2.isNotEmpty()
                || carry > 0) {
            var num1 = if (stack1.isNotEmpty()) stack1.pop() else 0
            var num2 = if (stack2.isNotEmpty()) stack2.pop() else 0
            var sum = num1 + num2 + carry
            carry = sum / 10
            sum %= 10
            // 注意这里直接建立关系，不要再额外遍历
            var newNode = ListNode(sum)
            newNode.next = ans
            ans = newNode
        }
        return ans
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}