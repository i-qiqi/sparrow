package leetcode.hot100;

public class T32Solution {

    /**
     * open parentheses, close parentheses
     * 
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0)
            return 0;
        int[] dp = new int[len]; // 以s[i] 结尾的最长有效括号
        dp[0] = 0;

        int ans = 0;

        for (int i = 1; i < len; i++) {
            char p = s.charAt(i);
            dp[i] = 0;
            if (p == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2;
                    if (i > 2) {
                        dp[i] += dp[i - 2];
                    }
                } else {
                    if (dp[i - 1] > 0) {
                        int j = i - dp[i - 1];
                        if (j >= 0 && s.charAt(j) == '(') {
                            dp[i] = 2 + dp[i - 1];
                            if (j >= 1) {
                                dp[i] += dp[j - 1];
                            }
                        }
                    }
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}
