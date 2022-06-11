package leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @螺旋矩阵
 * 写的易懂，记住！！！
 */
public class T54Solution {
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;

            List<Integer> ans = new ArrayList<>();
            int left = 0, right = n - 1, top = 0, bottom = m - 1;
            int i = left, j = top;
            while (ans.size() < m * n) {// 不能是<=
                /**
                 * case: 不夹住会重复扫
                 * 1 2 3 4
                 * 5 6 7 8
                 * 9 10 11 12
                 */
                if (top <= bottom) { //横向扫，夹上下
                    for (i = left, j = top; j <= right; j++) {
                        ans.add(matrix[i][j]);
                    }
                }
                top++;

                if (left <= right) {//纵向扫，夹左右
                    for (i = top, j = right; i <= bottom; i++) {
                        ans.add(matrix[i][j]);
                    }
                    right--;
                }

                if (top <= bottom) {
                    for (i = bottom, j = right; j >= left; j--) {
                        ans.add(matrix[i][j]);
                    }
                    bottom--;
                }

                if (left <= right) {
                    for (i = bottom, j = left; i >= top; i--) {
                        ans.add(matrix[i][j]);
                    }
                    left++;
                }
            }
            StringBuilder s = new StringBuilder();
            s.deleteCharAt(s.length()-1);

            return ans;
        }

    }
}
