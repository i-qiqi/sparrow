package leetcode.bitoperation;

public class T137Solution {
    class Solution {
        public int singleNumber(int[] nums) {
            //重复出现次数是odd
            int ones = 0;
            int twos = 0;
            for (int num : nums) {
                ones = ones ^ num & ~twos;
                twos = twos ^ num & ~ones;
            }
            return ones;
        }
    }

    class Solution1 {
        public int singleNumber(int[] nums) {
            //重复出现次数是odd
            int ans = 0;
            for (int i = 0; i < 32; i++) {
                int bitCnt = 0;
                int mask = 1 << i;
                for (int num : nums) {
                    if ((num & mask) == mask) {
                        // if(bitCnt == 0 || bitCnt == 1) {
                        //     bitCnt++;
                        // } else if (bitCnt == 2){
                        //     bitCnt = 0;
                        // }
                        bitCnt++;
                    }

                }
                bitCnt %= 3;
                if (bitCnt == 2) {
                    System.out.println("ex..");
                }

                ans |= bitCnt << i;
            }
            return ans;
        }
    }
}
