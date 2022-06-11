package leetcode.meetingroom;

import java.util.Arrays;

public class T1228Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // 首先来个排序, start asc, end desc
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] > o2[1]) {
                    return -1;
                } else if (o1[1] < o2[1]) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (o1[0] > o2[0]) {
                return 1;
            } else {
                return -1;
            }
        });

        int maxEnd = 0;
        int ans = 0;
        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                ans++;
                maxEnd = interval[1];
            }
        }
        return ans;
    }
}
