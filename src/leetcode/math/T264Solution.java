package leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class T264Solution {
    class Solution {
        Map<Integer, Boolean> isUgly;

        public int nthUglyNumber(int n) {
            if (n == 1) return 1;
            isUgly = new HashMap<>();
            isUgly.put(1, true);
            isUgly.put(2, true);
            isUgly.put(3, true);
            isUgly.put(5, true);

            int cnt = 1;
            int maxUgly = 5;
            for (int i = 2; i < maxUgly; i++) {
                if (isUglyNum(i)) {
                    cnt++;
                    if (cnt == n) return i;
                    for (int j = 2; j <= i; j++) {
                        if (isUglyNum(j)) {
                            isUgly.put(i * j, true);
                            maxUgly = Math.max(i * j, maxUgly);
                        }
                    }
                }
            }

            return -1;
        }

        public Boolean isUglyNum(int i) {
            return isUgly.containsKey(i) && isUgly.get(i) == true;
        }
    }

    class Solution1 {
        public int nthUglyNumber(int n) {
            int[] dp = new int[n + 1];

            dp[1] = 1;
            int p2 = 1, p3 = 1, p5 = 1;
            int a, b, c, tmpMin;
            for (int i = 2; i <= n; i++) {
                a = 2 * dp[p2];
                b = 3 * dp[p3];
                c = 5 * dp[p5];
                tmpMin = min(a, b, c);
                if (a == tmpMin) {
                    p2++;
                }

                if (b == tmpMin) {
                    p3++;
                }

                if (c == tmpMin) {
                    p5++;
                }

                dp[i] = tmpMin;
            }

            return dp[n];
        }

        public int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }
    }
}
