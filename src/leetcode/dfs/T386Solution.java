package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class T386Solution {
    List<Integer> ans;

    public List<Integer> lexicalOrder(int n) {
        ans = new ArrayList<>();
        for (int i = 1; i <= 9 && i <= n; i++) {
            dfs(n, i);
        }

        return ans;
    }

    public void dfs(int n, int num) {
        if (num > n) {
            return;
        }
        if (num >= 1) {
            ans.add(num);
        }

        for (int i = 0; i <= 9; i++) {
            dfs(n, num * 10 + i);
        }
    }
}
