package leetcode.greedy;

public class T330Solution {
    class Solution {
        public int minPatches(int[] nums, int n) {
            if (n < 0) return 0;

            long preSum = 0;

            int ret = 0;

            for (int i = 0; i < nums.length && preSum < n; i++) {
                int v = nums[i];

                while (v > preSum + 1 && preSum < n) {
                    ret += 1;
                    preSum += preSum + 1;
                }
                preSum += v;
            }

            if (preSum < n) {
                while (preSum < n) {
                    ret += 1;
                    preSum += (preSum + 1);
                }
            }

            return ret;
        }
    }
}
