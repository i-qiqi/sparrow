package leetcode.hot100;

public class T10Solution {

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        T10Solution t10Solution = new T10Solution();
        t10Solution.isMatch(s, p);
    }

    /**
     * f[i][j] : 1. p[j] == '*', s[i-1] == p[j-1] => f[i][j] = f[i-1][j],匹配多个
     * s[i-1] != p[j-1] => f[i][j] = f[i-1][j-2], 匹配0个
     * 2. p[j] == '.', s[j] any c => f[i][j] = f[i-1][j-1]
     * 3. p[j] == s[i], => f[i][j] = f[i][j-1]
     * 4. p[j] != s[i], => f[i][j] = false;
     * 
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        if (m == 0 || n == 0)
            return false;

        boolean[][] dp = new boolean[m][n];

        // i = 0的情况
        // 第1行
        dp[0][0] = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.' ? true : false;
        boolean flag = true;
        for (int i = 1; i < n; i++) {
            dp[0][i] = false;
            if (p.charAt(i) != '*' && p.charAt(i - 1) != '*') {
                flag = false;
            }
            if (p.charAt(i) == '*') {
                dp[0][i] = dp[0][i - 1];
                if (i >= 2) {
                    dp[0][i] = dp[0][i] || dp[0][i - 2];
                }
            } else if (p.charAt(i) == s.charAt(0) || p.charAt(i) == '.') {
                dp[0][i] = flag;
            }
        }

        // 第1列
        for (int i = 1; i < m; i++) {
            dp[i][0] = false;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 赋默认值
                dp[i][j] = false;
                if (p.charAt(j) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // 匹配2+个,也可选择仅匹配一个
                    }
                    if (j >= 2) {
                        dp[i][j] = dp[i][j] || dp[i][j - 2];
                    }
                } else {
                    // 正常字符
                    dp[i][j] = p.charAt(j) == s.charAt(i) ? dp[i - 1][j - 1] : false;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
