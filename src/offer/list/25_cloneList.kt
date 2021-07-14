package offer.list

/*
*
* ����һ����������ÿ���ڵ����нڵ�ֵ���Լ�����ָ�룬
* һ��ָ����һ���ڵ㣬��һ������ָ��randomָ��һ������ڵ㣩��
* ��Դ������������������ؿ������ͷ��㡣
*��ע�⣬���������벻Ҫ���ز����еĽڵ����ã�������������ֱ�ӷ��ؿգ�
*
* ��ͨԭʼ˼·��
* �Ȱ���next���ƣ�Ȼ���������randomָ�룬���ʱ��Ҫ��λrandom��λ�ã���λһ����Ҫһ�α�������ҪO��n^2���ĸ��Ӷȡ�
*
* �Ը߼�˼·��
* ��λrandomʱ�����ݵ�һ�δ��µ�map����o(n)�Ŀռ临�Ӷ�ʹʱ�临�ӶȽ�����o(n)
*
* �������뵽��˼·��
* ͬ���Ȱ�next���ƣ����ǰѸ��ƺ�Ľڵ�ŵ�ԭ�ڵ���棬����Ժ����׵����random��
* �������żλ�ò����������ʱ�临�Ӷ�O��n��������Ҫ����ռ䡣
*
* */


class Node(var `val`:Int) {

    var next:Node ?= null
    var random:Node ?= null


    override fun toString(): String {
        return "Node(value=$`val` ${random?.`val`})->"
    }
}

/**
 * ֻҪ����һ�Σ�ʱ��o(n),�ռ�o(n)
 * TODO: ���ǰѸ��ƺ�Ľڵ�ŵ�ԭ�ڵ���棬�ռ�o(1)
 */

var visited:MutableMap<Node, Node> = HashMap()

fun getClonedNode(node:Node?):Node? {
    if(node == null) return null
    if(!visited.containsKey(node)) {
        var newNode = Node(node.`val`)
        visited[node] = newNode
    }
    return visited[node]
}

fun copyRandomList(node: Node?): Node? {
    if(node == null) return null
    var cur = node
    var curNew = getClonedNode(cur)
    while(cur != null) {
        curNew?.next = getClonedNode(cur!!.next)
        curNew?.random = getClonedNode(cur!!.random)
        cur = cur.next
        curNew = curNew?.next
    }
    return visited[node]
}

fun main() {
    var data = Array(5){Node(0)}
    var last:Node? = null
    for (index in data.indices) {
        data[index].`val` = index
        last?.next = data[index]
        last = data[index]
    }

    data[0].random = data[3]
    data[2].random = data[4]

    var head = data[0]
    var cur:Node ? = head
    while (cur != null) {
        println(cur)
        cur = cur.next
    }

    println()

    var ret = copyRandomList(head)
    while (ret != null) {
        println(ret)
        ret = ret.next
    }

}