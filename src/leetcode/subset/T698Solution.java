package leetcode.subset;

import com.sun.deploy.util.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.*;

public class T698Solution {
    class Solution {
        int used = 0; // 使用位图技巧
        boolean ans = false;
        HashMap<Integer, Boolean> memo = new HashMap<>();

        public boolean canPartitionKSubsets(int[] nums, int k) {
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                total += nums[i];
            }
            if (total % k != 0) return false;

            traceback(nums, k, 0, 0, total / k);
            return ans;
        }

        //N^4
        public void traceback(int[] nums, int k, int st, int subSetSum, int target) {
            if (ans == true) return;
            if (k == 0) {
                //找到了
//                System.out.println("yes");
                ans = true;
                return;
            }
            // System.out.println(state);
            if (subSetSum == target) {
                //下一个桶
                traceback(nums, k - 1, 0, 0, target);
                memo.put(used, ans);
                return;
            }

            if (memo.containsKey(used)) {
                return;
            }
            for (int i = st; i < nums.length; i++) {
                // 剪枝
                if (((used >> i) & 1) == 1) { // 判断第 i 位是否是 1
                    // nums[i] 已经被装入别的桶中
                    continue;
                }
                if (subSetSum + nums[i] > target) {
                    continue;
                }
                //选st
                used |= 1 << i; // 将第 i 位置为 1
                traceback(nums, k, i + 1, subSetSum + nums[i], target);
                // 撤销选择
//                used ^= 1 << i; // 使用异或运算将第 i 位恢复 0
                used &= 0 << i;
                //回溯，跳到下个分支
            }
            // [4,4,6,2,3,8,10,2,10,7]
            //进入下一个桶
        }
    }


    class Solution1 {
        boolean ans = false;
        int bucketNum;

        public boolean canPartitionKSubsets(int[] nums, int k) {
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                total += nums[i];
            }
            if (total % k != 0) return false;

            int[] bucketSum = new int[k];
            bucketNum = k;
            Arrays.sort(nums);
            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            traceback(nums, 0, 0, bucketSum, total / k);
            return ans;
        }

        //N^4
        public void traceback(int[] nums, int k, int st, int[] bucketSum, int target) {

            if (st == nums.length) {
                //找到了
                for (int sum : bucketSum) {
                    if (sum != target) {
                        return;
                    }
                }
                ans = true;
                return;
            }

            if (nums[st] > target) return;

            for (int i = k; i < bucketNum; i++) {
                if (bucketSum[i] + nums[st] > target) continue;
                //选桶
                bucketSum[i] += nums[st];
                traceback(nums, 0, st + 1, bucketSum, target);
                //回溯，跳到下个桶
                bucketSum[i] -= nums[st];
            }
        }
    }

    public static void main1(String[] args) {
        boolean[] visited = new boolean[3];
        int[] state = new int[2];
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        System.out.println(String.join("#", "sss", "a"));
        String ans = StringUtils.join(list, "#");
        System.out.println(ans);
    }

    public static void main(String[] args) {
        System.out.println(5 & 1 << 1);
    }
}
