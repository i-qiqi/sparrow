package leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

public class T322Solution {

    // timeout
    class Solution1 {
        int total;
        int ans;
        Map<Integer, Integer> mem;

        public int coinChange(int[] coins, int amount) {
            ans = Integer.MAX_VALUE;
            mem = new HashMap<>();
            total = amount;
            traceback(coins, 0, 0, 0);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        private void traceback(int[] coins, int start, int cnt, int sum){
            if ( sum == total ) {
                ans = Math.min(ans, cnt);
            }
    
            if (start >= coins.length){
                return;
            }
    
            if ( sum > total) {
                return;
            }
    
            int left = 0;
//            for (int start = 0; )
//            map.put(sum, cnt);
            traceback(coins, start, cnt+1, sum + coins[start]);
            traceback(coins, start + 1, cnt, sum);
        }
    }

    class Solution {

        public int coinChange(int[] coins, int amount) {

            int[] dp = new int[amount + 1];

            for (int i = 1; i <= amount; i++) {
                dp[i] = amount + 1;
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i && dp[i - coins[j]] != amount + 1) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);

                    }
                }
            }

            return dp[amount] == amount + 1 ? -1 : dp[amount];
        }
    }

    /**
     * 完全背包也可以做
     */
    class Solution01 {
        public int coinChange(int[] coins, int amount) {
            // dp[i][j] = min(dp[i-1][j], dp[i][j-coins[i]] + 1)
            int[][] dp = new int[coins.length+1][amount+1];
            for(int j = 1; j <= amount; j++){
                dp[0][j] = amount+1;
            }
            for(int i = 0; i <= coins.length; i++){
                dp[i][0] = 0;
            }

            for(int i = 1; i <= coins.length; i++){
                for(int j = 1; j <= amount; j++){
                    dp[i][j] = dp[i-1][j];
                    if(j >= coins[i-1]){
                        dp[i][j] = Math.min(dp[i][j-coins[i-1]] + 1, dp[i][j]);
                    }
                }
            }

            return dp[coins.length][amount] == amount+1 ? -1 : dp[coins.length][amount];
        }
    }

    /**
     * 状态压缩
     */
    class Solution02 {
        public int coinChange(int[] coins, int amount) {
            // dp[i][j] = min(dp[i-1][j], dp[i][j-coins[i]] + 1)
            int[] dp = new int[amount+1];
            for(int j = 1; j <= amount; j++){
                dp[j] = amount+1;
            }
            dp[0] = 0;

            for(int i = 1; i <= coins.length; i++){
                for(int j = 1; j <= amount; j++){
                    if(j >= coins[i-1]){
                        dp[j] = Math.min(dp[j-coins[i-1]] + 1, dp[j]);
                    }
                }
            }

            return dp[amount] == amount+1 ? -1 : dp[amount];
        }
    }

    // T518
    class Solution2 {
        public int change(int amount, int[] coins) {

            int[][] dp = new int[amount + 1][coins.length + 1]; // dp[i][j] : dp[i - coins[j-1]][j] + dp[i][j-1]

            // base case dp[0][j]: 总金额为0
            for (int j = 0; j <= coins.length; j++) {
                dp[0][j] = 1;
            }

            // dp[i][0] : 不选硬币
            // for ( int i = 1; i <= amount; i++ ) {
            // dp[i][0] = 0;
            // }
            for (int i = 1; i <= amount; i++) {
                for (int j = 1; j <= coins.length; j++) {
                    if (i >= coins[j - 1]) {
                        dp[i][j] += dp[i - coins[j - 1]][j];
                    }
                    dp[i][j] += dp[i][j - 1];
                }
            }

            return dp[amount][coins.length];

        }
    }

    class Solution3 {
        public int change(int amount, int[] coins) {

            int[][] dp = new int[coins.length + 1][amount + 1]; // dp[i][j] : dp[i][j-coins[i-1]] + dp[i-1][j]

            // 总金额为0, dp[i][0]: 表示不用选就可以凑成
            for (int i = 0; i <= coins.length; i++) {
                dp[i][0] = 1;
            }
            // 对于前0个硬币，凑不成任何金额，默认为0， 不用初始化
            for (int i = 1; i <= coins.length; i++) { // i表示第几个硬币，指针偏移1
                for (int j = 1; j <= amount; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= coins[i - 1]) {
                        dp[i][j] += dp[i][j - coins[i - 1]];
                    }
                }
            }

            return dp[coins.length][amount];

        }
    }

    // 压缩下
    public int change(int amount, int[] coins) {

        int[] dp = new int[amount + 1]; // dp[i][j] : dp[i][j-coins[i-1]] + dp[i-1][j]

        // 总金额为0, dp[i][0]: 表示不用选就可以凑成
        // for (int i = 0; i <= coins.length; i++ ){
        // dp[i][0] = 1;
        // }
        dp[0] = 1;
        // 对于前0个硬币，凑不成任何金额，默认为0， 不用初始化
        for (int i = 1; i <= coins.length; i++) { // i表示第几个硬币，指针偏移1
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    dp[j] += dp[j - coins[i - 1]];
                }
            }
        }

        return dp[amount];

    }

}
