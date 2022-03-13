package leetcode.top200;

public class T695Solution {
    int m, n;
    private static final int[][] d = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] area = new int[1];
                dfs(grid, i, j, area);
                ans = Math.max(ans, area[0]);
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int i, int j, int[] area) {
        if (grid[i][j] == 0)
            return;

        grid[i][j] = 0;
        area[0]++;

        for (int k = 0; k < 4; k++) {
            int u = i + d[k][0];
            int v = j + d[k][1];
            if ((u >= 0 && u < m) && (v >= 0 && v < n)) {
                dfs(grid, u, v,area);
            }
        }
    }
}
