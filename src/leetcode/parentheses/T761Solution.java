package leetcode.parentheses;

import java.util.*;

public class T761Solution {
    public class Solution {
        public String makeLargestSpecial(String s) {

            int cntDiff = 0;
            int start = 0;

            List<String> res = new ArrayList<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                cntDiff = c == '1' ? cntDiff + 1 : cntDiff - 1;

                if (cntDiff == 0) {
//                    if (start + 1 == i) {
//                        res.add("10");
//                    } else {
                    res.add('1' + makeLargestSpecial(s.substring(start + 1, i + 1)) + '0');
//                    }
                    start = i + 1;
                }
            }

            Collections.sort(res, (s1, s2) -> -s1.compareTo(s2));
            return String.join("", res);
        }

        public StringBuilder helper(StringBuilder sb) {
            int cntDiff = 0;
            int start = 0;

            List<String> res = new ArrayList<>();
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                cntDiff = c == '1' ? cntDiff + 1 : cntDiff - 1;

                if (cntDiff == 0) {
                    StringBuilder tempSb = new StringBuilder("1").append(makeLargestSpecial(sb.substring(start + 1, i + 1))).append("0");
                    res.add(tempSb.toString());
                    start = i + 1;
                }
            }

            Collections.sort(res, (s1, s2) -> -s1.compareTo(s2));
            return new StringBuilder(String.join("", res));
        }
    }

    public class Solution1 {
        Map<String, String> memo;

        public String makeLargestSpecial(String s) {
            memo = new HashMap<>();
            return helper(s);
        }

        public String helper(String s) {
            int cntDiff = 0;
            int start = 0;

            List<String> res = new ArrayList<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                cntDiff = c == '1' ? cntDiff + 1 : cntDiff - 1;

                if (cntDiff == 0) {

                    String tempStr = s.substring(start + 1, i);
//                    System.out.println(tempStr);
                    if (memo.containsKey(tempStr)) {
//                        System.out.println("test");
                        res.add('1' + memo.get(tempStr) + '0');
                    } else {
                        String tempAns = helper(tempStr);
                        res.add('1' + tempAns + '0');
                        memo.put(tempStr, tempAns);
                    }

                    start = i + 1;
                }
            }

            Collections.sort(res, (s1, s2) -> -s1.compareTo(s2));
            return String.join("", res);
        }
    }

    public static void main(String[] args) {
        T761Solution t761Solution = new T761Solution();
        Solution1 so = t761Solution.new Solution1();
        so.makeLargestSpecial("110011100110110000");
    }
}
