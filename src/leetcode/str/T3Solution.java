package leetcode.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class T3Solution {
    /**
     *  换一种等价写法
     *  while right < len :
     *      c <- get s[j]
     *     // 拓展右边界
     *     if c满足添加到窗口的条件:
     *        添加到窗口内;
     *        更新判断窗口右拓展条件的变量
     *        j++;
     *
     *     // 收缩右边界
     *     while 不满足窗口右拓展条件 :
     *          c <- get s[i]
     *          if 满足在窗口中的条件:
     *              if 窗口元素临界条件:
     *                 更新判断窗口拓展条件的指标;
     *                 更新target值;
     *              从窗口中剔除
     *           i++
     */
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {

            int ans = 0;
            int len = s.length();
            char[] arr = s.toCharArray();
            Map<Character, Integer> w = new HashMap<>();

            int i = 0, j = 0;
            while (j < len) {
                char c = arr[j];
                w.put(c, w.getOrDefault(c, 0) + 1); // 计数
                j++;
                //删直到窗口中c.cnt == 1
                while (w.get(c) > 1) {
                    char d = arr[i];
                    w.put(d, w.get(d) - 1);
                    i++;
                }
                ans = Math.max(ans, j - i);
            }
            return ans;
        }
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {

            int ans = 0;
            int len = s.length();
            char[] arr = s.toCharArray();
            Set<Character> w = new HashSet<>();

            int i = 0, j = 0;

            while (j < len) {
                char c = arr[j];
                if (!w.contains(c)) {
                    w.add(arr[j]);
                    ans = Math.max(ans, w.size());
                    j++;
                } else {
                    do {
                        w.remove(arr[i]);
                    } while (arr[i++] != c);
                }
            }
            return ans;
        }
    }
}
