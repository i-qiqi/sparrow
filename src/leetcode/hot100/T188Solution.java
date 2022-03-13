package leetcode.hot100;


/**
 *T121 
 *T122
 *T123
 *T188
 *T309, t714
 */

public class T188Solution {

    //限定k次交易
    public int maxProfit188(int k, int[] prices) {
        // length 需要判断<=1
        if (prices.length <= 1)
            return 0;
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2]; // dp[i][j][k]: 在i天完成j笔交易后处于k状态的最大利润

        // i == 0
        int INF = Integer.MIN_VALUE; // 不可能的状态必须INF
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = j == 0 ? 0 : INF;
            dp[0][j][1] = j == 1 ? -prices[0] : INF;

        }

        // j == 0;
        for (int i = 0; i < len; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = INF;
        }

        int ans = 0; // 初值必须是0
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                // 卖出
                dp[i][j][0] = Math.max(dp[i - 1][j][1] != INF ? dp[i - 1][j][1] + prices[i] : INF,
                        dp[i - 1][j][0]);

                // 以买入次数作为交易次数
                dp[i][j][1] = Math.max(
                        dp[i - 1][j - 1][0] != INF ? dp[i - 1][j - 1][0] - prices[i] : INF,
                        dp[i - 1][j][1]);

                if (i == len - 1) {
                    ans = Math.max(ans, dp[i][j][1]);
                    ans = Math.max(ans, dp[i][j][0]);
                }
            }
        }

        return ans;
    }

     // K 是没有限制的
     public int maxProfit122(int[] prices) {
        // length 需要判断<=1
       if (prices.length <= 1)
           return 0;
       int len = prices.length;
       int[][] dp = new int[len][2]; // dp[i][k]: 在第i天后处于k状态的最大利润

       // i == 0
       dp[0][0] = 0;
       dp[0][1] = -prices[0];

       int ans = 0; // 初值必须是0
       for (int i = 1; i < len; i++) {
               // 卖出今天股票，或者继续不持有
               dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);

               //买入今天股票，或者继续持有昨天股票
               dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
           
       }

       return  Math.max(ans, Math.max(dp[len-1][0], dp[len-1][1]));
   }


   // 杀鸡焉用牛刀
   public int maxProfit121(int[] prices) {

    if ( prices.length <= 1 ) return 0;

    int ans = 0, minPri = prices[0];

    for ( int i = 1; i < prices.length; i++ ) {
        minPri = Math.min(minPri, prices[i]);
        ans = Math.max(ans, prices[i] - minPri);
    }

    return ans;
}
}
