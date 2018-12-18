package sort.time;

/**
 *
 * �ж�[1]->[2]->[3]->[4]->[3]->[2]->[1]
 * Ϊ��������
 *
 * ˼·��"����Ԥ����"����Ȼ������Ԥ�����ʱ�临�Ӷ��㹻�ͣ�
 *
 * ��һ��������Ϊ������1��������2��
 *
 * ������2��ת��Ŀ�ģ��ɴӺ�ڵ���ǰ�ڵ�����ã����������ȱ�㣺�޷����ݺ�ڵ��ҵ�ǰ�ڵ㣩
 *
 * ���αȽϡ�
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

            // ��ʱtmp ��Ϊnull��tmp->nextΪnull
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

        // ������1
        Node<Integer> head = _head;

        Node<Integer> low = head;
        Node<Integer> fast = head;
        /*
        * ͬʱ��ͬһ��λ��������
        * ע�ⲻ��next����
        *
        * ����˫��ʱ�����Ľڵ��ߵ�len/2��λ�á� len = 8,mid = 4
        * ���ȵ���ʱ�����Ľڵ��ߵ���len-1��/2��λ�á�len =9, mid = 5
        *
        * */
//        Node<Integer> fast = head.next;

        /*
        *
        * ��ѯ������м�ڵ㡣ע���׽ڵ�����value�ģ�����ͷ��㣡
        *
        * �׽ڵ㡢ͷ�ڵ������
        * ͷ�ڵ㣨head�� -> �׽ڵ㡣��������-> β�ڵ�
        *
        * */
        while (fast!=null
                && fast.next!=null
                && fast.next.next!=null){
            low = low.next;
            fast = fast.next.next;
        }

        // ������2
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
//        boolean ret = isPalindrome(list.head); ע�ⲻ�� list.head!
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
     * �ݹ�ʵ�ֵ���������
     *
     * �²�һ�� ����ͷ�������ԣ�
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
