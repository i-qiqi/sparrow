package leetcode.bitree;

public class T98Solution {
    class Solution {
        TreeNode prev = null;
        boolean ans = true;

        public boolean isValidBST(TreeNode root) {
            dfs(root);
            return ans;
        }


        public void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            if (prev != null) {
                if (root.val <= prev.val) {
                    ans = false;
                }
            }
            prev = root;
            dfs(root.right);
        }
    }


    /**
     * 注意integer 边界
     */
    class Solution1 {
        public boolean isValidBST(TreeNode root) {
            return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValid(TreeNode root, long leftMin, long rightMax) {
            if (root == null) return true;
            if (root.val <= leftMin || root.val >= rightMax) return false;

            return isValid(root.left, leftMin, root.val) && isValid(root.right, root.val, rightMax);
        }
    }

    /**
     * 换一种东哥写法
     */
    class Solution3 {
        public boolean isValidBST(TreeNode root) {
            return isValid(root, null, null);
        }

        public boolean isValid(TreeNode root, TreeNode leftBound, TreeNode rightBound) {
            if (root == null) return true;

            if (leftBound != null && root.val <= leftBound.val) return false;

            if (rightBound != null && root.val >= rightBound.val) return false;

            return isValid(root.left, leftBound, root) && isValid(root.right, root, rightBound);
        }
    }
}
