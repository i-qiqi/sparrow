package leetcode.top200;

public class T300Solution {

    // [10,9,2,5,6,3,7,101,18]
    /**
     * 0: 10
     * 1: 9
     * 2: 2
     * 3: 2 5
     * 4: 2 5 6
     * 5: 2 3 6 end = 1
     * 6: 2 3 6 7
     * 
     * 
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];

        dp[0] = 1;

        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public int lengthOfLISWithBinarySearch(int[] nums) {

        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int end = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tails[end]) {
                tails[++end] = nums[i];
            } else {
                // 二分查找 第一个不小于nums[i]的数
                int k = findFirstNotLessThanNum(tails, end, nums[i]);
                // int k = findFirstNotLessThanNum(tails, end+1, nums[i]); 也可
                // 替换
                tails[k] = nums[i];
            }
        }
        return end + 1;
    }

    // 二分查找边界处理
    // 1 2 3 3 4 : 3
    // 1 2 3 5 6 7 : 4
    // 1 2 3 4 : 1
    // Not less than >= 
    private int findFirstNotLessThanNum(int[] tails, int n, int targetVal) {

        int left = 0;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (tails[mid] < targetVal) {
                left++;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
