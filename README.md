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



#### 二叉搜索树3道
#### 数组11道
#### 字符串8道
#### 栈3道
#### 递归4道
#### 回溯法2道
#### 其他15道

- [剑指Offer系列刷题笔记汇总](https://cuijiahua.com/blog/2018/02/basis_67.html)
- [在线编程 > 剑指Offer](https://www.nowcoder.com/ta/coding-interviews?query=&asc=true&order=&page=1)
- [java解法：【剑指Offer】剑指offer题目汇总](https://www.cnblogs.com/gzshan/p/10910831.html)