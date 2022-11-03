package leetcode.bisearch;

import java.util.Arrays;

public class T436Solution {

    public int[] findRightInterval(int[][] its) {
        int n = its.length;
        int[][] clone = new int[n][2];

        for (int i = 0; i < n; i++) {
            clone[i] = new int[]{its[i][0], i};
        }

        Arrays.sort(clone, (a, b) -> a[0] - b[0]);

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int l = 0, r = n - 1;

            while (l < r) {
                int mid = l + r >> 1;
                if (clone[mid][0] >= its[i][1]) r = mid;
                else l = mid + 1;
            }

            ans[i] = clone[r][0] >= its[i][1] ? clone[r][1] : -1;
        }
        return ans;
    }
}
