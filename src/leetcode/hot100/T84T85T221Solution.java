package leetcode.hot100;

import java.util.LinkedList;

public class T84T85T221Solution {

    public static void main(String[] args) {
    }

    // T85 最大矩形
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }

            ans = Math.max(ans, largestRectangleArea(heights));
        }

        return ans;
    }

    // T84 直方图最大矩形
    public int largestRectangleArea(int[] heights) {

        // if ( heights.length == 1) return heights[0];
        LinkedList<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i <= heights.length; i++) {
            // heights[i] < heights[stack.getFirst()] || i == heights.length, 会先判断前面
            while (!stack.isEmpty() && (i == heights.length || heights[i] < heights[stack.getFirst()])) {
                // 遇到比栈顶小的,出栈
                int topIdx = stack.removeFirst();
                int leftIdx = stack.isEmpty() ? -1 : stack.getFirst();
                int tmpAns = heights[topIdx] * (i - leftIdx - 1);
                ans = Math.max(ans, tmpAns);
            }
            stack.addFirst(i);
        }
        return ans;
    }

    // T221 最大正方形
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }

            ans = Math.max(ans, largestSquareArea(heights));
        }

        return ans;
    }

    // 直方图最大正方形
    public int largestSquareArea(int[] heights) {

        // if ( heights.length == 1) return heights[0];
        LinkedList<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i <= heights.length; i++) {
            // heights[i] < heights[stack.getFirst()] || i == heights.length, 会先判断前面
            while (!stack.isEmpty() && (i == heights.length || heights[i] < heights[stack.getFirst()])) {
                // 遇到比栈顶小的,出栈
                int topIdx = stack.removeFirst();
                int leftIdx = stack.isEmpty() ? -1 : stack.getFirst();
                int sideLen = Math.min(heights[topIdx], (i - leftIdx - 1));
                ans = Math.max(ans, sideLen * sideLen);
            }
            stack.addFirst(i);
        }
        return ans;
    }
}
