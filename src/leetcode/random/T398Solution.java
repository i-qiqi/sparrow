package leetcode.random;

import java.util.Random;

public class T398Solution {
    class Solution {
        Random rand;
        int[] nums;
        public Solution(int[] nums) {
            this.nums = nums;
            this.rand = new Random();
        }

        /**
         * why I think select the nums equaling to target first, then random return res ??, so stupid
         * @param target
         * @return
         */
        public int pick(int target) {
            int cnt = 0;
            int ret = -1;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != target){
                    continue;
                }
                cnt++;
                if(rand.nextInt(cnt) == 0){
                    ret = i;
                }
            }

            return ret;
        }
    }
}
