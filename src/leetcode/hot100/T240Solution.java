package leetcode.hot100;

public class T240Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 从右下角(m-1, 0)或者右上角（0，n-1）走
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                i--;
            }
        }

        return false;

    }

}
