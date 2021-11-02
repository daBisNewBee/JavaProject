import offer.other.stack1
import java.util.*

//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 ) 
//
// 
//
// 示例 1： 
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
// 
//
// 示例 2： 
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],[],[5],[2],[],[]]
//输出：[null,-1,null,null,5,2]
// 
//
// 提示： 
//
// 
// 1 <= values <= 10000 
// 最多会对 appendTail、deleteHead 进行 10000 次调用 
// 
// Related Topics 栈 设计 队列 
// 👍 344 👎 0


class P_Offer_09_YongLiangGeZhanShiXianDuiLieLcof {
    //leetcode submit region begin(Prohibit modification and deletion)
class CQueue() {

    private val data = Stack<Int>()
    private val tmp = Stack<Int>()


    fun appendTail(value: Int) {
        data.push(value)
    }

    fun deleteHead(): Int {
        if (tmp.isEmpty()) { // 不要忘了这个判断！用光了再拿！
            while (data.isNotEmpty()) {
                tmp.push(data.pop())
            }
        }
        return if (tmp.isNotEmpty()) tmp.pop() else -1
    }

}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
//leetcode submit region end(Prohibit modification and deletion)

}