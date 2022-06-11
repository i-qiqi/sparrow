package leetcode.arr;

/**
 * @最大子数组和
 */
public class T53Solution {
    class Solution {
        public int maxSubArray(int[] nums) {
            int maxSum = 0;
            int ans = Integer.MIN_VALUE;
            for (int num : nums) {
                maxSum = Math.max(num, num + maxSum);
                ans = Math.max(ans, maxSum);
            }
            return ans;

        }
    }
}
