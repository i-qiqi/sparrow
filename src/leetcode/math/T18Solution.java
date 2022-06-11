package leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T18Solution {


    public static void main(String[] args) {
        T18Solution t18Solution = new T18Solution();
        SolutionNSum solutionNSum = t18Solution.new SolutionNSum();
        int[] nums = {-2, -1, -1, 1, 1, 2, 2};
        List<List<Integer>> res = solutionNSum.fourSum(nums, 3);

        for (List<Integer> ans : res) {
            System.out.println(ans);
        }
    }

    class SolutionNSum {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            // System.out.println(nums[0] + " " + nums[nums.length - 1]);
            return kSum(nums, target, 4, 0, nums.length - 1);
        }

        public List<List<Integer>> kSum(int[] nums, int target, int k, int low, int high) {
            List<List<Integer>> res = new ArrayList<>();

//            if (k < 2 || k > high + 1) return res;

            if (k == 2) {
                res = twoSum(nums, target, low, high);
            } else {
                for (int i = low; i <= high; i++) {
                    if (i > low && nums[i] == nums[i - 1]) {
                        continue;
                    }
                    List<List<Integer>> tempRes = kSum(nums, target - nums[i], k - 1, i + 1, high);
                    for (List<Integer> ans : tempRes) {
                        ans.add(nums[i]);
                        res.add(ans);
                    }
                    // while (i < high && nums[i] == nums[i + 1]) i++;
                }
            }
            return res;
        }

        public List<List<Integer>> twoSum(int[] nums, int target, int low, int high) {
            int left = low, right = high;

            List<List<Integer>> res = new ArrayList<>();
            while (left < right) {
                // bug 写法
                // if(left > low && nums[left] == nums[left - 1]) left++;
                // if(right < high && nums[right] == nums[right + 1]) right--;
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(nums[left]);
                    ans.add(nums[right]);
                    res.add(ans);
                    left++;
                    right--;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
                while (left < right && left > low && nums[left] == nums[left - 1]) left++;
                while (left < right && right < high && nums[right] == nums[right + 1]) right--;
            }

            return res;
        }
    }
}
