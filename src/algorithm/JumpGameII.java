package algorithm;

/**
 * 45. Jump Game II
 *
 * Input: [2,3,1,1,4]
 Output: 2
 Explanation: The minimum number of jumps to reach the last index is 2.
 Jump 1 step from index 0 to 1, then 3 steps to the last index.


 *
 *
 */
public class JumpGameII {

    /**
     *
     * https://www.youtube.com/watch?v=Vph9iepTHiA
     *
     * Greedy ̰���㷨
     *
     * ȱ�㣺ֻ�ܵó�������������޷���֤�ò����ܴﵽĿ��
     *
     * 2,3,1,1,4
     *
     * 2,3,1,1,1,1
     *
     * time: o(n)  space:o(1)
     *
     * @param nums
     * @return
     */
    int jump(int[] nums){

        if (nums == null || nums.length < 2)
            return 0;

        int level = 0;
        // ��Զ����
        int max = 0;
        // ��ǰ���ߵ���Զ����
        int curMax = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max,nums[i]+i);
            /*
            * 1. ÿ�ΰѷ��ֵ�"���·����¼����" <----- "̰��"����������
            * 2. ���ߵ���ǰ��ʱ�����²������������·��
            * 3. ѭ���ڶ���
            * ��������
            * */
            if (curMax == i){
                level++;
                curMax = max;
            }
        }

        return level;
    }


    /**
     *
     * ʹ�� BFS ȥ��
     *
     * time: o(n)  space:o(1)��û�п����¿ռ䣩
     *
     * @param nums
     * @return
     */
    int jump2(int[] nums){

        if (nums == null || nums.length < 2)
            return 0;

        int level = 0;
        // ��Զ����
        int max = 0;
        // ��ǰ���ߵ���Զ����
        int curMax = 0;
        int i = 0;

        // ��ʾ ��ǰ����Ч�����ܼ��������ߡ� �ο�"���� max >= i�� ���ܼ�������ȥ"
        // �ж��Ƿ�"����"
        while (curMax >= i){
            level++;
            // ��������ڣ�Ѱ�����ֵ�������ڵ������鳤��ʱ����ʾ�ɴ�
            for (; i <= curMax; i++) {
                max = Math.max(nums[i]+i,max);
                if (max >= nums.length-1) return level;
            }
            curMax = max;
        }

        // ����return level������bug�����ɴ������ʱ�����з���ֵ
        return 0;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 2, 1, 0, 4}; // �����⣡��Ȼ���н������2����
        int[] nums = new int[]{2,3,1,1,1,1};
//        int[] nums = new int[]{2,3,1,1,4};
        System.out.println("level = " + new JumpGameII().jump(nums));
    }
}
