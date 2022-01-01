package leetcode.editor.cn

import offer.TreeNode
import java.util.*


/**
 * 递归序
 *
 * "每一次访问某个节点，实际都访问了三次"
 *          1
 *        2  3
 *       4 5 6 7
 * 递归序为：
 * 1 2 4 4 4 2 5 5 5 2 1 3 6 6 6 3 7 7 7 3 1
 *
 * 因此，可得：
 * 先序：第一次访问节点的时候，打印：1245367
 * 中序：第二次访问节点的时候，打印：4251637
 * 后续：第三次访问节点的时候，打印：4526731
 *
 * 先、中、后序，都满足这个"递归序"标准，
 * 利用递归行为，三次能够到达自己，
 * 但是选择打印的时机不同，可以实现
 *
 *
 *
  */
private fun f(node:TreeNode?) {
    // 1 start
    if (node == null) {
        return
    }
    // 1 end
    f(node.left)
    // 2 start
    // 2 end
    f(node.right)
    // 3 start
    // 3 end
}

/**
 * 中序，非递归实现：
 *
 * 左中(右)
 *     左中(右)
 *          ....
 *
 * 为什么"左边界先入栈，再遍历右节点的左边界，可以做到中序遍历?"
 * 整棵树都按照"左边界"分解掉了，但是右树的"左边界"后搞，所以这个流程能做到中序遍历
 *
 */

private fun inOrder(head: TreeNode?) {
    var cur = head
    var stack = Stack<TreeNode>()
    while (stack.isNotEmpty() || cur != null) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }
        if (stack.isNotEmpty()) {
            cur = stack.pop()
            println(cur.value)
            cur = cur.right
        }
    }
}

/**
 *
 * 后序，非递归
 *
 * 求"左、右、中"，其实是求"中、右、左"的逆序
 *
 * 而"中、右、左"可以从"中、左、右"，即先序得来，只是打印的时候不打印，放到一个辅助栈里去
 *
 *
 */
private fun postOrder(head: TreeNode?) {
    var cur = head
    var stack = Stack<TreeNode>()
    stack.push(cur)
    var tmp = Stack<TreeNode>()
    while (stack.isNotEmpty()) {
        cur = stack.pop()
        tmp.push(cur)
        if (cur.left != null) {
            stack.push(cur.left)
        }
        if (cur.right != null) {
            stack.push(cur.right)
        }
    }
    while (tmp.isNotEmpty()) {
        println(tmp.pop().value)
    }
}

fun main() {
    var data = listOf(1,2,3,4,5,6,7)
    var arrays = offer.createTree(data)
    postOrder(arrays[0])
    println()
    inOrder(arrays[0])
}