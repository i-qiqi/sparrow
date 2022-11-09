package leetcode.dp;

public class T376Solution {
    public int wiggleMaxLength(int[] nums) {
        int[][] dp = new int[nums.length][2];

        int ans = 0;
        dp[0][0] = dp[0][1] = 1;
        //1 表示摆动序列最后一个元素是上升
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {//1
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = Math.max(dp[i - 1][0] + 1, dp[i - 1][1]);
            }

            if (nums[i] < nums[i - 1]) {//0
                dp[i][0] = Math.max(dp[i - 1][1] + 1, dp[i - 1][0]);
                dp[i][1] = dp[i - 1][1];
            }

            if (nums[i] == nums[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
            }
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

}
