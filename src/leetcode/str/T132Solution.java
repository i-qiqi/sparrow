package leetcode.str;

public class T132Solution {
    class Solution {
        boolean isPalindrome[][];

        public int minCut(String s) {

            int n = s.length();
            int dp[] = new int[s.length()];

            dp[0] = 0;
            isPalindrome = new boolean[n][n];
            // 预处理标记数组
            for (int i = 0; i < n; i++) {
                centerExtend(s, i, i);// 奇数
                centerExtend(s, i, i + 1);//偶数
            }

            for (int i = 1; i < n; i++) {
                int minCnt = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    if (isPalindrome[k][i]) {
                        minCnt = Math.min(minCnt, k == 0 ? 0 : dp[k - 1] + 1);
                        if (minCnt == 0) break;
                    }
                }
                dp[i] = minCnt;
            }

            return dp[n - 1];
        }

        private void centerExtend(String s, int i, int j) {
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                isPalindrome[i][j] = true;
                i--;
                j++;
            }
        }
    }
}
