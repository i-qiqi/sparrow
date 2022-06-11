package leetcode.swordoffer;

/**
 * @约瑟夫环 f(1) = 0;
 * f(n) = ( f(n-1) + m ) % n
 * f : 下标，从0开始
 */
public class SO62Solution {
    class Solution {
        public int lastRemaining(int n, int m) {
            int f = 0; //n = 1
            for (int i = 2; i <= n; i++) {
                f = (f + m) % i;
            }
            return f;
        }

    }
}
