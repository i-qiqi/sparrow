package leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T313Solution {
    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] dp = new int[n + 1];

            int[] pointers = new int[primes.length];
            dp[1] = 1;
            Arrays.fill(pointers, 1);
            int tmpMin, tmpIdx;
            for (int i = 2; i <= n; i++) {
                tmpMin = Integer.MAX_VALUE;
                tmpIdx = -1;
                List<Integer> tmpIdxs = new ArrayList<>();
                for (int j = 0; j < primes.length; j++) {
                    int tmp = dp[pointers[j]] * primes[j];
                    if (tmpMin > tmp) {
                        tmpMin = tmp;
                        tmpIdxs = new ArrayList<>();
                        tmpIdxs.add(j);
                    } else if (tmpMin == tmp) {
                        tmpIdxs.add(j);
                    }
                }

                for (int k = 0; k < tmpIdxs.size(); k++) {
                    pointers[tmpIdxs.get(k)]++;
                }

                dp[i] = tmpMin;
                // System.out.println(dp[i]);
            }

            return dp[n];
        }
    }

//    Arrays.stream(nums).min().getAsInt();
}
