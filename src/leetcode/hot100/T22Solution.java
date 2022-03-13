package leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * L R
 * L R L R
 * 
 * 
 * ))(
 * 
 * 
 * 
 * @param path
 * @param n
 */

public class T22Solution {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs("", 0, 0, 2 * n);
        return ans;
    }

    private void dfs(String path, int leftCnt, int rightCnt, int n) {
        // System.out.println(path.toString());
        if (rightCnt > leftCnt)
            return;

        if (leftCnt > n / 2)
            return;

        if (path.length() == n) {
            System.out.println(path.toString());
            ans.add(path.toString());
            return;
        }

        // 优先放'('
        dfs(path + "(", leftCnt + 1, rightCnt, n);
        dfs(path + ")", leftCnt, rightCnt + 1, n);
    }

    public static void main(String[] args) {
        T22Solution solution = new T22Solution();
        solution.generateParenthesis(3);
    }
}
