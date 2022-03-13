package leetcode.hot100;

public class T11Solution {
    public static void main(String[] args) {
        T11Solution t11Solution = new T11Solution();
        int[] h = {1,2,1};
        t11Solution.maxArea(h);
    }
    public int maxArea1(int[] height) {
        int len = height.length;
        // 左右双向遍历
        int[][] maxHeightIdxs = new int[len][2];
        int leftMaxIdx = 0, rightMaxIdx = len - 1;
        for (int i = 0, j = len - 1; i < len && j >= 0; i++, j--) {
            maxHeightIdxs[i][0] = leftMaxIdx;
            maxHeightIdxs[j][1] = rightMaxIdx;

            if (height[leftMaxIdx] < height[i]) {
                leftMaxIdx = i;
            }
            if (height[rightMaxIdx] < height[j]) {
                rightMaxIdx = j;
            }
            System.out.println(leftMaxIdx + "," + rightMaxIdx);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            int leftIdx = maxHeightIdxs[i][0], rightIdx = maxHeightIdxs[i][1];
            ans = Math.min(height[leftIdx], height[rightIdx]) * (rightIdx - leftIdx);
        }
        return ans;
    }

    public int maxArea(int[] height){
        int len = height.length;
        int ans = 0;
        int l = 0 , r = len - 1;
        while ( l < r ) {
            ans = Math.max(ans, Math.min(height[l], height[r]) * ( r - l));
            if ( height[l] <= height[r] ) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}