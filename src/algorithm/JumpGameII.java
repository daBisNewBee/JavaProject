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
     * Greedy 贪心算法
     *
     * 缺点：只能得出最大步数，但是无法保证该步数能达到目标
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
        // 最远距离
        int max = 0;
        // 当前能走的最远距离
        int curMax = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max,nums[i]+i);
            /*
            * 1. 每次把发现的"最大路径记录下来" <----- "贪心"体现在这里
            * 2. 当走到当前点时，更新步数，更新最大路径
            * 3. 循环第二步
            * 。。。。
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
     * 使用 BFS 去解
     *
     * time: o(n)  space:o(1)（没有开辟新空间）
     *
     * @param nums
     * @return
     */
    int jump2(int[] nums){

        if (nums == null || nums.length < 2)
            return 0;

        int level = 0;
        // 最远距离
        int max = 0;
        // 当前能走的最远距离
        int curMax = 0;
        int i = 0;

        // 表示 当前步有效，仍能继续往下走。 参考"所以 max >= i， 才能继续走下去"
        // 判断是否"能走"
        while (curMax >= i){
            level++;
            // 在最大步数内，寻找最大值，当大于等于数组长度时，表示可达
            for (; i <= curMax; i++) {
                max = Math.max(nums[i]+i,max);
                if (max >= nums.length-1) return level;
            }
            curMax = max;
        }

        // 不能return level。产生bug：不可达的数组时，仍有返回值
        return 0;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 2, 1, 0, 4}; // 有问题！仍然会有结果，是2！！
        int[] nums = new int[]{2,3,1,1,1,1};
//        int[] nums = new int[]{2,3,1,1,4};
        System.out.println("level = " + new JumpGameII().jump(nums));
    }
}
