package leetcode.hot100;

import java.util.HashMap;
import java.util.List;

public class T139Solution {
    HashMap<Integer, Boolean> mem = null;

    public boolean wordBreak(String s, List<String> wordDict) {
        mem = new HashMap<>();
        return traceback(s, 0, wordDict);
    }

    private boolean traceback(String s, int i, List<String> wordDict) {

        if (mem.containsKey(i)) {
            return mem.get(i);
        }

        if (i == s.length()) {
            return true;
        }

        boolean res = false;

        for (int k = 0; k < wordDict.size(); k++) {
            String w = wordDict.get(k);
            if (i + w.length() <= s.length() && s.substring(i, i + w.length()).equals(w)) {
                res = res || traceback(s, i + w.length(), wordDict);
                mem.put(i + w.length(), res);
            }
        }

        return res;
    }

    HashMap<Integer, List<String>> ansMem = new HashMap<>();

    //连接词
    public List<String> wordBreakII(String s, List<String> wordDict) {
        return null;
    }

    public void tracebackII() {

    }

    class Solution1 {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (String w : wordDict) {
                    if (i >= w.length() && w.equals(s.substring(i - w.length(), i))) {
                        dp[i] = dp[i] || dp[i - w.length()];
                    }
                }
            }
            return dp[s.length()];
        }
    }


}
