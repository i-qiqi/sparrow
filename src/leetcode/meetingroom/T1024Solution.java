package leetcode.meetingroom;

import java.util.Arrays;

public class T1024Solution {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                return 0;
            }
        });

        int curEnd = 0, nextEnd = 0;
        int ans = 0;
        int i = 0;
        while (i < clips.length) {
            // 找满足start < nextEnd的end最大的区间
            curEnd = nextEnd;
            boolean isFind = false;
            while (i < clips.length && clips[i][0] <= curEnd) {
                if (clips[i][1] > nextEnd) {
                    nextEnd = clips[i][1];
                    isFind = true;
                }
                i++;
            }

            if (!isFind) {
                //不用找了，找不到可以连接的最大边界
                break;
            } else {
                ans++;
                if (nextEnd >= time) {
                    return ans;
                }
            }
        }

        return -1;
    }

    //相同条件双循环
    public int videoStitchingFromLabladong(int[][] clips, int time) {
        if (time == 0) return 0;
        // 按起点升序排列，起点相同的降序排列
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        // 记录选择的短视频个数
        int res = 0;

        int curEnd = 0, nextEnd = 0;
        int i = 0, n = clips.length;
        while (i < n && clips[i][0] <= curEnd) {
            // 在第 res 个视频的区间内贪心选择下一个视频
            while (i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            // 找到下一个视频，更新 curEnd
            res++;
            curEnd = nextEnd;
            if (curEnd >= time) {
                // 已经可以拼出区间 [0, time]
                return res;
            }
        }
        // 无法连续拼出区间 [0, time]
        return -1;
    }

}
