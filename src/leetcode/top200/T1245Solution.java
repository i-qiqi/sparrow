package leetcode.top200;

import java.util.concurrent.ThreadPoolExecutor;

public class T1245Solution {
    int[][] d = new int[][] { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
    int m, n;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        // 遍历左右边
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);

        }
        // 遍历上下边
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);

        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int i, int j) {

        if (grid[i][j] == 1)
            return;

        grid[i][j] = 1;

        for (int k = 0; k < 4; k++) {
            int u = i + d[k][0];
            int v = j + d[k][1];
            if ((u >= 0 && u < m) && (v >= 0 && v < n)) {
                dfs(grid, u, v);
            }
        }


    }
}