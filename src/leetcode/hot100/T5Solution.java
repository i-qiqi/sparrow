package leetcode.hot100;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class T5Solution {
    public static void main(String[] args) {

        System.out.println(T5Solution.longestPalindrome("aacabdkacaa"));
        System.out.println(T5Solution.longestPalindrome1("aacabdkacaa"));

    }

    /**
     * f[i][j] = f[i+1][j-1] + 2, s[i] == s[j]
     * = 0 , s[i] != s[j]
     * 
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();

        int[][] dp = new int[len][len];
        int ans = 0;
        int st = 0, ed = 0;

        for (int step = 1; step <= len; step++) {
            for (int i = 0; i + step - 1 < len; i += 1) {
                int j = i + step - 1;
                if (step == 1) {
                    dp[i][j] = 1;
                } else if (step == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 0;
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] != 0 ? dp[i + 1][j - 1] + 2 : 0;
                }

                if (dp[i][j] > ans) {
                    ans = dp[i][j];
                    st = i;
                    ed = j;
                }
            }
        }
        return s.substring(st, ed + 1);
    }

    /**
     * 
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        int len = s.length();
        int ans = 0;
        int st = 0, ed = 0;
        Result res = new Result(0, 0, 0);
        for (int i = 0; i < len; i++) {
            Result res1 = centerExtend(s, i, i);
            Result res2 = centerExtend(s, i, i + 1);
            res1 = res1.ans > res2.ans ? res1 : res2;
            res = res.ans > res1.ans ? res : res1;
        }
        return s.substring(res.st, res.ed);
    }

    private static Result centerExtend(String s, int left, int right) {
        Result res = new Result(0, 0, 0);
        int len = s.length();
        for (; left >= 0 && right < len; left--, right++) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
        }
        res.ans = right - left - 1;
        res.st = left + 1;
        res.ed = right;

        return res;
    }

    private static class Result {
        int ans;
        int st;
        int ed;

        Result(int ans, int st, int ed) {
            this.ans = ans;
            this.st = st;
            this.ed = ed;
        }
    }

}