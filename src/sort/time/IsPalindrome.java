package sort.time;

/**
 *
 * 判断[1]->[2]->[3]->[4]->[3]->[2]->[1]
 * 为回文链表
 *
 * 思路："数据预处理"（当然，数据预处理的时间复杂度足够低）
 *
 * 将一个链表拆分为子链表1、子链表2。
 *
 * 子链表2反转的目的：可从后节点获得前节点的引用（单向链表的缺点：无法根据后节点找到前节点）
 *
 * 依次比较。
 *
 * LeetCode 234
 *
 * Given a singly linked list, determine if it is a palindrome.
 Follow up:
 Could you do it in O(n) time and O(1) space?
 *
 *
 */
public class IsPalindrome {

    static class Node<T>{
        Node next;
        T value;

        Node(T _value){
            value = _value;
        }

        Node(){

        }
    }

    static class List<T>{
        Node<T> head;

        List(){
            head = new Node();
            head.next = null;
            head.value = null;
        }

        void addData(T value){
            Node<T> node = new Node(value);

            Node<T> tmp = head;

            while (tmp.next != null){
                tmp = tmp.next;
            }

            // 此时tmp 不为null，tmp->next为null
            tmp.next = node;
        }

        void print(){
            StringBuilder sb = new StringBuilder();
            sb.append("[head data]");
            Node<T> tmp = head.next;

            while (tmp != null){
                sb.append(String.format("->[%s]",tmp.value));
                tmp = tmp.next;
            }
            System.out.println("\n" + sb.toString());
        }
    }

    public static boolean isPalindrome(Node _head) {

        // 子链表1
        Node<Integer> head = _head;

        Node<Integer> low = head;
        Node<Integer> fast = head;
        /*
        * 同时从同一个位置启动。
        * 注意不是next！！
        *
        * 长度双数时：中心节点走到len/2的位置。 len = 8,mid = 4
        * 长度单数时：中心节点走到（len-1）/2的位置。len =9, mid = 5
        *
        * */
//        Node<Integer> fast = head.next;

        /*
        *
        * 查询链表的中间节点。注意首节点是有value的，不是头结点！
        *
        * 首节点、头节点的区别：
        * 头节点（head） -> 首节点。。。。。-> 尾节点
        *
        * */
        while (fast!=null
                && fast.next!=null
                && fast.next.next!=null){
            low = low.next;
            fast = fast.next.next;
        }

        // 子链表2
        low = reverse_recur(low);
//        low = reverse(low);

        while (head != null ){
            if (head.value != low.value){
                return false;
            }
            head = head.next;
            low = low.next;
        }

        return true;
    }

    public static void main(String[] args) {

        List<Integer> list  = new List();

        int[] arr = new int[]{1,2,3,4,3,2,1};
//        int[] arr = new int[]{1,2,3,3,2,1};

        for (int i : arr) {
            list.addData(i);
        }

        list.print();

        boolean ret = isPalindrome(list.head.next);
//        boolean ret = isPalindrome(list.head); 注意不是 list.head!
        System.out.println("ret = " + ret);

    }

    // https://www.cnblogs.com/csbdong/p/5674990.html
    private static Node<Integer> reverse(Node<Integer> head) {
        Node<Integer> pre = null;

        while (head != null){

            Node<Integer> next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }

        return pre;
    }


    /**
     *
     * 递归实现单链表逆置
     *
     * 吐槽一下 今日头条的面试：
     * https://www.v2ex.com/t/345807
     *
     * @param head
     * @return
     */
    private static Node<Integer> reverse_recur(Node<Integer> head){
        if (head == null || head.next == null)
            return head;

        Node<Integer> result = null;
        result = reverse_recur(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

}
