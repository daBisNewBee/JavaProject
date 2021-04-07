package offer

class ListNode {
    var value:Int = 0
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode(value=$value)"
    }

    companion object {
        private val CACHE = ArrayList<ListNode>()

        fun obtainNode(value:Int):ListNode? {
            var index = findNode(value)
            return if (index >= 0) {
                CACHE[index]
            } else {
                var node = ListNode()
                node.value = value
                CACHE.add(node)
                node
            }
        }

        fun findNode(value:Int):Int {
            if (CACHE.isEmpty()) return -1
            for (index in CACHE.indices) {
                if (CACHE[index].value == value) return index
            }
            return -1
        }

    }
}