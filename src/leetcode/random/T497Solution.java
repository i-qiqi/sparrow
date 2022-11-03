package leetcode.random;

import java.util.Random;

public class T497Solution {
    private int[][] rects;

    public T497Solution(int[][] rects) {
        this.rects = rects;
    }

    public int[] pick() {
        int idx = -1, cur = 0, curSum = 0, n = rects.length;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int x1 = rects[i][0], y1 = rects[i][1], x2 = rects[i][2], y2 = rects[i][3];
            cur = (x2 - x1 + 1) * (y2 - y1 + 1);
            curSum += cur;
            int r = rand.nextInt(curSum) + 1;
            if (r <= cur) {
                idx = i;
            }
        }
        int x1 = rects[idx][0], y1 = rects[idx][1], x2 = rects[idx][2], y2 = rects[idx][3];
        int rx = rand.nextInt(x2 - x1 + 1) + x1;
        int ry = rand.nextInt(y2 - y1 + 1) + y1;
        return new int[]{rx, ry};
    }
}
