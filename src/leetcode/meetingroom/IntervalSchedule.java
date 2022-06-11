package leetcode.meetingroom;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 举个例子，intvs=[[1,3],[2,4],[3,6]]，这些区间最多有两个区间互不相交，即[[1,3],[3,6]]，你的算法应该返回 2。注意边界相同并不算相交。
 * 求最多有几个区间互不相交
 */
public class IntervalSchedule {
    int intervalScheduling(int[][] intvs) {
        if (intvs == null || intvs.length == 0) return 0;
        Arrays.sort(intvs, (o1, o2) -> o1[0] - o2[0]);

        int preEnd = intvs[0][0];
        int ans = 0;
        for (int[] interval : intvs) {
            if (interval[0] >= preEnd) {
                ans++;
                preEnd = interval[1];
            } else {
                if (interval[1] <= preEnd) {
                    preEnd = interval[1];
                }
            }
        }
        return ans;
    }

    int intervalSchedulingSortOnEnd(int[][] intvs) {
        if (intvs == null || intvs.length == 0) return 0;

        Arrays.sort(intvs, (o1, o2) -> o1[1] > o2[1] ? 1 : o1[1] == o2[1] ? 0 : -1);

        int minEnd = intvs[0][1];
        int ans = 1;

        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= minEnd) {
                ans++;
                minEnd = interval[1];
            }
        }

        return ans;
    }

    public static void main1(String[] args) {
        IntervalSchedule intervalSchedule = new IntervalSchedule();
        int[][] intvs = {{1, 3}, {2, 4}, {3, 6}};
        System.out.println(intervalSchedule.intervalScheduling(intvs));
        int[][] intvs1 = {{1, 3}, {1, 2}, {1, 4}};
        System.out.println(intervalSchedule.intervalScheduling(intvs1));
        int[][] intvs2 = {{1, 2}, {2, 3}, {2, 4}};
        System.out.println(intervalSchedule.intervalScheduling(intvs2));
        int[][] intvs3 = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(intervalSchedule.intervalScheduling(intvs3));
        int[][] intvs4 = {{1, 2}};
        System.out.println(intervalSchedule.intervalScheduling(intvs4));


//        Arrays.sort(intvs, (o1, o2) -> {
//            if (o1[0] == o2[0]) {
//                return o1[1] - o2[1];
//            } else {
//                return o1[0] - o2[0];
//            }
//        });
    }

    public static void main(String[] args) {
        IntervalSchedule intervalSchedule = new IntervalSchedule();

//        int[][] intvs =  {{-2147483646,-2147483645},{2147483646,2147483647}};
        int[][] intvs = {{0, 3}, {1, 3}, {2, 3},{3, 1}};

        //o1 前一个元素， o2 后一个元素， 返回1 交换, 返回-1不交换, 0表示相等，啥也不做
        Arrays.sort(intvs, (o1, o2) -> o1[1] > o2[1] ? 1 : o1[1] == o2[1] ? 0 : -1);
        Arrays.stream(intvs).forEach(e -> System.out.println(e[0] + " : " + e[1]));
    }

}
