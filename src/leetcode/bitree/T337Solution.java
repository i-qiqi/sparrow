package leetcode.bitree;

public class T337Solution {
    class Solution {

        public int rob(TreeNode root) {
            int[] ret = dfs(root);
            return Math.max(ret[0], ret[1]);
        }

        public int[] dfs(TreeNode root) {
            if (root == null) return new int[]{0, 0};

            int[] leftRob = dfs(root.left);
            int[] rightRob = dfs(root.right);
            int robSum = leftRob[0] + rightRob[0] + root.val;
            int notRobSum = Math.max(leftRob[0], leftRob[1]) + Math.max(rightRob[0], rightRob[1]);
            return new int[]{notRobSum, robSum};
        }
    }
}
