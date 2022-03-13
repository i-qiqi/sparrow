package leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * s = "FADOBECODEBANC", t = "ABC"
 * "BANC"
 */
public class T76Solution {
    public static void main(String[] args) {
        //装箱、拆箱、Integer缓存
        Integer a = 127; // Integer.valueOf(127)
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        Integer e = new Integer(127);
        Integer f = Integer.valueOf(127);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == e);
        System.out.println(a.equals(e));
        System.out.println(a == f);
        System.out.println(a.equals(f));
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }

    public String minWindow(String s, String t) {
        int len = s.length();
        int i = 0, j = 0;

        Map<Character, Integer> wordCnt = new HashMap();
        for (int k = 0; k < t.length(); k++) {
            incr(wordCnt, t.charAt(k));
        }

        Map<Character, Integer> winCnt = new HashMap();
        Integer winSum = 0;
        int ansStartIdx = 0, ansEndIdx = -1;
        Integer ansLen = Integer.MAX_VALUE;
        while (j < len) {
            // 移动右指针
            Character c = s.charAt(j);
            if (wordCnt.containsKey(c) && !isMatch(winSum, wordCnt)) {
                incr(winCnt, c);
                if (winCnt.get(c).equals(wordCnt.get(c))) {
                    winSum++;
                }
            }
            // 移动左指针
            if (isMatch(winSum, wordCnt)) {
                // 移动i, 一直移动到word中包含的字符为止
                c = s.charAt(i);
                while (!wordCnt.containsKey(c)) {
                    i++;
                    c = s.charAt(i);
                }

                int tempAnsLen = j - i + 1;
                if (tempAnsLen < ansLen) {
                    ansStartIdx = i;
                    ansEndIdx = j;
                    ansLen = tempAnsLen;
                }

                decr(winCnt, c);
                if (winCnt.get(c).equals(wordCnt.get(c) - 1))
                    winSum--;
                    
                i++;
            }

            if (!isMatch(winSum, wordCnt))
                j++;

        }

        return s.substring(ansStartIdx, ansEndIdx + 1);
    }

    private boolean isMatch(Integer sum, Map<Character, Integer> mp){
        return sum.equals(mp.size()) ? true : false;
    } 

    private void incr(Map<Character, Integer> mp, Character key) {
        mp.put(key, mp.getOrDefault(key, 0) + 1);
    }

    private void decr(Map<Character, Integer> mp, Character key) {
        Integer cnt = mp.get(key);
        mp.put(key, cnt - 1);
    }

}
