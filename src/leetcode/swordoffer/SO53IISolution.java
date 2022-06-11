package leetcode.swordoffer;

/**
 * @找缺失数字
 */
public class SO53IISolution {
    class Solution {
        public int missingNumber(int[] nums) {
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (mid == nums[mid]) {
                    low = mid + 1;
                } else if (mid > nums[mid]) {
                    //不存在的情况
                } else if (mid < nums[mid]) {
                    high = high - 1;
                }
            }

            return low;
        }
    }
}
