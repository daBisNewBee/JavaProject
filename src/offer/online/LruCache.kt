
/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 *
 * 细节很多！易错！
 */

class LRUCache(private val capacity: Int) {

    class Node(var key:Int, var value:Int) {
        var prev:Node?= null
        var next:Node?= null
    }

    /**
     *
     * 注意用到的两个结构：
     *
     * 1. 一个 map，维护键值对
     * 2. 一个双指针 head、tail，实现的双向链表，维护顺序
     *
     * "LinkedHashMap"是简单实现！
     *
     * 时间复杂度：对于 put 和 get 都是 O(1)O(1)
     * 空间复杂度：O(capacity)
     *
     */
    private val cache = HashMap<Int,Node>()
    private val head:Node = Node(0,0)
    private val tail:Node = Node(0,0)

    init {
        head.prev = null
        head.next = tail
        tail.prev = head
        tail.next = null
    }

    fun get(key: Int):Int {
        if(cache.containsKey(key)) {
            var node:Node = cache[key]!!
            move2Head(node)
            return node.value
        }
        return -1
    }

    fun put(key: Int, value: Int) {
        var cur = cache.get(key)
        if(cur == null) {
            var newNode = Node(key, value)
            cache[key] = newNode
            add2Head(newNode)
            if(cache.size > capacity) {
                var last = removeLast()
                cache.remove(last.key)
            }
        } else {
            cur.value = value
            cache[key] = cur
            move2Head(cur)
        }
    }

    fun removeNode(node:Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    fun removeLast():Node {
        var last = tail.prev!!
        removeNode(last)
        return last
    }

    fun move2Head(node:Node){
        removeNode(node)
        add2Head(node)
    }

    fun add2Head(node:Node) {
        node.next = head.next
        head.next?.prev = node
        head.next = node
        node.prev = head
    }
}

fun main() {
    var cache = LRUCache(2)
    cache.put(1,1)
    cache.put(2,2)

    var ret = cache.get(1)
    println(ret)

    cache.put(3,3)

    ret = cache.get(2)
    println(ret)

}
