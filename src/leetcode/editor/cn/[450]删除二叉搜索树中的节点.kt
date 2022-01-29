
//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：root = [5,3,6,2,4,null,7], key = 3
//输出：[5,4,6,2,null,null,7]
//解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//
// 
//
// 示例 2: 
//
// 
//输入: root = [5,3,6,2,4,null,7], key = 0
//输出: [5,3,6,2,4,null,7]
//解释: 二叉树不包含值为 0 的节点
// 
//
// 示例 3: 
//
// 
//输入: root = [], key = 0
//输出: [] 
//
// 
//
// 提示: 
//
// 
// 节点数的范围 [0, 104]. 
// -105 <= Node.val <= 105 
// 节点值唯一 
// root 是合法的二叉搜索树 
// -105 <= key <= 105 
// 
//
// 
//
// 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。 
// Related Topics 树 二叉搜索树 二叉树 
// 👍 628 👎 0


class P_450_DeleteNodeInABst {
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        root?:return null

        if (key < root.`val`) {
            root.left = deleteNode(root.left, key)
        } else if (key > root.`val`) {
            root.right = deleteNode(root.right, key)
        } else {
            // 当前节点就是要删除的节点
            root.left ?: return root.right // 无左子树
            root.right ?: return root.left // 无右子树
            // 有右子树、也有左子树
            var tmp = root.right
            while (tmp?.left != null) {
                tmp = tmp.left
            }
            // tmp 是右子树的最左节点
            tmp?.left = root.left // 左子树放到右子树的最左子节点
            return root.right
        }
        return root
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var array = createTree(listOf(5,3,6,2,4,5,7))
    P_450_DeleteNodeInABst.Solution().deleteNode(array[0], 3)
}

fun createTree(data:List<Int>):Array<TreeNode> {
    var arrays: Array<TreeNode> = Array(data.size) { i -> TreeNode(data[i]) }

    for (index in 0 until arrays.size/2) {
        arrays[index].left = arrays[2*index+1]
        if (2*index+2 < arrays.size) {
            arrays[index].right = arrays[2*index+2]
        }
    }
    return arrays
}