package leetcode.hot100;

import java.util.Arrays;

public class T200Solution {
    int m, n;
    private static final int[][] d = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    // private static final int[][] d = new int[][]{ { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    // boolean[][] visited; // 采用淹海水的方法
    
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        // visited = new boolean[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if (!visited[i][j] && grid[i][j] == '1') {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {

        // if (grid[i][j] == '0' || visited[i][j])
        if (grid[i][j] == '0')
            return;

        // visited[i][j] = true;
        grid[i][j] = '0';

        for (int k = 0; k < 4; k++) {
            int u = i + d[k][0];
            int v = j + d[k][1];
            if ((u >= 0 && u < m) && (v >= 0 && v < n)) {
                dfs(grid, u, v);
            }
        }
    }

    public static void main(String[] args) {
        boolean[][] visited = new boolean[3][2];
        int[][] arr = new int[3][2];
        Arrays.fill(visited[0], true);
        System.out.println(visited[0][1]+","+arr[2][0]);
    }

}
