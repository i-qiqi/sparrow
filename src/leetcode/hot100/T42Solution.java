package leetcode.hot100;

public class T42Solution {
    class Solution {
        public int trap(int[] height) {
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;

            int ans = 0;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);

                //if(height[left] < height[right])
                if (leftMax < rightMax) { // leftMax = height[left] < height[right] = rightMax
                    ans += leftMax - height[left];
                    left++;
                } else { // leftMax = height[left] > height[right] = rightMax
                    ans += rightMax - height[right];
                    right--;
                }
            }
            return ans;
        }
    }
}
