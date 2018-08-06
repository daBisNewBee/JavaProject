package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * "ȫ�������⣬�Ӽ����⣬��Ϻ����ⶼ�Ǿ���Ļ������⡣"
 *
 * Combination Sum������ָ��ĳһ�������ҳ�ĳ������ʹ���Ϊ��ֵTarget��
 *
 *
 * TODO:
 *  https://github.com/FreeTymeKiyan/LeetCode-Sol-Res/blob/master/src/main/java/com/freetymekiyan/algorithms/level/medium/CombinationSum4.java
 *
 */
public class CombinationSum {

    List<List<Integer>> combinationSum2(int[] nums, int target){

        // �����ˣ������ȶ��������������������������ظ�ʱ��Ĳ�����
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

//        helper(res, new ArrayList<Integer>(), nums, target, 0);
        helperEx(res, new ArrayList<Integer>(), nums, target, 0);

        return res;
    }

    // ��һ������ǿ����ظ�����Ԫ��
    void helper(List<List<Integer>> res, List<Integer> tmpList, int[] nums, int remain, int start){
        if (remain < 0)
            return;

        if (remain == 0){
//           ���� ������ res.add(tmpList);
            res.add(new ArrayList<>(tmpList));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            tmpList.add(nums[i]);
            helper(res, tmpList, nums, remain-nums[i], i);
            tmpList.remove(tmpList.size() -1);
//           ���� ������ tmpList.remove(nums[i]); ����Ϊʲô��
        }
    }

    // �ڶ�������ǲ������ظ�����Ԫ��
    // res�� �ܵĽ��    tmpList: ��ʱ���   nums: ����͵�����Ԫ��
    // remian: ʣ��ĺ�    start: ��ʼλ��
    void helperEx(List<List<Integer>> res, List<Integer> tmpList, int[] nums, int remain, int start){
        if (remain < 0)
            return;

        if (remain == 0){
//           ���� ������ res.add(tmpList);
            res.add(new ArrayList<>(tmpList));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (((i+1) < nums.length) && (nums[i] == nums[i+1])) continue;
            tmpList.add(nums[i]);
            helperEx(res, tmpList, nums, remain-nums[i], i+1);// ע��� i+1 ��ʼ����
            tmpList.remove(tmpList.size() -1);
//           ���� ������ tmpList.remove(nums[i]); ����Ϊʲô��
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,1,1,3,4,5,1,2,};
        int target = 7;
        List<List<Integer>> res = new CombinationSum().combinationSum2(array,target);
        res.forEach(System.out::println);
        System.out.println("res = " + res.size());
    }
}
