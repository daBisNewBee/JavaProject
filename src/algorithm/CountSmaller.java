package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSmaller {

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Integer[] ans = new Integer[len];
        List<Integer> sorted = new ArrayList<>();

        for (int i = len - 1; i >= 0; i--){
            int index = findIndex(sorted, nums[i]);
            index = index == -1 ? 0 : index;
            ans[i] = index;
            sorted.add(index,nums[i]);
        }
        return Arrays.asList(ans);
    }

    private int findIndex(List<Integer> sorted, int target){
        if (sorted.size() == 0) return 0;
        int lf = 0, rt = sorted.size()-1;
        while (lf < rt){
//            int mid = (rt + lf) / 2;
            int mid = lf + (rt + 1 - lf) / 2;
            if (sorted.get(mid) >= target){
                rt = mid - 1;
            }else{
                lf = mid;
            }
        }
        if (sorted.get(lf) < target) return lf+1;
        return -1;
    }

    public static void main(String[] args) {
        int[] A = new int[]{5, 2, 6, 1};
        List<Integer> list = new CountSmaller().countSmaller(A);
        list.forEach(System.out::println);
    }
}
