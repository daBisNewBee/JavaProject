package offer.tree

import offer.TreeNode
import offer.printTreePreOrder

/*
*
* �����ӽṹ
*
* �������ö�����A��B���ж�B�ǲ���A���ӽṹ��
* ��ps������Լ��������������һ�������ӽṹ��
*
* 1. һ���Ƚ�ϸ�Ĺ��̽��⣺
* https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
*
*
* */

private fun hasSubtree(tree1:TreeNode?, tree2:TreeNode?):Boolean {
    if (tree1 == null || tree2 == null) {
        return false
    }
    var res = false
    // 1. ����A���ҵ�����B�ĸ����ֵһ���Ľ��R
    if (tree1.value == tree2.value) {
        //����ͬ�ĸ��ڵ㣬�жϵڶ���
        res = doseTree1HasTree2(tree1, tree2)
    }
    if (!res) {
        //�����㣬�����������в���
        res = hasSubtree(tree1.left, tree2)
    }
    if (!res) {
        //������Ҳû���ҵ�������������
        res = hasSubtree(tree1.right, tree2)
    }
    return res
}

// 2. �ж���A����RΪ�����������ǲ��ǰ�������Bһ���Ľṹ��
private fun doseTree1HasTree2(tree1:TreeNode?, tree2:TreeNode?):Boolean {
    if (tree2 == null) return true
    if (tree1 == null) return false
    if (tree1.value != tree2.value) return false
    return doseTree1HasTree2(tree1.left, tree2.left) && doseTree1HasTree2(tree1.right, tree2.right)
}

var index:Int = 0


fun createTree(node:TreeNode, n:Int, valueArray:Array<Int>):TreeNode? {
    if (valueArray[n] == 0) {
        return null
    } else {
        node.value = valueArray[n]
    }
    var leftChild = TreeNode()
    node.left = createTree(leftChild, ++index, valueArray)
    var rightChild = TreeNode()
    node.right = createTree(rightChild, ++index, valueArray)
    return node
}

fun main(args:Array<String>) {
    var VALUE1 = listOf(1,2,3,0,4,5,0,0,6,0,0,7,0,0,8,0,9,10,0,0,0)
    var VALUE2 = listOf(2,3,0,0,7,0,0)
    var VALUE3 = listOf(2,3,0,0,8,0,0)

    var root1 = TreeNode()
    createTree(root1, 0, VALUE1.toTypedArray())
    printTreePreOrder(root1)

    println()

    index = 0
    var root2 = TreeNode()
    createTree(root2, 0, VALUE2.toTypedArray())
    printTreePreOrder(root2)

    println()

    var ret = hasSubtree(root1, root2)
    print("hasSubtree:$ret")

    println()

    index = 0
    var root3 = TreeNode()
    createTree(root3, 0, VALUE3.toTypedArray())
    printTreePreOrder(root3)

    println()

    ret = hasSubtree(root1, root3)
    print("hasSubtree:$ret")


}