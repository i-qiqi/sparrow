package leetcode.swordoffer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SO40Solution {
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0) return new int[]{};
            //大顶堆
            Comparator<Integer> c = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 == o2) {
                        return 0;
                    } else if (o1 < o2) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            };

            Queue<Integer> q = new PriorityQueue(k, c);
            for (int i = 0; i < arr.length; i++) {
                if (q.size() < k) {
                    q.offer(arr[i]);
                } else {
                    int top = q.peek();
                    if (arr[i] < top) {
                        q.poll();
                        q.offer(arr[i]);
                    }
                }
            }

            int[] ans = new int[k];
            int i = 0;
            while (q.size() > 0) {
                ans[i++] = q.poll();
            }
            return ans;
        }
    }
}
