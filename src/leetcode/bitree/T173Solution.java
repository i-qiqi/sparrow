package leetcode.bitree;

import java.util.LinkedList;

public class T173Solution {

    class BSTIterator {
        LinkedList<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new LinkedList<>();
            pushLeftBranch(root);
        }

        public void pushLeftBranch(TreeNode p) {
            while (p != null) {
                stack.addLast(p);
                p = p.left;
            }
        }

        public int next() {
            int ret = -1;
            TreeNode q = stack.removeLast();
            ret = q.val;
            pushLeftBranch(q.right);
            return ret;
        }

        public boolean hasNext() {
            return stack.size() > 0;
        }
    }

    class BSTIterator1 {
        LinkedList<TreeNode> stack;
        TreeNode curr;

        public BSTIterator1(TreeNode root) {
            stack = new LinkedList<>();
            curr = root;
        }

        public int next() {
            int ret = -1;
            while (curr != null) {
                stack.addLast(curr);
                curr = curr.left;
            }
            TreeNode q = stack.removeLast();
            ret = q.val;
            curr = q.right;
            return ret;
        }

        public boolean hasNext() {
            return stack.size() > 0 || curr != null;
        }
    }
}
