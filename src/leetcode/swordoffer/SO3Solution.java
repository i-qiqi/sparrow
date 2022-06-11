package leetcode.swordoffer;

public class SO3Solution {
    class Solution {
        public int findRepeatNumber(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                //本身在自己的位置或者之前被调过，就跳过
                while (i != nums[i]) {
                    if (nums[i] == nums[nums[i]]) {
                        return nums[i];
                    }

                    //归位
                    int temp = nums[i];
                    nums[i] = nums[nums[i]];
                    nums[temp] = temp;
                    // int temp = nums[nums[i]];
                    // nums[nums[i]] = nums[i];
                    // nums[i] = temp;

                }
            }

            return -1;
        }
    }
}
