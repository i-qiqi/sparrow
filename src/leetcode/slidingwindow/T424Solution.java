package leetcode.slidingwindow;

public class T424Solution {
    /**
     * 424. 替换后的最长重复字符
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] window = new int[26];
        int left = 0, right = 0;
        int ans = 0;
        int maxCharCnt = 0;
        int i = 0, j = 0;
        while (right < s.length()) {
            j = s.charAt(right) - 'A';
            window[j]++;
            maxCharCnt = Math.max(maxCharCnt, window[j]);
            right++;
            while (right - left - maxCharCnt > k) { //剩余不同的字符超过k个
                i = s.charAt(left) - 'A';
                window[i]--;
                left++;
            }
            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}
