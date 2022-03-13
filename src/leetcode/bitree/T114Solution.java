package leetcode.bitree;

public class T114Solution {
    //写的不够优雅，还需要学习东哥写法
    class Solution {
        public void flatten(TreeNode root) {
            traverse(root);
        }

        public TreeNode traverse(TreeNode root) {
            if (root == null)
                return null;
            if (root.left == null) {
                root.right = traverse(root.right);
                return root;
            }

            if (root.right == null) {
                root.right = traverse(root.left);
                root.left = null;
                return root;
            }

            TreeNode leftHead = traverse(root.left);
            TreeNode rightHead = traverse(root.right);

            TreeNode p = leftHead;
            while (p.right != null) {
                p = p.right;
            }
            p.right = rightHead;
            p.left = null;

            root.left = null;
            root.right = leftHead;

            return root;
        }
    }
}
