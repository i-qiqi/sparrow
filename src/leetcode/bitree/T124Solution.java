package leetcode.bitree;

public class T124Solution {
    class Solution {
        int ans = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            helper(root);
            return ans;
        }

        public int helper(TreeNode root) {
            if(root == null) return 0;
            int left = helper(root.left);
            int right = helper(root.right);

            int rootToLeft = Math.max(root.val, root.val+left);
            int rootToRight = Math.max(root.val, root.val + right);

            ans = Math.max(ans, rootToLeft);
            ans = Math.max(ans, rootToRight);
            ans = Math.max(ans, root.val + left + right);

            return Math.max(rootToLeft, rootToRight);
        }
    }
}
