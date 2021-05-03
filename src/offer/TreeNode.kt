package offer

class TreeNode(inValue:Int = 0) {

    // 指向父结点的指针
    var next:TreeNode ?= null

    var left:TreeNode ?= null

    var right:TreeNode ?= null

    var value:Int = inValue
}