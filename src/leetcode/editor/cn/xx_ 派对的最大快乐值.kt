package leetcode.editor.cn


/**
 *
 * 整个公司的人员结构可以看作是一棵标准的多叉树。树的头节点是公司唯一的老板，除老板外，
 * 每个员工都有唯一的直接上级，叶节点是没有任何下属的基层员工，除基层员工外，
 * 每个员工都有一个或多个直接下级，另外每个员工都有一个快乐值。
 *
    这个公司现在要办 party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下的原则：
    1.如果某个员工来了，那么这个员工的所有直接下级都不能来。
    2.派对的整体快乐值是所有到场员工快乐值的累加。
    3.你的目标是让派对的整体快乐值尽量大。
    给定一棵多叉树，请输出派对的最大快乐值。
 *
 */

// yes: 头结点来的时候，整棵树最大值；no：头结点不来的时候，整棵树最大值
private class Info(var yes:Int, var no:Int)

private class Employee(var happy:Int, var nexts: List<Employee>)

private fun process(employee: Employee):Info {
    if (employee.nexts.isNullOrEmpty()) {
        return Info(employee.happy, 0)
    }
    var yes = employee.happy
    var no = 0

    employee.nexts.takeIf { it.isNullOrEmpty().not() }?.forEach {
        var nextInfo = process(it)
        no += Math.max(nextInfo.no, nextInfo.yes)
        yes += nextInfo.no
    }

    return Info(yes, no)
}