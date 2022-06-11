package leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 *  @timeout
 *  @equals not "=="
 */
public class T567Solution {
    class Solution {
        public boolean checkInclusion(String s1, String s2) {

            int len = s2.length();
            Map<Character, Integer> needWin = new HashMap<>();
            for (char c : s1.toCharArray()) {
                needWin.put(c, needWin.getOrDefault(c, 0) + 1);
            }
            Map<Character, Integer> cntWin = new HashMap<>();
            int sum = 0;
            int i = 0, j = 0;

            while (j < len) {
                char c = s2.charAt(j);
                j++;
                if (needWin.containsKey(c)) {
                    cntWin.put(c, cntWin.getOrDefault(c, 0) + 1);
                    if (cntWin.get(c).equals(needWin.get(c))) {
                        sum++;
                    }
                }

                while (j - i >= s1.length()) {
                    if (sum == needWin.size()) {
                        return true;
                    }
                    char d = s2.charAt(i);
                    i++;
                    if (needWin.containsKey(d)) {
                        if (cntWin.get(d).equals(needWin.get(d))) {
                            sum--;
                        }
                        cntWin.put(d, cntWin.get(d) - 1);
                    }
                }

            }

            return false;

        }
    }

}
