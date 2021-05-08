package offer.array

/*
* 13、调整数组顺序使奇数位于偶数前面
*
* 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
* 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
*
* 注意点：
* 1. 要申请两个临时数组，不是一个
* 2. 不能对一个数组即遍历、又增删。因为修改会改变数组内容，使得后面的遍历无效
* */

fun reOrder(list: MutableList<Int>) {
    var oddList:MutableList<Int> = ArrayList()
    var evenList:MutableList<Int> = ArrayList()

    var index = 0
    while (index < list.size) {
        if (list[index] % 2 != 0) {
            oddList.add(list[index])
        } else {
            evenList.add(list[index])
        }
        index++
    }
    list.clear()
    list.addAll(oddList)
    list.addAll(evenList)
}

fun main(args:Array<String>) {
    var data = mutableListOf(1,2,3,4,5,6,7,8,9,10)
    reOrder(data)
    data.forEach { print(it.toString()+" ") }
    // 结果：1 3 5 7 9 2 4 6 8 10

    println()

    data = mutableListOf(2,4,6,5,7)
    reOrder(data)
    data.forEach { print(it.toString()+" ") }

}