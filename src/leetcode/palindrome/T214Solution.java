package leetcode.palindrome;

public class T214Solution {
    class Solution {
        public String shortestPalindrome(String s) {
            if (s.length() == 0) return s;
            int[] next = new int[s.length() * 2 + 1];

            next[0] = -1; // 表示不存在前后对称的
            StringBuilder sb = new StringBuilder(s);
            StringBuilder sb1 = new StringBuilder(s);
            //防止"aaaaa"的情况
            // 0 1 2 3  4 5 6 7 8 9  i
            //-1 0 1 2 3  4 5 6 7 8  next
            // a a a a a  a a a a a
            sb.append("#").append(sb1.reverse());

            for (int i = 1; i < sb.length(); i++) {
                int k = next[i - 1];
                while (k != -1) {
                    if (sb.charAt(i) == sb.charAt(k + 1)) {
                        next[i] = k + 1;
                        break;
                    } else {
                        k = next[k];
                    }
                }
                // k == -1;
                if (k == -1) {
                    next[i] = sb.charAt(i) == sb.charAt(0) ? 0 : -1;
                }
                System.out.println(next[i]);
            }


            int lastNext = next[sb.length() - 1];
            System.out.println(lastNext);
            if (lastNext == -1) {
                lastNext = 0;
            }
            sb = new StringBuilder(s);
            return sb.reverse() + s.substring(lastNext + 1, s.length());
        }
    }

    class Solution1 {
        public String shortestPalindrome(String s) {
            if (s.length() == 0) return s;
            int[] next = new int[s.length() * 2 + 1];

            next[0] = -1; // 表示不存在前后对称的
            StringBuilder sb = new StringBuilder(s);
            StringBuilder sb1 = new StringBuilder(s);
            //防止"aaaaa"的情况
            // 0 1 2 3  4 5 6 7 8 9  i
            //-1 0 1 2 3  4 5 6 7 8  next
            // a a a a a  a a a a a
            sb.append("#").append(sb1.reverse());

            for (int i = 1; i < sb.length(); i++) {
                next[i] = computeNextValue(i, sb, next);
            }
            int lastNext = next[sb.length() - 1];
            System.out.println(lastNext);
            if (lastNext == -1) {
                lastNext = 0;
            }
            sb = new StringBuilder(s);
            return sb.reverse().append(s.substring(lastNext + 1, s.length())).toString();
        }

        public int computeNextValue(int i, StringBuilder sb, int[] next) {
            int k = next[i - 1];
            for (; ; ) {
                if (k == -1) {
                    return next[i] = sb.charAt(i) == sb.charAt(0) ? 0 : -1;
                }
                if (sb.charAt(i) == sb.charAt(k + 1)) {
                    return k + 1;
                } else {
                    k = next[k];
                }
            }
        }
    }


    /**
     * better solution
     */
    class Solution2 {
        public String shortestPalindrome(String s) {
            if (s.length() == 0) return s;
            StringBuilder sb = new StringBuilder(s);

            int[] next = new int[s.length()];
            next[0] = -1;
            for (int i = 1; i < s.length(); i++) {
                next[i] = computeNextValue(i, s, next);
            }

            StringBuilder rsb = new StringBuilder(s).reverse();
            // rsb 作为母串，s作为模式串，进行kmp匹配
            int i = 0, j = 0;
            while (i < rsb.length()) {
                //匹配
                if (rsb.charAt(i) == s.charAt(j)) {
                    i++;
                    j++;
                } else { //失配
                    //  if(next[j-1] == -1) {
                    //      j = 0;
                    //  }
                    if (j == 0 || next[j - 1] == -1) {
                        j = 0;
                        i++;
                    } else {
                        j = next[j - 1] + 1;
                    }
                }
            }
            // System.out.println(j);
            return rsb.append(s.substring(j, s.length())).toString();
        }

        public int computeNextValue(int i, String s, int[] next) {
            int k = next[i - 1];
            for (; ; ) {
                if (k == -1) {
                    return next[i] = s.charAt(i) == s.charAt(0) ? 0 : -1;
                }
                if (s.charAt(i) == s.charAt(k + 1)) {
                    return k + 1;
                } else {
                    k = next[k];
                }
            }
        }
    }
}
