package leetcode.swordoffer;

import leetcode.bitree.TreeNode;

public class SO53Solution {
    class Solution {
        TreeNode pre, ans;

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null) return null;
            TreeNode ansLeft = inorderSuccessor(root.left, p);
            if (ansLeft != null) return ansLeft;
            if (pre == p) {
                return root;
            }
            pre = root;
            TreeNode ansRight = inorderSuccessor(root.right, p);
            return ansRight;
        }
    }

    class Solution1 {
        TreeNode pre, ans;

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            traverse(root, p);
            return ans;
        }

        public void traverse(TreeNode root, TreeNode p) {
            if (root == null) return;
            traverse(root.left, p);
            if (pre == p) {
                ans = root;
                pre = null; //attention
                return;
            }
            pre = root;
            traverse(root.right, p);
        }
    }

    class Solution2 {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            //非递归
            TreeNode q = root;
            TreeNode next = null;
            while (q != null) {
                if (p.val == q.val) {
                    //找下一个节点
                    TreeNode right = q.right;
                    if (right == null) return next;
                    while (right.left != null) {
                        right = right.left;
                    }
                    return right;
                } else if (p.val < q.val) {
                    next = q;
                    q = q.left;
                } else if (p.val > q.val) {
                    q = q.right;
                }
            }

            return null;
        }
    }

    class Solution3 {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            //非递归
            TreeNode ans = null;
            while (root != null) {
                if (root.val > p.val) {
                    ans = root;
                    root = root.left;
                } else {
                    root = root.right;
                }
            }

            return ans;
        }
    }
}
