package leetcode.str;

import java.util.*;

public class T140Solution {
    class Solution {
        LinkedList<String> ans;
        List<String> words;

        public List<String> wordBreak(String s, List<String> wordDict) {
            ans = new LinkedList<>();
            words = wordDict;
            dfs(s, 0, "", 0);
            return ans;
        }

        private void dfs(String s, int start, String path, int cnt) {
            if (cnt > s.length()) {
                return;
            }

            if (start == s.length()) {
                if (cnt == s.length()) {
                    ans.addLast(path.toString().trim());
                }
                return;
            }

            for (String w : words) {
                dfs(s, start + w.length(), path + w + " ", cnt + w.length());
            }
        }
    }

    class Solution1 {
        List<String> words;
        final String SEP = " ";

        public List<String> wordBreak(String s, List<String> wordDict) {
            words = wordDict;
            LinkedList ans = new LinkedList<>();
            dfs(s, 0, new LinkedList<>(), ans);
            return ans;
        }

        private void dfs(String s, int start, LinkedList<String> path, LinkedList<String> pathList) {


            if (start == s.length()) {
                pathList.addLast(String.join(SEP, path));
                return;
            }

            Set<String> tempAns = new HashSet<>();
            for (String w : words) {
                if (start + w.length() <= s.length() && w.equals(s.substring(start, start + w.length()))) {
                    path.addLast(w);
                    dfs(s, start + w.length(), path, pathList);
                    path.removeLast();
                }
            }

        }
    }

    class Solution2 {
        List<String> words;
        final String SEP = " ";

        Map<Integer, LinkedList<String>> memo;

        public List<String> wordBreak(String s, List<String> wordDict) {
            words = wordDict;
            memo = new HashMap<>();
            LinkedList ans = new LinkedList<>();
            dfs(s, s.length(), ans);
            return memo.get(s.length());
        }

        private void dfs(String s, int end, LinkedList<String> pathList) {

            if (memo.containsKey(end)) {
                pathList.addAll(memo.get(end));
                return;
            }

            if (end == 0) {
                pathList.addLast("");
                return;
            }

            for (String w : words) {
                if (end - w.length() >= 0 && w.equals(s.substring(end - w.length(), end))) {
                    LinkedList<String> tempPathList = new LinkedList<>();
                    dfs(s, end - w.length(), tempPathList);
                    for (String tempPath : tempPathList) {
                        if (tempPath.length() > 0) {
                            pathList.addLast(tempPath + SEP + w);
                        } else {
                            pathList.addLast(w);
                        }
                    }
                }
            }

            memo.put(end, pathList);
        }
    }

    /**
     * good , 我自己想的
     */
    class Solution3 {
        LinkedList<String> res;

        public List<String> wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            Map<Integer, List<Integer>> g = new HashMap<>();
            res = new LinkedList<>();
            for (int i = 1; i <= s.length(); i++) {
                for (String w : wordDict) {
                    if (i >= w.length() && w.equals(s.substring(i - w.length(), i)) && dp[i - w.length()]) {
                        List<Integer> preList = g.getOrDefault(i, new ArrayList<>());
                        preList.add(i - w.length());
                        g.put(i, preList);
                        dp[i] = true;
                    }
                }
            }
            dfs(g, s.length(), s, new LinkedList<>());
            return res;
        }

        public void dfs(Map<Integer, List<Integer>> g, int i, String s, LinkedList<String> path) {
            if (i == 0) {
                String ans = String.join(" ", path);
                res.addLast(ans);
                return;
            }
            List<Integer> preList = g.getOrDefault(i, new ArrayList());
            for (int k = 0; k < preList.size(); k++) {
                int prei = preList.get(k);
                path.addFirst(s.substring(prei, i));
                dfs(g, prei, s, path);
                path.removeFirst();
            }
        }
    }


    public static void main(String[] args) {
        String[] s = {"a"};
        String[] s1 = {""};
        String[] s2 = {"", "a"};

        System.out.println(String.join(" ", s));
        System.out.println(String.join(" ", s1));
        System.out.println(String.join(" ", s2));
    }
}
