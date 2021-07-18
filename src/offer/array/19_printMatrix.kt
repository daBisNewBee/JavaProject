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
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    if(matrix.isEmpty() || matrix[0].isEmpty()) return ArrayList()
    var rows = matrix.size
    var columns = matrix[0].size
    var left = 0
    var right = columns-1
    var top = 0
    var bottom = rows-1

    var res = ArrayList<Int>()

    while(left <= right && top <= bottom) {
        for(i in left .. right) {
            res.add(matrix[top][i])
        }
        for(j in top+1 .. bottom) {
            res.add(matrix[j][right])
        }
        // 这个条件容易忘记！！
        if(left < right && top < bottom) {
            for(i in right-1 downTo left) {
                res.add(matrix[bottom][i])
            }
            for(j in bottom-1 downTo top+1) {
                res.add(matrix[j][left])
            }
        } else {
            println("这里不满足条件： left:$left right:$right top:$top bottom:$bottom")
        }

        left++
        right--
        top++
        bottom--
    }
    return res
}

fun main(args:Array<String>) {
//    var array = Array(3) { IntArray(3) }
    var array = Array(4) { IntArray(4) }
    var value = 1
    for (i in array.indices) {
        for (j in array[i].indices) {
            array[i][j] = value++
        }
    }

    var res = spiralOrder(array)

    res.forEach {
        print("$it,")
    }
}