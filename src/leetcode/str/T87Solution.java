package leetcode.str;

public class T87Solution {
    public boolean isScramble(String s1, String s2){
        if(s1.equals(s2)) return true;

        if(s1.length() != s2.length()) return false;
        int n = s1.length();
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        //f[i][j][len] : s1从i开始，s2从j开始，后面长度为len的字符串是否能形成 扰乱字符串。
        boolean[][][] f = new boolean[n][n][n+1];

        //先处理长度为1的情况
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                f[i][j][1] = cs1[i] == cs2[j];
            }
        }

        for(int len = 2; len <= n; len++){
            for(int i = 0; i <= n - len; i++){
                for(int j = 0; j <= n - len; j++){
                    for(int k = 1; k < len; k++){
                        boolean a = f[i][j][k] && f[i+k][j+k][len-k];
                        boolean b = f[i][j+len-k][k] && f[i+k][j][len - k];
                        if(a || b){
                            f[i][j][len] = true;
                        }
                    }
                }
            }
        }

        return f[0][0][n];
    }
}

