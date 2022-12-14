package leetcode.buckets;

import java.util.Arrays;

public class T164Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;

        if (n < 2) {
            return 0;
        }

        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();

        int d = Math.max(1, (maxVal - minVal)) / (n - 1);
        int buketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[buketSize][2];

        for (int i = 0; i < buketSize; ++i) {
            Arrays.fill(bucket[i], -1);
        }

        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int prev = -1;

        for (int i = 0; i < buketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }

            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }

            prev = i;
        }

        return ret;
    }
}
