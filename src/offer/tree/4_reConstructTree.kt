package offer.tree

import offer.TreeNode
import offer.printTreeInnOrder
import offer.printTreePostOrder
import offer.printTreePreOrder


/*
*
*
* 4、重建二叉树
*
* 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
* 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
* 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回根结点。
*
* 思路：
* 1. 根据前序序列第一个结点确定根结点
  2. 根据根结点在中序序列中的位置分割出左右两个子序列
  3. 对左子树和右子树分别递归使用同样的方法继续分解
*
* 递归实现：
*
*   // 建树的伪代码
    TreeNode* build(1...) {
        if (2...) return nullptr;
        TreeNode *root = new TreeNode(3...);
        root->left = build(4...); // 递归建立左子树
        root->right = build(5...); // 递归建立右子树
        return root;
    }
*
* */

fun reConstructBinaryTree(pre:List<Int>?, inn:List<Int>?): TreeNode? {
    if (pre == null || inn == null) return null
//    return reConstructBinaryTree2(pre, inn, 0, pre.size-1, 0, inn.size-1)
    return reConstructBinaryTree(pre, inn, 0, pre.size-1, 0, inn.size-1)

}

fun reConstructBinaryTree(pre: List<Int>, inn: List<Int>,
                          pre_begin:Int, pre_end:Int, inn_begin:Int, inn_end:Int):TreeNode? {
    //递归结束条件
    if (pre_begin > pre_end || inn_begin > inn_end) return null

    var rootNode = TreeNode(pre[pre_begin])
    var rootValue = pre[pre_begin]

    if (pre_begin == pre_end || inn_begin == inn_end) return rootNode

    // 在中序序列中，找到root，前面的就是左子树，右边的就是右子树
    var rootIndex = inn_begin //root在中序序列中的位置 fixme:容易写成pre_begin！！
    while (rootIndex <= inn_end
            && inn[rootIndex] != rootValue) {
        rootIndex++
    }
    var leftCount = rootIndex - inn_begin // fixme:容易写成pre_begin！！
    // 分别构建左子树、右子树
    rootNode.left = reConstructBinaryTree(pre, inn,
            pre_begin+1, pre_begin+leftCount,inn_begin, rootIndex-1)
    rootNode.right = reConstructBinaryTree(pre, inn,
            pre_begin+leftCount+1, pre_end, rootIndex+1, inn_end)
    return rootNode
}

// fixme:还有问题
fun reConstructBinaryTree2(pre: List<Int>, inn: List<Int>,
                          pre_begin:Int, pre_end:Int, inn_begin:Int, inn_end:Int):TreeNode? {
    if (pre_begin > pre_end || inn_begin > inn_end) return null
    var rootNode = TreeNode(pre[pre_begin])
    for (index in inn_begin..inn_end) {
        if (inn[index] == pre[pre_begin]) {
            rootNode.left = reConstructBinaryTree2(pre, inn,
                    pre_begin+1,pre_begin+index,inn_begin,index-1)
            rootNode.right = reConstructBinaryTree2(pre, inn,
                    pre_begin+index+1,pre_end,index+1,inn_end)
            break
        }
    }
    return rootNode
}



fun main(args:Array<String>) {

    var preData = listOf(1,2,4,7,3,5,6,8)
    var innData = listOf(4,7,2,1,5,3,8,6)

    var root = reConstructBinaryTree(preData, innData)

    printTreePreOrder(root)

    println()

    printTreeInnOrder(root)

    println()

    printTreePostOrder(root)

}