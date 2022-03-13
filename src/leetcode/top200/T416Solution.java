package leetcode.top200;

import java.util.Arrays;

//子集分割
public class T416Solution {

    // T416
    class Solution {
        public boolean canPartition(int[] nums) {
            boolean ans = false;
            // 寻找和为sum/2
            int total = Arrays.stream(nums).sum();

            if ((total & 1) == 1) { // 奇数
                return false;
            }

            // 在nums 种寻找和为total/2的子集
            // dp[i][j] : 前i个元素中是否存在和为j的集合， dp[i-1][j] | dp[i-1][j - nums[i-1]]
            boolean[][] dp = new boolean[nums.length + 1][total / 2 + 1];

            // basecase
            for (int i = 0; i <= nums.length; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i <= nums.length; i++) {
                for (int j = 1; j <= total / 2; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= nums[i - 1]) {
                        dp[i][j] |= dp[i - 1][j - nums[i - 1]];
                    }
                }
            }

            return dp[nums.length][total / 2];
        }
    }

    // T698
    class Solution1 {
        public boolean canPartitionKSubsets(int[] nums, int k) {

            boolean ans = false;

            return ans;
        }
    }
}
