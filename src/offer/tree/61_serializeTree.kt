package offer.tree

import offer.TreeNode
import offer.breadthFirst
import org.bouncycastle.util.Strings

/*
*
* 61、序列化二叉树
*
*
* 请实现两个函数，分别用来序列化和反序列化二叉树。
*
* fixme: 要摸清题目的含义，反序列化...就是重建二叉树，如何重建呢？根据先序、中序，可以推断二叉树。但是这种方法有个问题，不能有重复结点。
* fixme: 为什么重建二叉树，要先序+中序，但是这里只要前序就可以了？序列化时，可以插入"#"表示叶子节点，在发序列化时发挥作用
* 比如，
* 重建二叉树时，先序为：1,2,4,7,3,5,6,8
* 反序列化时，先序为：0,1,3,#,#,4,#,#,2,5,#,#,6,#,#, 包含的信息比前者多！
*
*
* */

// 只根据前序遍历的顺序来进行序列化
fun serialize(node:TreeNode?):String {
    if (node == null) {
        return "#,"
    }
    var res = node.value.toString()
    res += ","
    res += serialize(node.left)
    res += serialize(node.right)
    return res
}

var start = -1

fun deSerialize(arrays:Array<String>):TreeNode? {
    start++
    if (start >= arrays.size || arrays[start] == "#") {
        return null
    }
    var cur = TreeNode()
    cur.value = Integer.parseInt(arrays[start])
    cur.left = deSerialize(arrays)
    cur.right = deSerialize(arrays)
    return cur
}

fun main(args:Array<String>) {

    var data = listOf(0,1,2,3,4,5,6)

    var array = offer.createTree(data)

    var ret = serialize(array[0])

    /*
    * 0,1,3,#,#,4,#,#,2,5,#,#,6,#,#,
    *
    * "先序遍历的数组总是可以分为三部分[ [根] , [左子树的先序序列] , [右子树的先序序列] ]，且每部分的首位元素为该部分子树的根节点"
    *
    * [根 0] [左...] [右...]
    *
    * [1,3,###] = [1] [左] [右]
    * */
    println(ret)

    var deSerArray = Strings.split(ret,',')

    var retNode = deSerialize(deSerArray)

    breadthFirst(retNode!!)

}