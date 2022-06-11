package leetcode.str;

import java.util.LinkedList;
import java.util.List;

public class T131Solution {
    class Solution {
        boolean isPalindrome[][];
        LinkedList<List<String>> ans;

        public List<List<String>> partition(String s) {
            isPalindrome = new boolean[s.length()][s.length()];

            // 预处理标记数组
            for (int i = 0; i < s.length(); i++) {
                centerExtend(s, i, i);// 奇数
                centerExtend(s, i, i + 1);//偶数
            }
            ans = new LinkedList<>();
            dfs(s, 0, new LinkedList<>());
            return ans;
        }

        private void dfs(String s, int start, LinkedList<String> path) {
            if (start == s.length()) {
                ans.addLast(new LinkedList<>(path));
                return;
            }
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome[start][i]) {
                    path.addLast(s.substring(start, i + 1));
                    dfs(s, i + 1, path);
                    path.removeLast();
                }
            }
        }

        private void centerExtend(String s, int i, int j) {
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                isPalindrome[i][j] = true;
                i--;
                j++;
            }
        }
    }
}
