package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * a + b + c = 0
 * 且不重复的三元组
 */
public class T15Solution {

    public static void main(String[] args) {

        List<String> list = Stream.of(new String[] { "111" }).collect(Collectors.toList());
        // List<Integer> list = new ArrayList<>();
        // list.add(2);
        // list.add(3);
        // list.add(-2);
        // list.sort((a, b) -> a - b);
        // List<Integer> list1 = new ArrayList<>();
        // list1.add(2);
        // list1.add(3);
        // list1.add(-2);
        // list1.sort((a, b) -> a - b);

        // Set<String> deDuplSet = new HashSet<>();
        // if (!deDuplSet.(list.toString())) {
        // deDuplSet.add(list.toString());
        // }
        // if (!deDuplSet.(list.toString())) {
        // deDuplSet.add(list1.toString());
        // }
        // deDuplSet.add(list1.toString());

        // System.out.println(list.toString());

        int[] nums = { -1, 0, 1, 2, -1, -4 };
        T15Solution solution = new T15Solution();
        solution.threeSum(nums);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        Set<String> deDuplSet = new HashSet<>();
        int preSum = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int tmpSum = -nums[i];
            if (preSum != tmpSum) {
                // 求 a + b = tempSum => (a, b);
                Map<Integer, Integer> mp = new HashMap<>();
                for (int k = 0; k < len; k++) {
                    if (k == i)
                        continue;
                    if (mp.containsKey(tmpSum - nums[k])) {
                        List<Integer> tmpAns = new ArrayList<>();
                        tmpAns.add(nums[i]);
                        tmpAns.add(tmpSum - nums[k]);
                        tmpAns.add(nums[k]);
                        tmpAns.sort((a, b) -> a - b);
                        if (!deDuplSet.contains(tmpAns.toString())) {
                            // System.out.println(tmpAns.toString());
                            ans.add(tmpAns);
                            deDuplSet.add(tmpAns.toString());
                        }
                    }
                    mp.put(nums[k], k);
                }

            }
            preSum = tmpSum;
        }
        return ans;
    }

}
