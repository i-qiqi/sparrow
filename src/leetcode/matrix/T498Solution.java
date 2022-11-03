package leetcode.matrix;

public class T498Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int all = mat.length * mat[0].length;
        int x = 0, y = 0;
        int[] result = new int[all];
        for (int i = 0; i < all; i++) {
            result[i] = mat[x][y];

            if ((x + y) % 2 == 0) {
                if (y == mat[0].length - 1) x++;
                else if (x == 0) {
                    y++;
                } else {
                    x--;
                    y++;
                }
            } else {
                if (x == mat.length - 1) {
                    y++;
                } else if (y == 0) {
                    x++;
                } else {
                    x++;
                    y--;
                }
            }
        }
        return result;
    }
}
