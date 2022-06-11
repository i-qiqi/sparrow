package leetcode.math;

import java.util.Arrays;

/**
 * @矩形面积
 */
public class T223Solution {
    class Solution {
        /**
         * 也可不判断相交来做
         * @param ax1
         * @param ay1
         * @param ax2
         * @param ay2
         * @param bx1
         * @param by1
         * @param bx2
         * @param by2
         * @return
         */
        public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int[] xarr = {ax1, ax2, bx1, bx2};
            int[] yarr = {ay1, ay2, by1, by2};
            //判断不相交
            Arrays.sort(xarr);
            Arrays.sort(yarr);
            int aArea = (ax2 - ax1) * (ay2 - ay1);
            int bArea = (bx2 - bx1) * (by2 - by1);
            if (!isRectangleOverlap(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2)) return aArea + bArea;
            return aArea + bArea - (xarr[2] - xarr[1]) * (yarr[2] - yarr[1]);
        }


        public boolean isRectangleOverlap(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

            if (Math.min(ax1, ax2) >= Math.max(bx1, bx2)) {
                return false;
            }
            if (Math.min(bx1, bx2) >= Math.max(ax1, ax2)) {
                return false;
            }
            if (Math.min(ay1, ay2) >= Math.max(by1, by2)) {
                return false;
            }
            if (Math.min(by1, by2) >= Math.max(ay1, ay2)) {
                return false;
            }
            return true;
        }
    }
}
