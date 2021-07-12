package offer.tree

/*
* 23、二叉搜索树的后序遍历序
*
*
* 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
* 如果是则返回true,否则返回false。
* 假设输入的数组的任意两个数字都互不相同。（ps：我们约定空树不是二叉搜素树）
*
* 思路：
* 1. 后序遍历：最后一个结点一定是根节点
* 2. 找到左、右子树：分别与根节点比较大小
* 3. 递归比较左右子树
*
* fixme:要知道"二叉搜索树"是什么！不要一脸懵逼！
*
*
* "二叉搜索树/二叉查找树/二叉排序树"的几个特性：
* 1. 左小右大。
*       左子树上所有结点的值均不大于它的根结点的值；
*       右子树上所有结点的值均不小于它的根结点的值；
* 2. 任意结点的左、右子树也分别为二叉搜索树。
* 3. BST:Binary Search Tree
*
*
* TODO: 方法二：上限约束法
*
* */

// 方法一：递归法， 时间复杂度为nlogn
fun verifySequenceOfBST(data:List<Int>):Boolean {

    if (data.isEmpty()) return false

    if (data.size == 1) return true

    var rootNode = data[data.size-1]

    var leftChildIndex = 0

    while (data[leftChildIndex] <= rootNode) {
        leftChildIndex++
    }

    var rightChildIndex = leftChildIndex
    while (data[rightChildIndex] > rootNode
            && rightChildIndex < data.size - 1) {
        rightChildIndex++
    }
    if (rightChildIndex != data.size - 1) {
        return false
    }
    return verifySequenceOfBST(data.subList(0, leftChildIndex))
            && verifySequenceOfBST(data.subList(leftChildIndex, rightChildIndex))
}

fun main(args:Array<String>) {
    var rawData = listOf(4,8,6,12,16,14,10)
//    var rawData = listOf(3,6,4,8,10,9,7)
    var ret = verifySequenceOfBST(rawData)
    println(ret)
}