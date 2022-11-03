package leetcode.dp;

public class T375Solution {

    public final int inf = Integer.MAX_VALUE;
    public int getMoneyAmount(int n) {
        int[][] f = new int[n+2][n+2]; // 避免越界，外围多圈一层
        //k + f[i][k-1], k + f[i+1][j]
        // f[i][j] = min{k + max(f[i][k-1], f[k+1][j])}, i <= k <= j
        // f[i][i] = 0, 不用算
        for ( int i = n - 1 ; i > 0 ; i-- ) {     //f[n][n] = 0 , 默认的, i 从n 开始即可
            for ( int j = i + 1 ; j <= n ; j++ ) {    //f[i][i] = 0, 从i+1 开始即可
                f[i][j] = inf;
                for ( int k = i ; k <= j ; k++ ) {
                    f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k-1], f[k+1][j])); //f[i][j] = 0 , i > j, 在对角线下方
                }
            }
        }

        return f[1][n];
    }
}
