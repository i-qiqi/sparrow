package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class T768Solution {
    public int maxChunksToSorted(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();

        int[] expected = arr.clone();

        Arrays.sort(expected);

        int numCntDiff = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {

            int a = arr[i], b = expected[i];

            counter.put(a, counter.getOrDefault(a, 0) + 1);

            if (counter.get(a) == 1) {
                numCntDiff++;
            }

            if (counter.get(a) == 0) {
                numCntDiff--;
            }

            counter.put(b, counter.getOrDefault(b, 0) - 1);

            if (counter.get(b) == -1) {
                numCntDiff++;
            }

            if (counter.get(b) == 0) {
                numCntDiff--;
            }

            if (numCntDiff == 0) {
                ans++;
            }

        }

        return ans;
    }

    public int maxChunksToSorted1(int[] arr) {
        Map<Integer, Integer> need = new HashMap<>();
        int[] expected = arr.clone();

        Arrays.sort(expected);

        int cnt = 0; //当前window窗口需要匹配的树
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {

            int a = arr[i], b = expected[i];

            need.put(a, need.getOrDefault(a, 0) + 1);

            if (need.get(a) == 1) {
                cnt++;
            }

            need.put(b, need.getOrDefault(b, 0) - 1);

            if (need.get(b) == 0) {
                cnt--;
            }

            if (cnt == 0) {
                ans++;
            }

        }

        return ans;
    }
}
