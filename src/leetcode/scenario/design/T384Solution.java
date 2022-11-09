package leetcode.scenario.design;

import java.util.Random;

public class T384Solution {
    int[] shuffled;
    final int[] origin;

    public T384Solution(int[] nums) {
        this.shuffled = nums;
        origin = new int[nums.length];
        System.arraycopy(nums, 0, origin, 0, origin.length);
    }

    public int[] reset() {
        System.arraycopy(origin, 0, shuffled, 0, shuffled.length);
        return shuffled;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < shuffled.length; i++) {
            int rand = i + random.nextInt(shuffled.length - i); //不包括右边界 [i, shuffled.length - 1]
            swap(shuffled, i, rand);
        }
        return shuffled;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
