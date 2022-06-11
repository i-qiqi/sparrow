package leetcode.subsequence;

import java.util.ArrayList;

/***
 * S， [p1, p2, ..., pk]
 *
 * S长度n, p的平均长度m
 *
 *  1 二分法: O(n) + k * log(n)
 *  2  dp: 26*O(n) + k * O(m)
 *  3 分桶法: O(n) + k * O(m)
 *
 *  n -> 无穷, m -> 无穷大， 法1更佳
 *  前半部分都是固定耗时, log(n) < log(5 * 10000) < log(64 * 1024) = 16 < 20 和 m 差不多量级
 *
 *  题目的数据
 *  1 <= s.length <= 5 * 104
 *  1 <= words.length <= 5000
 *  1 <= words[i].length <= 50
 * words[i]和 s都只由小写字母组成。
 *
 *  EF(1) < EF(2) < EF(3)
 *
 */
public class T792Solution {
    /**
     * 270ms 左右， leetcode 原写法180ms 左右
     *
     * 耗时在dp初始化部分
     */
    class Solution {
        public int numMatchingSubseq(String s, String[] words) {
            int[][] dp = new int[s.length()][26];

            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    if (s.charAt(i) == j + 'a') {
                        dp[i][j] = i;
                    } else if (i < s.length() - 1) {
                        dp[i][j] = dp[i + 1][j];
                    } else {
                        dp[i][j] = -1; //赋初值，表示s[i]后面不存在j+'a'字符
                    }
                }
            }

            int ans = 0;
            for (String w : words) {
                if (isSubsequenceOfS(w, dp)) {
                    ans++;
                }
            }

            return ans;
        }

        private boolean isSubsequenceOfS(String p, int[][] dp) {
            int i = 0, k = 0;
            for (k = 0; k < p.length() && i < dp.length; k++) {
                char c = p.charAt(k);
                if (dp[i][c - 'a'] == -1) {
                    return false;
                } else {
                    //存在比较下一个字符
                    i = dp[i][c - 'a'] + 1;
                }
            }
            //s 可能先匹配完
            return k == p.length();
        }
    }

    /**
     * 超时 87ms 左右
     * 二分查找
     */
    class Solution1 {
        public int numMatchingSubseq(String s, String[] words) {
            int ans = 0;
            // 对 t 进行预处理
            ArrayList<Integer>[] index = new ArrayList[256];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (index[c] == null)
                    index[c] = new ArrayList<>();
                index[c].add(i);
            }

            for(String w : words){
                if(isSubsequence(w, s, index)){
                    ans++;
                }
            }

            return ans;
        }

        boolean isSubsequence(String s, String t, ArrayList<Integer>[] index) {
            int m = s.length(), n = t.length();

            // 串 t 上的指针
            int j = 0;
            // 借助 index 查找 s[i]
            for (int i = 0; i < m; i++) {
                char c = s.charAt(i);
                // 整个 t 压根儿没有字符 c
                if (index[c] == null) return false;
                int pos = left_bound(index[c], j);
                // 二分搜索区间中没有找到字符 c
                if (pos == index[c].size()) return false;
                // 向前移动指针 j
                j = index[c].get(pos) + 1;
            }
            return true;
        }
        // 查找左侧边界的二分查找
        int left_bound(ArrayList<Integer> arr, int tar) {
            int lo = 0, hi = arr.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tar > arr.get(mid)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }

    }

}
