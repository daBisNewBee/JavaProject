package offer.array


/*
*
* 1、二维数组中的查找
*
* 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
* 每一列都按照从上到下递增的顺序排序。
* 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
*
* 思路：从左下角（或者右上角）开始查找，因为该行右边大于它，
*      上边小于它，每次比较可以删除某一行或者某一列
*
* 注意：左上和右下不可以，因为无法减小问题规模（行和列都无法删除)
*
* */

fun find(array:Array<Array<Int>>, target:Int):Boolean {
    for (i in array.size-1 downTo 0) {
        // 注意："until 不包括边界，".."包括边界 "
        for (j in array[i].indices) {
            println("当前 ${array[i][j]} ")
            if (target == array[i][j]) {
                return true
            } else if (target < array[i][j]) {
                break
            } else {
                // target > arrays[i][j]
                if (i == array.size-1) {
                    return false
                }
                continue
            }
        }
        println()
    }
    return false
}

// 在一个循环里控制，写法比上面稍微简洁些，复杂度其实一样：
// 时间复杂度：O(m+n) ，其中m为行数，n为列数，最坏情况下，需要遍历m+n次。
fun find2(array:Array<Array<Int>>, target:Int):Boolean {
    val row = array.size //行数
    val col = array[0].size //列数
    var i = row - 1
    var j = 0
    while (i >= 0 && j < col) { //从左下角开始查找
        println("当前 ${array[i][j]} ")
        when {
            array[i][j] === target
                //找到
            -> return true
            array[i][j] > target
                //不可能在该行，跳过该行
            -> i--
            else
                //不可能在该列，跳过该列
            -> {
                if (i == row -1) {
                    // 及时终止
                    return false
                }
                j++
            }
        }
    }
    return false
}


fun main(args:Array<String>) {

    var array = Array(4) {Array(4) {0} }
    var value = 0
    for (i in array.indices) {
        for (j in array[i].indices) {
            array[i][j] = value++
        }
    }


    var targetList = listOf(1,11,16)
    targetList.forEach{
        var ret = find2(array, it)
        println("是否找到$it: $ret")
    }
}