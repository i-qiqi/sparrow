package leetcode.hot100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class T210Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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

        int[] path = new int[numCourses];
        int k = 0;
        while (!queue.isEmpty()) {
            Integer u = queue.removeFirst();
            path[k++] = u;
            List<Integer> tempList = edges.getOrDefault(u, new ArrayList<>());
            for (int v : tempList) {
                inDegrees[v]--;
                if (inDegrees[v] == 0) {
                    cnt--;
                    queue.addLast(v);
                }
            }
        }

        return cnt == 0 ? path : new int[] {};
    }
}
