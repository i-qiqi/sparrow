package leetcode.str;

import java.util.HashMap;
import java.util.Map;

public class T76Solution {
    class Solution {
        public String minWindow(String s, String t) {
            int len = s.length();

            int i = 0, j = 0;
            Map<Character, Integer> w = new HashMap<>();
            for (char c : t.toCharArray()) {
                w.put(c, w.getOrDefault(c, 0) + 1);
            }
            int sum = 0;
            String ans = "";
            int ansLen = Integer.MAX_VALUE;
            int ansStart = 0;
            int ansEnd = 0;
            while (i < len || j < len) {
                if (sum != w.size() && j < len) {
                    char c = s.charAt(j);
                    if (w.containsKey(c)) {
                        w.put(c, w.get(c) - 1);
                        if (w.get(c) == 0) {
                            sum++;
                        }
                    }
                    j++;
                    continue;
                }

                do {
                    char c = s.charAt(i);
                    if (w.containsKey(c)) {
                        if (w.get(c) == 0 && w.size() == sum) {
                            if (j - i <= ansLen) {
                                ansLen = j - i;
                                ansStart = i;
                                ansEnd = j;
                            }
                            sum--;
                        }
                        w.put(c, w.get(c) + 1);
                    }
                    i++;
                } while (sum == w.size());

            }
            return ansLen == Integer.MAX_VALUE ? "" : s.substring(ansStart, ansEnd);
        }
    }

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
     *           // 也可以更新target值;
     *           i++
     */
    class Solution1 {
        public String minWindow(String s, String t) {
            int len = s.length();

            int i = 0, j = 0;
            //C -> N : N 表示需要的字符数量
            Map<Character,Integer> needWin = new HashMap<>();
            for (char c : t.toCharArray()){
                needWin.put(c, needWin.getOrDefault(c, 0) + 1);
            }
            int sum = 0;
            String ans = "";
            int ansLen = Integer.MAX_VALUE;
            int ansStart = 0;
            int ansEnd = 0;
            while(j < len){
                char c = s.charAt(j);
                //拓展右边界
                if(needWin.containsKey(c)){
                    needWin.put(c, needWin.get(c)-1);
                    if(needWin.get(c) == 0) {
                        sum++;
                    }
                }
                j++;

                //收缩左边界
                while(sum == needWin.size()) {
                    c = s.charAt(i);
                    if(needWin.containsKey(c)){
                        if(needWin.get(c) == 0) {
                            sum--;
                        }
                        needWin.put(c, needWin.get(c)+ 1);
                    }
                    if(j - i <= ansLen ){
                        ansLen = j - i;
                        ansStart = i;
                        ansEnd = j;
                    }
                    i++;
                }

            }
            return ansLen == Integer.MAX_VALUE ? "" : s.substring(ansStart, ansEnd);
        }
    }
}
