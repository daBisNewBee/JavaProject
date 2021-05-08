package offer.array


/*
* 19、顺时针打印矩阵
*
* 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
* 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
* 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
*
* */

// 画一画矩阵各个元素索引，找到画圈规律，终止条件等
fun printMatrix(matrix:Array<Array<Int>>):MutableList<Int> {

    var row = matrix.size

    var col = matrix[0].size

    var res :MutableList<Int> = ArrayList()

    var i = 0
    // 圈数，从外向内,循环结束条件需要注意
    while (col > 2*i && row > 2*i) {
        var endY = col - 1 -i
        var endX = row - 1 -i

        // 左到右
        for (j in i..endY)
            res.add(matrix[i][j])

        // 上到下
        for (j in i+1..endX)
            res.add(matrix[j][endY])

        // 右到左
//        if(endX>i)
            for (j in endY-1 downTo i) // downTo 包括边界
                res.add(matrix[endX][j])

        // 下到上
//        if (endY>i)
            for (j in endX-1 downTo i+1)
                res.add(matrix[j][i])
        i++
    }
    return res
}

fun main(args:Array<String>) {
    var array = Array(4) {Array(4) {0} }
    var value = 1
    for (i in 0 until array.size) {
        for (j in 0 until array[i].size) {
            array[i][j] = value++
        }
    }

    var res = printMatrix(array)

    res.forEach {
        print("$it,")
    }
}