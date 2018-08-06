package sort.time;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * LeetCode 234��
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 For example, given
 s = ��leetcode��,
 dict = [��leet��, ��code��].
 Return true because ��leetcode�� can be segmented as ��leet code��.

 �ж�һ���ַ����ܷ����ֵ��еĵ�����ɡ��տ�ʼ���������˼ά�������ֵ��еĵ��ʽ���������ϣ�
 ���ܷ��������ַ�����ͬ��Ȼ������ʱ�临�Ӷ�O(n!)�������ˡ���һ��ͱȽ��ʺ���"˫ָ����б���"��
 Ҳ�Ƕ�̬�滮��һ���⡣
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
