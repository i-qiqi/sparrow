package leetcode.graph.bfs;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * public static int compare(int x, int y) {
 * return (x < y) ? -1 : ((x == y) ? 0 : 1);
 * }
 */
public class T407Solution {
    class Solution {
        public int trapRainWater(int[][] heightMap) {
            int m = heightMap.length, n = heightMap[0].length;
            boolean[][] visited = new boolean[m][n];

            //前k大，小顶堆， 默认小顶堆
            //前k小，大顶堆
            PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        pq.offer(new Pair(i, j, heightMap[i][j]));
                        visited[i][j] = true;
                    }
                }
            }

            int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int ret = 0;
            int u, v, w;
            while (!pq.isEmpty()) {
                Pair p = pq.poll();
                for (int k = 0; k < 4; k++) {
                    u = p.u + d[k][0];
                    v = p.v + d[k][1];
                    if (u >= 0 && u < m && v >= 0 && v < n && !visited[u][v]) {
                        w = heightMap[u][v];
                        if (p.w > w) {
                            ret += p.w - w;
                        }
                        //需要更新边界
                        pq.offer(new Pair(u, v, Math.max(p.w, w)));
                        visited[u][v] = true;
                    }
                }
            }

            return ret;
        }

        class Pair {
            int u, v, w;

            public Pair(int u, int v, int w) {
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }
    }
}
