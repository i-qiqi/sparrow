package leetcode.str;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T212Solution {

    class Solution {
        private class TrieNode {
            String val;
            TrieNode children[];

            public TrieNode(String v) {
                this.val = v;
                this.children = new TrieNode[26]; //小写英文字母
            }
        }

        int d[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Set<String> ans;
        // boolean visited[][];
        int m, n;
        int maxWordLen = 0;

        public List<String> findWords(char[][] board, String[] words) {
            //构造字典树
            TrieNode root = new TrieNode(null);
            for (String w : words) {
                insert(root, w);
                if (w.length() > maxWordLen) {
                    maxWordLen = w.length();
                }
            }

            m = board.length;
            n = board[0].length;
            // visited = new boolean[m][n];
            ans = new HashSet<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, root, i, j, 0);
                }
            }

            // System.out.println(ans);

            return new ArrayList<>(ans);
        }

        private void dfs(char[][] board, TrieNode p, int i, int j, int cnt) {
            if (cnt > maxWordLen) return;
            if (i < 0 || i >= m || j < 0 || j >= n) return;
            if (board[i][j] == '#') {
                return;
            }
            TrieNode q = p.children[board[i][j] - 'a'];
            if (q == null) {
                return;
            }

            if (q.val != null) {
                ans.add(q.val);
            }
            char c = board[i][j];
            board[i][j] = '#'; // 消除标记数组

            for (int k = 0; k < 4; k++) {
                int u = i + d[k][0];
                int v = j + d[k][1];
                dfs(board, q, u, v, cnt + 1);
            }

            board[i][j] = c;
        }

        /**
         * insert w into dict tree
         *
         * @param root
         * @param w
         * @return
         */
        private TrieNode insert(TrieNode root, String w) {
            TrieNode p = root;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                int idx = c - 'a';
                if (p.children[idx] == null) { // 不存在，newNode
                    p.children[idx] = new TrieNode(null);
                }
                p = p.children[idx];
            }
            p.val = w;
            return p;
        }

        private TrieNode insert(TrieNode root, String w, int start) {
            TrieNode p = root;
            char c = w.charAt(start);
            int idx = c - 'a';
            if (root.children[idx] == null) {
                p.children[idx] = new TrieNode(null);
            }

            if (start == w.length() - 1) {
                p.children[idx].val = w;
                return p.children[idx];
            }
            return insert(root.children[idx], w, start + 1);
        }
    }

    public static void main(String[] args) {
        int A = 'A';
        int a = 'a';
        int z = 'z';
        int Z = 'Z';
        System.out.printf(A + ", " + Z + ", " + a + ", " + z);
        for (int i = 90; i <= 97; i++) {
            char c = (char) i;
            System.out.println(c + " ");
        }
    }

}
