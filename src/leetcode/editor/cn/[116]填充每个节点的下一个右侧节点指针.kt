import java.util.*

//给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
//
// 
//
// 示例： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[1,#,2,3,#,4,5,6,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 
//next 指针连接，'#' 标志着每一层的结束。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量少于 4096 
// -1000 <= node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 512 👎 0

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

class P_116_PopulatingNextRightPointersInEachNode {
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var left: Node? = null
 *     var right: Node? = null
 *     var next: Node? = null
 * }
 */

class Solution {
    // BFS，时间和空间都是o(n)，比较常规
    fun connect(root: Node?): Node? {
        if (root == null) return null
        var queue = LinkedList<Node>()
        queue.offer(root)
        var last:Node? = null
        while (queue.isNotEmpty()) {
            var levelSize = queue.size
            while (levelSize-- > 0) {
                var node = queue.pop()
                if (node.left != null) {
                    queue.offer(node.left)
                }
                if (node.right != null) {
                    queue.offer(node.right)
                }
                if (last != null) {
                    last.next = node
                }
                last = node
            }
            last = null
        }
        return root
    }

    // 还有一种空间o(1)的做法：使用已建立的next指针：
    // 1. 同一个父节点的两个子节点：node.left.next = node.right
    // 2. 不同父亲的子节点之间建立连接：node.right.next = node.next.left
    fun connect2(root: Node?): Node? {
        if (root == null) {
            return root
        }
        if (root.left != null) {
            root.left!!.next = root.right
            root.right!!.next = if (root.next != null) root.next!!.left else null
            connect(root.left)
            connect(root.right)
        }
        return root
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}