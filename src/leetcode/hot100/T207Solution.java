package leetcode.hot100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class T207Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 求每个点入度，然后找入度为0的进入
        int[] inDegrees = new int[numCourses];
        Map<Integer, List<Integer>> edges = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < prerequisites.length; i++) {
            int ai = prerequisites[i][0];
            int bi = prerequisites[i][1]; // bi -> ai
            inDegrees[ai]++;
            List<Integer> tmpList = edges.getOrDefault(bi, new ArrayList<>());
            tmpList.add(ai);
            edges.put(bi, tmpList);
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.addLast(i);
            } else {
                cnt++;
            }
        }

        while (!queue.isEmpty()) {
            Integer u = queue.removeFirst();
            List<Integer> tempList = edges.getOrDefault(u, new ArrayList<>());
            for (int v : tempList) {
                inDegrees[v]--;
                if (inDegrees[v] == 0) {
                    cnt--;
                    queue.addLast(v);
                }
            }
        }
        return cnt == 0;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for(Map.Entry<Integer, List<Integer>> e : edges.entrySet()){
            System.out.println(e.getKey());
        }
    }
}
