package leetcode.swordoffer;

public class SO33Solution {
    class Solution {
        public boolean verifyPostorder(int[] postorder) {
            return verifyPostorder(postorder, 0, postorder.length - 1);
        }

        public boolean verifyPostorder(int[] postorder, int low, int high) {
            if (low >= high) return true;

            int rootVal = postorder[high];
            int mid = high; //默认找不到，右子树为空
            //边界情况处理，注意
            for (int k = low; k < high; k++) {
                if (postorder[k] > rootVal) {
                    mid = k;
                    break;
                }
            }

            for (int k = mid; k < high; k++) {
                if (postorder[k] < rootVal) {
                    return false;
                }
            }

            return verifyPostorder(postorder, low, mid - 1) && verifyPostorder(postorder, mid, high - 1);
        }
    }
}
