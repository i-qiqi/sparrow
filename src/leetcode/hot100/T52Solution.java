package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T52Solution {

    // T52
    int ans = 0;
    int size;

    public int totalNQueens(int n) {
        // - 的 优先级高于 <<
        // x << n : x 左移n位
        ans = 0;
        size = (1 << n) - 1; // n bit 1, 1表示可以放Q
        dfs(0, 0, 0); // 按1到n行依次选择每行的某一列
        return ans;
    }

    // row : 每一位表示是否放了皇后，0-未放
    private void dfs(int row, int leftDiagonal, int rightDiagnoal) {

        if (row == size) {
            ans++;
            return;
        }

        // 对角线不能放
        // 取反找可以放的
        int optionalPositions = size & (~(row | leftDiagonal | rightDiagnoal));
        // 尝试每一位1
        while (optionalPositions != 0) {
            // 取可放皇后的位置，取最后一位1
            int currentPosition = optionalPositions & -optionalPositions;

            // currentPosition 的左下方位置不能放，就是左边列不能放，或leftDiagonal再左移一位，有对角线同理。
            dfs(row | currentPosition, (leftDiagonal | currentPosition) << 1, (rightDiagnoal | currentPosition) >> 1);

            // 抹去最后一个1, 尝试下一位1
            optionalPositions = optionalPositions & (optionalPositions - 1); // 选最后一位为皇后，置为0
        }

    }

    /**
     * T51
     */
    List<List<String>> ansSequences;
    int len;

    // int size;
    public List<List<String>> solveNQueens(int n) {
        len = n;
        ansSequences = new ArrayList<>();
        size = (1 << n) - 1;
        dfs(0, 0, 0, new ArrayList<>());
        return ansSequences;

    }

    private void dfs(int row, int leftDiagonal, int rightDiagnoal, List<String> path) {

        if (row == size) {
            ansSequences.add(new ArrayList<>(path));
            return;
        }

        // 对角线不能放
        // 取反找可以放的
        int optionalPositions = size & (~(row | leftDiagonal | rightDiagnoal));
        // 尝试每一位1
        while (optionalPositions != 0) {
            // 取可放皇后的位置，取最后一位1
            int currentPosition = optionalPositions & -optionalPositions;

            // 第几列
            // System.out.println(currentPosition);
            double idx = Math.log(currentPosition) / Math.log(2);
            // System.out.println(idx);

            String s = getRowString(len, (int) idx);
            // currentPosition 的左下方位置不能放，就是左边列不能放，或leftDiagonal再左移一位，有对角线同理。
            // System.out.println(s);
            path.add(s);
            dfs(row | currentPosition, (leftDiagonal | currentPosition) << 1, (rightDiagnoal | currentPosition) >> 1,
                    path);
            path.remove(path.size() - 1);

            // 抹去最后一个1, 尝试下一位1
            optionalPositions = optionalPositions & (optionalPositions - 1); // 选最后一位为皇后，置为0
        }

    }

    private String getRowString(int n, int idx) {
        char[] arr = new char[n];
        Arrays.fill(arr, '.');
        arr[n - 1 - idx] = 'Q';
        String s = new String(arr);
        arr = null;
        return s;
    }

    public static void main(String[] args) {
        // System.out.println(solution.totalNQueens(1));
        // System.out.println(solution.totalNQueens(2));
        // System.out.println(solution.totalNQueens(3));
        // System.out.println(solution.totalNQueens(4));
        // System.out.println(solution.totalNQueens(5));
        // System.out.println(solution.totalNQueens(6));
        // System.out.println(solution.totalNQueens(7));
        // System.out.println(solution.totalNQueens(8));
        // System.out.println(solution.totalNQueens(9));
        System.out.println(Math.log(1) / Math.log(2));
        System.out.println(Math.log(2) / Math.log(2));
        System.out.println(Math.log(4) / Math.log(2));
    }

}
