package leetcode.dp;

public class T72Solution {
    class Solution {
        public int minDistance(String word1, String word2) {
            if (word1.length() == 0) return word2.length();
            if (word2.length() == 0) return word1.length();
            int[][] dp = new int[word1.length()][word2.length()];
            //s[i] == p[j] : dp[i][j] = dp[i-1][j-1]
            //s[i] != p[j] : dp[i][j] = Math.min(dp[i-1][j-1] + 1 , d[i][j-1] + 1, dp[i-1][j] + 1) // R, I, D
            dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
            //dp[i][0]
            for (int i = 1; i < word1.length(); i++) {

                dp[i][0] = word1.charAt(i) == word2.charAt(0) ? i : Math.min(i + 1, dp[i - 1][0] + 1);//D
            }

            //dp[0][j]
            for (int j = 1; j < word2.length(); j++) {
                dp[0][j] = word1.charAt(0) == word2.charAt(j) ? j : Math.min(j + 1, dp[0][j - 1] + 1); //I
            }

            for (int i = 1; i < word1.length(); i++) {
                for (int j = 1; j < word2.length(); j++) {
                    if (word1.charAt(i) == word2.charAt(j)) {
                        // dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1] + 1), dp[i-1][j] + 1); 反证法后面的没必要
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1), dp[i - 1][j] + 1);
                    }
                }
            }

            return dp[word1.length() - 1][word2.length() - 1];
        }
    }

    class Solution1 {
        public int minDistance(String word1, String word2) {
            if (word1.length() == 0) return word2.length();
            if (word2.length() == 0) return word1.length();
            int[] pre = new int[word2.length()];
            pre[0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;

            //pre
            for (int j = 1; j < word2.length(); j++) {
                pre[j] = word1.charAt(0) == word2.charAt(j) ? j : Math.min(j + 1, pre[j - 1] + 1); //I
            }

            int[] cur = new int[word2.length()];
            for (int i = 1; i < word1.length(); i++) {
                for (int j = 0; j < word2.length(); j++) {
                    if (j == 0) {
                        cur[0] = word1.charAt(i) == word2.charAt(0) ? i : Math.min(i + 1, pre[0] + 1);
                    } else {
                        if (word1.charAt(i) == word2.charAt(j)) {
                            cur[j] = pre[j - 1];
                        } else {
                            cur[j] = Math.min(Math.min(pre[j - 1] + 1, cur[j - 1] + 1), pre[j] + 1);
                        }
                    }
                }
                System.arraycopy(cur, 0, pre, 0, pre.length);
            }

            return pre[word2.length() - 1];
        }
    }

    class Solution2 {
        public int minDistance(String word1, String word2) {
            if (word1.length() == 0) return word2.length();
            if (word2.length() == 0) return word1.length();
            int[] pre = new int[word2.length() + 1];
            //pre
            for (int j = 0; j <= word2.length(); j++) {
                pre[j] = j; //I
            }

            int[] cur = new int[word2.length() + 1];
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 0; j <= word2.length(); j++) {
                    if (j == 0) {
                        cur[0] = i;
                    } else {
                        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                            cur[j] = pre[j - 1];
                        } else {
                            cur[j] = Math.min(Math.min(pre[j - 1] + 1, cur[j - 1] + 1), pre[j] + 1);
                        }
                    }
                }
                System.arraycopy(cur, 0, pre, 0, pre.length);
            }

            return pre[word2.length()];
        }
    }


}
