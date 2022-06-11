package leetcode.meetingroom;

import java.util.*;
import java.util.stream.Collectors;

public class T986Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int[][] mergeList = new int[firstList.length + secondList.length][2];
        System.arraycopy(firstList, 0, mergeList, 0, firstList.length);
        System.arraycopy(secondList, 0, mergeList, firstList.length, secondList.length);

        Arrays.sort(mergeList, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return 0;
            } else if(o1[0] > o2[0]) {
                return 1;
            } else {
                return -1;
            }
        });

        int maxEnd = -1;
        int cnt = 0;
        for ( int[] interval : mergeList){
            // System.out.println(interval[0] + " , " + interval[1]);
            if(interval[0] <= maxEnd){ // 相交
                mergeList[cnt][0] = interval[0];
                mergeList[cnt][1] = Math.min(interval[1], maxEnd);
                cnt++;
            }

            maxEnd = Math.max(interval[1], maxEnd);
        }

        int[][] res = new int[cnt][2];
        System.arraycopy(mergeList, 0, res, 0, cnt);

        return res;
    }
    //更慢一点
    public int[][] intervalIntersection1(int[][] firstList, int[][] secondList) {
        List<int[]> mergeList = new LinkedList<>();
        mergeList.addAll(Arrays.stream(firstList).collect(Collectors.toList()));
        mergeList.addAll(Arrays.stream(secondList).collect(Collectors.toList()));
        mergeList.sort((o1, o2) -> {
            if(o1[0] == o2[0]) {
                return 0;
            } else if(o1[0] > o2[0]) {
                return 1;
            } else {
                return -1;
            }
        });

        int maxEnd = -1;
        int cnt = 0;
        List<int[]> ans = new ArrayList<>();
        for ( int[] interval : mergeList){
            if(interval[0] <= maxEnd){ // 相交
                ans.add(new int[] {interval[0], Math.min(interval[1], maxEnd)});
                cnt++;
            }

            maxEnd = Math.max(interval[1], maxEnd);
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        int[][] firstList = {{1,2}};
        int[][] secondList= {{1,2}, {3,4}};
        list.addAll(Arrays.stream(firstList).collect(Collectors.toList()));
        list.addAll(Arrays.stream(secondList).collect(Collectors.toList()));

        list.forEach(e -> System.out.println(e[0] + " , " + e[1]));
        list.add(new int[]{1,2});
        int[][] arr = new int[1][];
        System.out.println(arr.getClass());
        T986Solution[] solutions = new T986Solution[2];
        System.out.println(solutions.getClass());
        int[][] res = list.toArray(arr); // 把list数据copy到arr 中
        System.out.println(list);

        Map<String, Object> mp = new HashMap<>();
        mp.put(null, 1);
        mp.put(null, null);
        System.out.println(mp.toString());
    }
}
