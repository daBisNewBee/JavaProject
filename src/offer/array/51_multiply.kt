package offer.array


/*
*
* 51、构建乘积数组
*
* 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
* 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
* （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
* 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
*
* B[i] = A[0]*A[1]*A[2]*···*A[i-1]*A[i+1]*···*A[n-1]
*
* 拆解为：
*
* C[i] = A[0]*A[1]*A[2]*···*A[i-1]
*
* D[i] = A[i+1]*···*A[n-1]
*
* 即：
* B[i] = C[i] * D[i]
*
* C[0]=1,C[i]=C[i-1]*A[i-1]
*
* D[len-1]=1,D[i]=D[i+1]*A[i+1]
*
* 两个循环分别求得 C[i] 、 D[i]
*
* 时间：O(n)
* */
fun multiply(A: IntArray):IntArray {
    if (A.isEmpty()) return A
    var len = A.size
    var B = IntArray(len)
    B[0] = 1
    for (i in 1 until len) {
        B[i] = B[i - 1] * A[i - 1]
    }

    var tmp = 1
    for (i in len-2 downTo 0) {
        tmp *= A[i + 1]
        B[i] *= tmp
    }
    return B
}

// 比较巧妙！就是慢了点:o(n2)
fun multiply2(A: IntArray):IntArray {
    var B = IntArray(A.size)
    for (i in A.indices) {
        var tmp = A[i]
        A[i] = 1
        B[i] = 1
        for (j in A.indices) {
            B[i] *= A[j]
        }
        A[i] = tmp
    }
    return B
}

fun main() {
    var data = intArrayOf(1,2,3,4,5)
    var ret = multiply(data)
    for (i in ret) {
        print("$i,")
    }
    println()
    ret = multiply2(data)
    for (i in ret) {
        print("$i,")
    }
}