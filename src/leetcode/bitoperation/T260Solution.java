package leetcode.bitoperation;

public class T260Solution {
    class Solution {
        public int[] singleNumber(int[] nums) {
            int ans = 0;
            for (int num : nums) {
                ans ^= num;
            }

            // 防止溢出
            // 因为二进制有正负0，负零用于多表示一位负数，这个负数如果取相反数，会产生溢出，所以不能用 a & (-a) 取最低有效位
            // 负0的特点是第一位是1，其余位是0，所以它的最低有效位就是自己
//            int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
            int bitMask = 0;
            for (int i = 0; i < 32; i++) {
                int mask = 1 << i;
                //TODO: mask & ans) == 1 千万不要这么误写！！！
                if ((mask & ans) == mask) {
                    bitMask = mask;
                    break;
                }
            }

            int ans1 = 0, ans2 = 0;
            for (int num : nums) {
                if ((bitMask & num) == bitMask) {
                    ans1 ^= num;
                } else {
                    ans2 ^= num;
                }
            }

            return new int[]{ans1, ans2};
        }
    }
}
