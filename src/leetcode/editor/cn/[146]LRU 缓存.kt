
//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 ke
//y-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 105 
// 最多调用 2 * 105 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1860 👎 0


class P_146_LruCache {
    //leetcode submit region begin(Prohibit modification and deletion)
class LRUCache(capacity: Int) {

    private var cache = mutableMapOf<Int, MyNode>()
    private var head = MyNode()
    private var tail = MyNode()
    private var capacity = capacity

    init {
        head.next = tail
        tail.pre = head
    }

    fun get(key: Int): Int {
        if (!cache.containsKey(key)) {
            return -1
        }
        var ans = cache[key]!!
        removeNode(ans)
        add2First(ans)
        return ans.value
    }

    private fun add2First(node: MyNode) {
        cache[node.key] = node
        node.next = head.next
        head.next?.pre = node
        head.next = node
        node.pre = head
    }

    private fun removeNode(node: MyNode) {
        cache.remove(node.key)
        node.pre?.next = node.next
        node.next?.pre = node.pre
    }

    private fun removeLast() {
        removeNode(tail.pre!!)
    }


    fun put(key: Int, value: Int) {
        var node:MyNode
        if (cache.containsKey(key)) {
            node = cache[key]!!
            // 不要忘了更新value
            node.value = value
            removeNode(node)
        } else {
            node = MyNode(key = key, value = value)
        }
        add2First(node)
        if (cache.size > capacity) {
            removeLast()
        }
    }

        private class MyNode (var next:MyNode? = null, var pre:MyNode? = null, var key:Int = 0, var value: Int = 0)
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    var obj = P_146_LruCache.LRUCache(2)
    obj.put(1, 1)
    obj.put(2, 2)
    obj.get(1)
    obj.put(3,3)
    var ans = obj.get(2)
    println(ans)

}