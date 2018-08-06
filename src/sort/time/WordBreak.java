package sort.time;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * LeetCode 234题
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 For example, given
 s = “leetcode”,
 dict = [“leet”, “code”].
 Return true because “leetcode” can be segmented as “leet code”.

 判断一个字符串能否由字典中的单词组成。刚开始有想过逆向思维——将字典中的单词进行排列组合，
 看能否与所给字符串相同。然而想想时间复杂度O(n!)还是算了。这一题就比较适合用"双指针进行遍历"。
 也是动态规划的一类题。
 *
 */
public class WordBreak {

    boolean solution(String s, Set<String> dict){
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && dict.contains(s.substring(j,i))){
                    f[i] = true;
                    break;
                }
            }
        }

        return f[s.length()];
    }

    public static void main(String[] args) {
        String s = "leetCode";

        System.out.println("WordBreak.main:"+s.substring(0,3));
        System.out.println("WordBreak.main:"+s.substring(0,4));

        Set<String> dict = new HashSet<>();
        dict.add("leet");
        dict.add("Code");
        boolean ret = new WordBreak().solution(s,dict);
        System.out.println("ret = " + ret);
    }
}
