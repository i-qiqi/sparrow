package leetcode.slidingwindow;

import java.util.LinkedList;

public class T862Solution {

    /**
     * 可以解答T209
     * <p>
     * T862
     * 数组中可能有负数
     */
    class Solution {
        public int shortestSubarray(int[] nums, int k) {
            long[] preSum = new long[nums.length + 1];
            preSum[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }

            int ans = Integer.MAX_VALUE;
            int left = 0, right = 0;
            MonotonicQueue window = new MonotonicQueue();

            //win[0:right-1] , preSum[right]
            while (right < preSum.length) {
                window.push(preSum[right]);
                right++;

                while (right < preSum.length && left < right && preSum[right] - window.min() >= k) {
                    ans = Math.min(ans, right - left);
                    window.pop(preSum[left]);
                    left++;
                }
            }

            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        class MonotonicQueue {
            LinkedList<Long> q;

            public MonotonicQueue() {
                q = new LinkedList<>();
            }

            public void push(long item) {
                while (!q.isEmpty() && item < q.getLast()) {
                    q.removeLast();
                }

                q.addLast(item);
            }

            public void pop(long item) {
                if (q.getFirst() == item) {
                    q.removeFirst();
                }
            }

            public long min() {
                return q.getFirst();
            }
        }
    }

    /**
     * T209 长度最小的子数组
     * 仅仅适用于数组全为正整数的情况
     */
    class T209Solution {
        public int shortestSubarray(int[] nums, int k) {
            int left = 0, right = 0;
            int ans = Integer.MAX_VALUE;
            long sum = 0;
            while (right < nums.length) {
                sum += nums[right];
                right++;
                while (right <= nums.length && left < right && sum >= k) {
                    ans = Math.min(ans, right - left);
                    sum -= nums[left];
                    left++;
                }
            }

            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
}
