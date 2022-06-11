package leetcode.dp;

public class T343Solution {
    class Solution {
        public int integerBreak(int n) {
            int[][] dp = new int[n + 1][n + 1];
            //dp[1][j]
            for (int j = 1; j <= n; j++) {
                dp[1][j] = j;
            }
            for (int i = 2; i <= n; i++) {
                for (int j = 2; j <= n; j++) {
                    dp[i][j] = -1;
                    for (int k = 1; k <= j - i + 1; k++) {
                        dp[i][j] = Math.max(dp[i][j], k * dp[i - 1][j - k]);
                    }
                }
            }

            int ans = dp[2][n];
            for (int i = 2; i <= n; i++) {
                ans = Math.max(ans, dp[i][n]);
            }

            return ans;
        }
    }

    class Solution1 {
        public int integerBreak(int n) {
            int[] dp = new int[n+1];
            dp[1] = 1;
            dp[2] = 1;
            for(int i = 3; i <= n; i++){
                //TODO: j < i 改成 j < 4更牛批， 需要推导
                for(int j = 1; j < 4; j++){
                    dp[i] = Math.max(dp[i],Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }
    }
}
