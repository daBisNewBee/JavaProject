package algorithm;

/**
 *
 * 55. Jump Game
 *
 * 思想：每次都取当前最大值，使用贪心算法较简单！
 *
 *  2, 3, 1, 1, 4   -> true
 *
 *  3, 2, 1, 0, 4   -> false
 *
 *
 *  i : [0,4]
 *  0   1   2   3   4
 *
 *              max = 3? 可以继续走
 *              max = 2? 无法继续
 *              所以 max >= i， 才能继续走下去
 *              i > max时，就结束。false
 *
 *  max: 最大步数（从i = 0算起）
 *
 *
 *
 */
public class JumpGame {

    boolean jumpGame(int[] nums) {

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > max)
                return false;

            max = Math.max(nums[i] + i, max);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,3,1,1,4};
        int[] b = new int[]{3,2,1,0,4};
        System.out.println("a = " + new JumpGame().jumpGame(a));
        System.out.println("b = " + new JumpGame().jumpGame(b));

    }
}
