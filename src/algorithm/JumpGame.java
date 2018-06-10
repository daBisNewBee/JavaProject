package algorithm;

/**
 *
 * 55. Jump Game
 *
 * ˼�룺ÿ�ζ�ȡ��ǰ���ֵ��ʹ��̰���㷨�ϼ򵥣�
 *
 *  2, 3, 1, 1, 4   -> true
 *
 *  3, 2, 1, 0, 4   -> false
 *
 *
 *  i : [0,4]
 *  0   1   2   3   4
 *
 *              max = 3? ���Լ�����
 *              max = 2? �޷�����
 *              ���� max >= i�� ���ܼ�������ȥ
 *              i > maxʱ���ͽ�����false
 *
 *  max: ���������i = 0����
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
