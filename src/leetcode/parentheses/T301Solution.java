package leetcode.parentheses;

import java.util.*;

public class T301Solution {
    class Solution {
        LinkedList<String> res;
        public static final String NULL = "*";

        public List<String> removeInvalidParentheses(String s) {
            int lr = 0, rr = 0;
            char[] sarr = s.toCharArray();

            res = new LinkedList<>();

            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] == '(') {
                    lr++;
                }
                if (sarr[i] == ')') {
                    if (lr == 0) {
                        rr++;
                    } else {
                        lr--;
                    }
                }
            }

            dfs(sarr, 0, lr, rr);
            return res;
        }

        public void dfs(char[] s, int st, int lr, int rr) {
            if (lr == 0 && rr == 0) {
                String ans = isValid(s);
                if (!NULL.equals(ans)) {
                    res.add(ans);
                }
                return;
            }

            for (int i = st; i < s.length; i++) {
                //去重
                if (s[i] != '(' && s[i] != ')') continue;
                if (i != st && s[i] == s[i - 1]) continue;

                // if(lr + rr > s.length - i) return ;
                char c = s[i];
                if (lr > 0 && s[i] == '(') {
                    s[i] = '#';
                    dfs(s, i + 1, lr - 1, rr);
                    s[i] = c;
                }

                if (rr > 0 && s[i] == ')') {
                    s[i] = '#';
                    dfs(s, i + 1, lr, rr - 1);
                    s[i] = c;
                }

            }
        }

        public String isValid(char[] s) {
            int lcnt = 0, rcnt = 0;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < s.length; i++) {
                if (s[i] == '(') {
                    lcnt++;
                }
                if (s[i] == ')') {
                    rcnt++;
                }
                if (rcnt > lcnt) return NULL;
                if (s[i] != '#') {
                    sb.append(s[i]);
                }
            }

            return lcnt == rcnt ? sb.toString() : NULL;
        }
    }

    class SolutionBFS {
        LinkedList<String> res;

        public List<String> removeInvalidParentheses(String s) {
            int lr = 0, rr = 0;
            Queue<String> q = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            res = new LinkedList<>();

            q.offer(s);
            boolean isFound = false;
            int size = 1;
            while (size > 0) {
                int levelSize = 0;
                for (int i = 0; i < size; i++) {
                    String t = q.poll();
                    if (isValid(t)) {
                        res.add(t);
                        isFound = true;
                    }
                    for (int j = 0; j < t.length(); j++) {
                        if (j > 0 && t.charAt(j) == t.charAt(j - 1)) continue;
                        char c = t.charAt(j);
                        if (c == '(' || c == ')') {
                            String schild = t.substring(0, j) + t.substring(j + 1);
                            if (visited.contains(schild)) continue;
                            q.offer(schild);
                            visited.add(schild);
                            levelSize++;
                        }
                    }
                }
                size = levelSize;

                if (isFound) {
                    return res;
                }
            }

            return res;
        }

        public boolean isValid(String s) {
            int lcnt = 0, rcnt = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    lcnt++;
                }
                if (c == ')') {
                    rcnt++;
                }
                if (rcnt > lcnt) return false;
            }

            return lcnt == rcnt;
        }
    }
}
