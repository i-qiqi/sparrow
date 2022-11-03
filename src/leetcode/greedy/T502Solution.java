package leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * IPO
 */
public class T502Solution {
    class Solution {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            int n = profits.length;
            int curr = 0;
            int[][] projects = new int[n][2];

            for (int i = 0; i < n; i++) {
                projects[i][0] = capital[i];
                projects[i][1] = profits[i];
            }

            Arrays.sort(projects, (a, b) -> a[0] - b[0]);

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            for (int i = 0; i < k; i++) {
                while (curr < n && projects[curr][0] <= w) {
                    pq.offer(projects[curr][1]);
                    curr++;
                }

                if (!pq.isEmpty()) {
                    w += pq.poll();
                } else {
                    break;
                }
            }

            return w;
        }
    }
}
