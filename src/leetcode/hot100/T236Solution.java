package leetcode.hot100;

public class T236Solution {
    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            this.val = v;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root == p)
            return p;
        if (root == q)
            return q;

        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null) {
            return root;
        } else if (l != null) {
            return l;
        } else if (r != null) {
            return r;
        }

        return null;
    }
}
