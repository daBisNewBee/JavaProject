import java.util.*

//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例： 
//
// 
//
// 
//
// 
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
//
// 
//
// 
//
// 
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 
//
// 
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 
// 👍 356 👎 0

/**
 *
 * 1. 定义前驱节点 pre、头节点 head
 *
 * 2. pre == null 时
 *      head = cur
 *    pre != null
 *      pre.right = cur
 *      cur.left = pre
 *
 * 3. pre = cur
 *
 *
 */
class P_Offer_36_ErChaSouSuoShuYuShuangXiangLianBiaoLcof {
    //There is no code of Kotlin type for this problem
    class Solution {

        var pre :Node ?= null
        var head: Node ?= null

        // 递归
        fun dfs(cur:Node?) {
            if (cur == null) return
            dfs(cur.left)

            // visit 当前节点
            if (pre == null) {
                head = cur
            } else {
                pre?.right = cur
            }
            cur.left = pre
            pre = cur


            dfs(cur.right)
        }

        // 非递归
        fun dfs_Stack(root: Node?):Node? {
            if (root == null) return null

            var stack = Stack<Node>()
            var cur :Node ?= root
            while (stack.isNotEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur)
                    cur = cur.left
                }
                cur = stack.pop()

                // visit 当前节点
                println(cur)
                if (pre == null) {
                    head = cur
                } else {
                    pre?.right = cur
                }
                cur.left = pre
                pre = cur

                cur = cur.right
            }
            head?.left = pre
            pre?.right = head
            return head
        }

        fun treeToDoublyList(root: Node?): Node? {
            if(root == null) return null
            dfs(root)
            head?.left = pre
            pre?.right = head
            return head
        }
    }
}