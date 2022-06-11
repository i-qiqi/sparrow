package leetcode.arr;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class T215Solution {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            return find(nums, 0, nums.length - 1, k);
        }

        public int find(int[] nums, int left, int right, int k) {
            if (left > right) return Integer.MIN_VALUE;
            int pivotIdx = partition(nums, left, right);
            int kIdx = left + k - 1;
            if (kIdx == pivotIdx) {
                return nums[pivotIdx];
            } else if (kIdx < pivotIdx) {
                return find(nums, left, pivotIdx - 1, k);
            } else {
                return find(nums, pivotIdx + 1, right, k - (pivotIdx - left + 1));
            }
        }

        private int partitionRandom(int[] nums, int low, int high) {
//            Random rand = new Random();
//            int pivotIdx = rand.nextInt(right - left + 1) + left; // [left , right]
            int tmp = nums[low];
            while (low < high) {
                //先从后往前遍历
                //比主元小的跳过, right 左边都比主元小
                while (low < high && nums[high] <= tmp) high--;
                nums[low] = nums[high];
                //再从前往后遍历
                // 比主元大的跳过，low 的左边都主元大
                while (low < high && nums[low] >= tmp) low++;
                nums[high] = nums[low];
            }
            //归位
            //跳出循环时low = high, 当前位置不是tmp
            nums[low] = tmp;
            return low;
        }

        //int randNumber = rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        public int partition(int[] nums, int left, int right) {
            Random rand = new Random();
            int randPivotIdx = rand.nextInt(right - left + 1) + left;
            int pivot = nums[randPivotIdx];
            // 把主元存在一边，等下交换
            swap(nums, randPivotIdx, right);
            int start = left;

            for (int i = left; i < right; i++) {
                if (nums[i] > pivot) {
                    swap(nums, start, i);
                    start++;
                }
            }
            swap(nums, start, right);
            return start;
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            Queue<Integer> q = new PriorityQueue<>(k);

            for(int num : nums){
                if(q.size() < k) {
                    q.offer(num);
                } else {
                    int top = q.peek();
                    if(num > top){
                        q.poll();
                        q.offer(num);
                    }
                }
            }

            return q.peek();
        }
    }
}
