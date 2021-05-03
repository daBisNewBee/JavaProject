package offer.tree

import offer.TreeNode
import java.util.*
import kotlin.collections.ArrayList

/*
*
* 59、按之字形顺序打印二叉树
*
* 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
* 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
*
*        0
*     1    2
*    3 4  5 6
*
* 方法一：
* 两个栈！调试验证可以推断一个栈、或者队列不够用！
*
* 方法二：
* BFS！fixme:注意倒序和正序的相关性，倒序可以从正序演变而来，因此求倒序时，仍可以套用正序的方法，比如BFS！
*
* */

// 关键点在于要用两个栈！
fun print(root:TreeNode?) {
    if (root == null) {
        return
    }
    var level = 0 // 从第0层开始
    var stackQi = Stack<TreeNode>()
    var stackOu = Stack<TreeNode>()
    stackOu.add(root)

    while (level % 2 == 0 && stackOu.isNotEmpty()
            || level % 2 != 0 && stackQi.isNotEmpty()) {
        if (stackOu.isNotEmpty()) {
            var curLevelSize = stackOu.size
            while (curLevelSize-- > 0) {
                var cur = stackOu.pop()
                print("${cur.value} ")
                if (cur.left != null) {
                    stackQi.push(cur.left)
                }
                if (cur.right != null) {
                    stackQi.push(cur.right)
                }
            }
            level++
            continue
        }
        if (stackQi.isNotEmpty()) {
            var curLevelSize = stackQi.size
            while (curLevelSize-- > 0) {
                var cur = stackQi.pop()
                print("${cur.value} ")
                if (cur.right != null) {
                    stackOu.push(cur.right)
                }
                if (cur.left != null) {
                    stackOu.push(cur.left)
                }
            }
            level++
            continue
        }
    }
}

fun print2(root:TreeNode?) {
    if (root == null) {
        return
    }
    var queue = LinkedList<TreeNode>()
    queue.offer(root)
    var isReverse = false
    var resultList:MutableList<MutableList<Int>> = ArrayList()
    while (queue.isNotEmpty()) {
        var curLevelSize = queue.size
        var array :MutableList<Int> = ArrayList()
        while (curLevelSize-- > 0) {
            var cur: TreeNode? = queue.pop() ?: continue
            if (!isReverse) {
                array.add(cur?.value!!)
            } else {
                // 注意：倒序 = 正序的反转 = add(0, node) 正序按照0位置插入
                array.add(0, cur?.value!!)
            }
            if (cur.left != null) {
                queue.add(cur.left!!)
            }
            if (cur.right != null) {
                queue.add(cur.right!!)
            }
        }
        resultList.add(array)
        isReverse = !isReverse
    }
    resultList.forEach { itt ->
        itt.forEach{
            print("$it ")
        }
    }
}

fun main(args:Array<String>) {

    var data = listOf(0,1,2,3,4,5,6)

    var root = offer.createTree(data)
    println("两个栈方法：")
    print(root[0])

    println("\nBFS方法：")
    print2(root[0])
}