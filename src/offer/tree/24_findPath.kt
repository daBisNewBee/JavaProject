package offer.tree

import offer.TreeNode

/*
*
* 24、二叉树中和为某一值的路径
*
* 输入一颗二叉树的根结点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
* 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
* (注意: 在返回值的list中，数组长度大的数组靠前)
*
* */

var resultList: MutableList<MutableList<Int>> = ArrayList()

fun findPath(node: TreeNode?, target: Int, tmp:MutableList<Int>) {
    if (node == null) return
    tmp.add(node.value)
    if (node.left == null && node.right == null) { // root是叶结点
        if (target == node.value) {
            // 找到了一条路径
            var list : MutableList<Int> = ArrayList() // 新建数组，防止回溯对已找到路径的破坏
            list.addAll(tmp)
            resultList.add(list)
        }
    } else {
        if (node.left != null) {
            findPath(node.left, target-node.value, tmp)
        }
        if (node.right != null) {
            findPath(node.right, target-node.value, tmp)
        }
    }
    // 回溯
    if (tmp.isNotEmpty()) {
        tmp.removeAt(tmp.size - 1)
    }
}

fun main(args:Array<String>) {

    var data = listOf(10,5,12,4,7)

    var array = offer.createTree(data)

    var tmp : MutableList<Int> = ArrayList()

    findPath(array[0],22, tmp)

    resultList.forEach {itt->
        itt.forEach{
            print("$it,")
        }
        println()
    }
    resultList.sortWith(LenghtCompare())

    println()

    resultList.forEach {itt->
        itt.forEach{
            print("$it,")
        }
        println()
    }
}

class LenghtCompare:Comparator<List<Int>> {
    override fun compare(o1: List<Int>?, o2: List<Int>?): Int {
        var size1 = o1?.size ?: 0
        var size2 = o2?.size ?: 0
        return when {
            size1 > size2 -> 1
            size1 == size2 -> 0
            else -> -1
        }
    }
}