package leetcode.bitree;

import java.util.LinkedList;

public class T538Solution {

    class Solution {
        public TreeNode convertBST(TreeNode root) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            TreeNode p = root;
            int sum = 0;
            while (!stack.isEmpty() || p != null) {
                while (p != null) {
                    stack.addFirst(p);
                    p = p.right;
                }

                p = stack.removeFirst();
                p.val = p.val + sum;
                sum = p.val;
                p = p.left;
            }

            return root;
        }

    }

}
