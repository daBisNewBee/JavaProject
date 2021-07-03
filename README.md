# JavaProject
一些Java下的基础操作。

## 剑指offer(66题)
#### 链表-8道
|场景\方法|map|stack|递归|双指针|哨兵节点|数学|插入0位置(数组、链表)|
|---|---|---|---|---|---|---|---|
|03、从尾到头打印链表|-|y|y|-|-|-|y|
|14、链表中倒数第k个结点|-|-|-|y|-|-|-|
|15、反转链表|-|y|y|-|-|-|y|
|16、合并两个有序链表|-|-|y|y|y|-|-|
|25、复杂链表的复制|y|-|-|-|-|-|-|
|36、两个链表的第一个公共结点|y|y|-|-|-|y|-|
|55、链表中环的入口结点|y|-|-|y|y|y|-|
|56、删除链表中重复的结点|-|-|-|y|y|-|

记忆点:
1. map:
    - 查询快，时间O(1)，可以用来保存映射关系，降低查询时间复杂度
    - 唯一性，可用来查重
2. stack:
    - 后进先出的特性，可用于倒序的场合
    - 自定义的栈
    - 递归。特殊的栈，系统栈
3. 递归：
    - 有栈的特性，用于倒序场合
    - 大问题可以分解为小问题
4. 双指针
    - 需要跟踪链表的多个位置，进行下一步操作
5. 哨兵节点
    - 头结点也要参与分析
    - 处理后，头结点容易变更(去重等)
6. 数学
    - 需要推导，分析规律，简化问题
7. 插入0位置
    - 通过指定在另一个数据结构中的比较靠前位置，可以更改原有比较靠后位置的访问属性
    - 用于倒序、反转等场合

如何递归：
````
 public ListNode reverseList(参数0) {
    if (终止条件)
        return;

    逻辑处理（可能有，也可能没有，具体问题具体分析）

    //递归调用
    ListNode reverse = reverseList(参数1);

    逻辑处理（可能有，也可能没有，具体问题具体分析）
}
````

如何反转链表：
````
// 这个写起来最简单：三个指针；一个循环；4次赋值；
fun reverseList2(pHead:ListNode):ListNode? {
    var pre:ListNode ?= null
    var cur:ListNode ?= pHead
    var next:ListNode?
    while (cur != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}
````


#### 二叉树12道
- 04_重建二叉树
- 17_树的子结构
- 18_二叉树的镜像
- 22_从上往下打印二叉树 BFS
- 23_二叉搜索树的后序遍历序
- 24_二叉树中和为某一值的路径
- 26_二叉搜索树与双向链表
- 38_二叉树的深度
- 39_平衡二叉树
- 57_二叉树的下一个结点
- 58_对称的二叉树
- 59_按之字形顺序打印二叉树
- 60_把二叉树打印成多行
- 61_序列化二叉树
- 62_二叉搜索树的第k个结点

#### 数组12道
- 1、二维数组中的查找： 
    - 减小问题规模：从右上角或者左下角开始查找
- 6、旋转数组的最小数字
    - **二分查找**：针对有序数组，不用绝对有序，相对有序即可(比如，两个分别有序的子数组)，
    影响的是low、high的定义规则
- 13、调整数组顺序使奇数位于偶数前面
    - 常规做法：两个链表
    - 双指针法：分别指向奇数、偶数，插入排序。缺点：需要移动元素，耗时且复杂
- 19、顺时针打印矩阵
    - 手动画出运行时的角标，发现变化规律，并用i、j表现出来
- 28、数组中出现次数超过一半的数字
    - 候选法：o(n),times记录result出现的次数，相同++，不同--，最后result即为所求
    - 减小问题规模：先排序，后查找。因此o(n)取决于排序，最快为o(nlogn)
    - 空间换时间：map，此处，o(n)的空间复杂度可以降低时间复杂度到o(n)
    - 总结：能够对o(n)调优，并得出不同算法
- 30、连续子数组的最大和
    - 动态规划：关键点，写出状态转移方程？
    - 方程1：sumI = if ((sumI + list[i]) > list[i])
                  sumI + list[i] else sumI
    - 方程2:(更优化)
        - list[i] += if (list[i-1] > 0) list[i-1] else 0          
- 32、把数组排成最小的数
    - 关键点：找到数组的排序规则，定义出来一个"Comparator"
- 35、数组中的逆序对   TODO:有点难 
- 37、数字在排序数组中出现的次数
    - 常规遍历：o(n)
    - 联想到二分查找的关键词：有序、查找、复杂度调优(仅o(logn)，哪个基础的调优？线性遍历o(n))
- 40、数组中只出现一次的数字
    - 熟悉位操作：异或的几个特性
        - 0 xor X = X
        - X xor X = 0
        - 实现两个值的交换，而不必使用临时变量
        - 快速判断两个值是否相等：（a ^ b） == 0
- 50、数组中重复的数字
    - 数组重排：o(n), 每个元素最多被交换"两次"就可以找到自己的位置
    - 减少问题规模：先排序，后查找。因此o(n)取决于排序
    - map: 空间换时间
- 51、构建乘积数组
    - 推算公式：B[i] = C[i] * D[i]
    - 两次遍历，分别计算C[i] 、 D[i]

记忆点：
1. 对无序数组的处理
    - 减小问题规模：先排序，再处理(查找)。最快的快排的时间复杂度为o(nlogn)
    - 空间换时间：map
2. 对有序数组的处理
    - 二分查找，很快，仅o(logn)
    - 有时数据不是绝对有序，是相对有序，ex:旋转数组的最小数字:两个有序子数组
    - 有序实例 
        - 旋转数组的最小数字
        - 数字在排序数组中出现的次数
    - 无序数据，排序后实例
        - 数组中出现次数超过一半的数字
        - 数组中重复的数字
3. 减小问题规模的几个办法：
    - 先排序，再处理(对无序数据)
    - 找到切入点(从右上角或者左下角开始查找)
4. 动手画一画，找找规律，推导出方程
    - 顺时针打印矩阵
    - 连续子数组的最大和(动态规划)
    - 构建乘积数组
5. 动态规划DP
    - 关键词：最优解
    - 状态转移方程
6. 异或的几个特性

#### 字符串8道
- 2、替换空格
    - 新空间: 创建新字符串
    - 原地替换: 时间：o(n)
    - 库函数: String.split
- 27、字符串的排列: 较难
    - 递归
    - 字典序法：TODO
- 34、第一个只出现一次的字符
    - hash(数组代替)
    - 模式匹配：indexOfFirst、lastIndexOf
- 43、左旋转字符串
    - 巧妙：反转三次
    - 队列
    - 库函数：substring
- 44、反转单词序列
    - 反转两次：两个指针，确定一个单词位置
    - 库函数split + 栈
- 49、把字符串转换成整数
    - 统一符号 + 注意边界 + (累加和*10 + 当前字符)
- 52、正则表达式匹配 TODO
    - DP
- 53、表示数值的字符串
    - 逐位判断: A[.[B]][e|EC]或者.B[e|EC]
    - 正则
- 54、字符流中第一个不重复的字符
    - str保留所有字符，每次遍历
    - 队列保留字符，每次对头判断，重复出队

记忆点：
1. 数组代替哈希表(key是int的时候，SparseArray更好，省内存，不会autoBox)
   记住几个常用的字符ASC码(共128个):
   0: 48    A: 65    a: 97
2. "反转、旋转"，可以解决针对"部分"交换位置的需求，反转两次可以解决"部分"内元素乱序的问题
3. 库函数的使用：裁剪substring、分割split...
4. TODO：正则表达式的使用："表示数值的字符串"、"正则表达式匹配"


#### 数学3道
- 2、数值的整数次方
- 47、求1+2+3+4+···+n
- 48、不用加减乘除做加法

#### 栈3道
- 5、用两个栈来实现一个队列
- 20、包含min函数的栈
- 21、栈的压入、弹出序列

#### 递归4道
- 7、斐波那契数列
- 8、跳台阶
- 9、变态跳台阶
- 10、矩形覆盖

#### 回溯法2道
#### 其他15道
- 11、二进制中1的个数
- 29、最小的K个数
- 31、从1到n整数中1出现的次数
- 33、丑数
- 41、和为S的连续正数序列



- [剑指Offer系列刷题笔记汇总](https://cuijiahua.com/blog/2018/02/basis_67.html)
- [在线编程 > 剑指Offer](https://www.nowcoder.com/ta/coding-interviews?query=&asc=true&order=&page=1)
- [java解法：【剑指Offer】剑指offer题目汇总](https://www.cnblogs.com/gzshan/p/10910831.html)