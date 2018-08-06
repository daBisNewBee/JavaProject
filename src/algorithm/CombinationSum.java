package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * "全排列问题，子集问题，组合和问题都是经典的回溯问题。"
 *
 * Combination Sum问题是指从某一数组中找出某几个数使其和为定值Target。
 *
 *
 * TODO:
 *  https://github.com/FreeTymeKiyan/LeetCode-Sol-Res/blob/master/src/main/java/com/freetymekiyan/algorithms/level/medium/CombinationSum4.java
 *
 */
public class CombinationSum {

    List<List<Integer>> combinationSum2(int[] nums, int target){

        // 别忘了！！首先对数组进行排序，来方便后续过滤重复时候的操作。
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

//        helper(res, new ArrayList<Integer>(), nums, target, 0);
        helperEx(res, new ArrayList<Integer>(), nums, target, 0);

        return res;
    }

    // 第一种情况是可以重复利用元素
    void helper(List<List<Integer>> res, List<Integer> tmpList, int[] nums, int remain, int start){
        if (remain < 0)
            return;

        if (remain == 0){
//           不是 ！！！ res.add(tmpList);
            res.add(new ArrayList<>(tmpList));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            tmpList.add(nums[i]);
            helper(res, tmpList, nums, remain-nums[i], i);
            tmpList.remove(tmpList.size() -1);
//           不是 ！！！ tmpList.remove(nums[i]); 想想为什么？
        }
    }

    // 第二种情况是不可以重复利用元素
    // res： 总的结果    tmpList: 临时结果   nums: 待求和的数组元素
    // remian: 剩余的和    start: 起始位置
    void helperEx(List<List<Integer>> res, List<Integer> tmpList, int[] nums, int remain, int start){
        if (remain < 0)
            return;

        if (remain == 0){
//           不是 ！！！ res.add(tmpList);
            res.add(new ArrayList<>(tmpList));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (((i+1) < nums.length) && (nums[i] == nums[i+1])) continue;
            tmpList.add(nums[i]);
            helperEx(res, tmpList, nums, remain-nums[i], i+1);// 注意从 i+1 开始！！
            tmpList.remove(tmpList.size() -1);
//           不是 ！！！ tmpList.remove(nums[i]); 想想为什么？
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
