package leetcode.hot100;

public class T48Solution {
    /**
     * 
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 
     * step1 : 主对角线翻转
     * 1 4 7
     * 2 5 8
     * 3 6 9
     * 
     * 左右翻转
     * 
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int rn = matrix.length;
        int cn = matrix.length;
        // step 1:
        for (int i = 0; i < rn; i++) {
            for (int j = i; j < cn; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        // step 2
        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn / 2; j++) {
                swap(matrix, i, j, i, cn - j - 1);
            }
        }
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int t = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = t;
    }

}