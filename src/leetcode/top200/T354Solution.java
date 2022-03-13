package leetcode.top200;

import java.util.Arrays;
import java.util.Comparator;

public class T354Solution {

    public int maxEnvelopes(int[][] envelopes) {

        // Comparator<int[]> c = Comparator.comparing(o -> o[0]);
        // Comparator<int[]> c = Comparator.comparing(o -> o[1]);
        //第二列降序, 避免相互包含
        Comparator<int[]> c = (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
        Arrays.sort(envelopes, c);

        int k = 1;
        int[] tails = new int[envelopes.length];
        tails[0] = envelopes[0][k];
        int end = 0;

        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][k] > tails[end]) {
                tails[++end] = envelopes[i][k];
            } else {
                int left = 0, right = end;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (tails[mid] < envelopes[i][k]) {
                        left++;
                    } else {
                        right = mid;
                    }
                }
                tails[left] = envelopes[i][k];
            }
        }
        return end + 1;

    }

    public static void main(String[] args) {
        int[][] envelopes = { { 2, 1 }, { 1, 2 }, { 2, 3 }, { -2, 4 }, { 1, 1 } };
        Comparator<int[]> c = (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
        Arrays.sort(envelopes, c);
        for (int[] arr : envelopes) {
            System.out.println(Arrays.toString(arr));
        }
    }

}
