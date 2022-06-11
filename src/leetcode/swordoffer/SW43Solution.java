package leetcode.swordoffer;


/**
 * 核心思想: 贡献法
 * (left) d (right)
 * 1 d == 0: [0...left-1] 1 [0...Base(d)] => left * base
 * 2 d == 1: [0...left-1] 1 [0...Base(d)] + [left] 1 [0...right]  => left * base + right + 1
 * 3 d > 1:  [0...left] 1 [0...Base[d]] => (left + 1) * base
 * <p>
 * left = n: left = left / 10
 * right = 1, base = 1: right = d * base + right
 * base = 1: base = base * 10
 */
public class SW43Solution {
    class Solution {
        /**
         * so hard!!!
         * @param n
         * @return
         */
        public int countDigitOne(int n) {
            int i = 0;
            int ans = 0;
            int left = n;
            int right = 0;
            int base = 1;
            while (n > 0) {
                int d = n % 10;
                left = left / 10;
                if (d == 0) {
                    ans += left * base;
                } else if (d == 1) {
                    ans += left * base + right + 1;
                } else if (d > 1) {
                    ans += (left + 1) * base;
                }
                right = d * base + right;
                base = base * 10;
                n = left;
            }

            return ans;
        }
    }
}
