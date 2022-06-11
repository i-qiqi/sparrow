package leetcode.random;

import java.util.Random;

public class T528Solution {
    class Solution {
        int[] preSum;
        Random rand;

        public Solution(int[] w) {
            this.rand = new Random();
            preSum = new int[w.length];
            preSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                preSum[i] = preSum[i - 1] + w[i];
            }
        }

        public int pickIndex() {
            int target = rand.nextInt(preSum[preSum.length - 1]) + 1;

            //二分查找比右边界;
            return rightBound(preSum, target);
        }

        public int rightBound(int[] nums, int target) {
            if (nums.length == 0) return -1;
            //边界情况
            if (target > nums[nums.length - 1]) return -1;

            if (target < nums[0]) return 0;

            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (nums[mid] == target) {
                    low = mid;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                }
            }

            if (nums[low] != target) {
                return nums[low] > target ? low : low + 1;
            }

            return low;
        }
    }

    class Solution1 {
        int[] w;
        Random rand;

        public Solution1(int[] w) {
            this.w = w;
            this.rand = new Random();
        }

        public int pickIndex() {
            int[] preSum = new int[w.length];
            preSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                preSum[i] = preSum[i - 1] + w[i];
            }

            int target = rand.nextInt(preSum[w.length - 1]) + 1;

            //二分查找比右边界;
            return leftBound(preSum, target);
        }

        public int leftBound(int[] nums, int target) {
            if (nums.length == 0) return -1;
            //边界情况
            if (target > nums[nums.length - 1]) return -1;

            if (target < nums[0]) return 0;

            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    high = mid;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                }
            }

            return nums[low] < target ? low + 1 : low;
        }
    }

}
