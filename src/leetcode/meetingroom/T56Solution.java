package leetcode.meetingroom;

import java.util.Arrays;

public class T56Solution {

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return 0;
            } else if(o1[0] > o2[0]){
                return 1;
            } else {
                return -1;
            }
        });


        int cnt = 0;

        int nextStart = intervals[0][0], nextEnd = intervals[0][0];

        for(int[] interval : intervals){
            if(interval[0] <= nextEnd) { //重叠
                if(interval[1] > nextEnd) {
                    nextEnd = interval[1];
                }
            } else {
                intervals[cnt][0] = nextStart;
                intervals[cnt][1] = nextEnd;
                nextStart = interval[0];
                nextEnd = interval[1];
                cnt++;
            }
        }

        intervals[cnt][0] = nextStart;
        intervals[cnt][1] = nextEnd;

        int[][] res = new int[cnt+1][2];
        System.arraycopy(intervals, 0, res, 0, res.length);
        return res;
    }
}
