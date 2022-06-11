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

    class Solution1 {
        TreeNode prev;

        public void flatten(TreeNode root) {
            preOrder(root);
        }

        void preOrder(TreeNode root) {
            if (root == null) return;
            TreeNode tempRight = root.right;
            if (prev != null) {
                prev.right = root;
                prev.left = null;
            }
            prev = root;
            preOrder(root.left);
            preOrder(tempRight);
        }

    }

    /**
     * O(n)
     * O(1)
     */
    class Solution2 {
        //morris 遍历
        public void flatten(TreeNode root) {
            TreeNode p = root;
            TreeNode left, right, t;
            while (p != null) {
                right = p.right;
                left = p.left;
                if (left == null) {
                    p = right;
                } else {
                    //修改左子树最有节点right指针
                    t = left;
                    while (t.right != null) {
                        t = t.right;
                    }
                    t.right = right;
                    p.right = left;
                    p.left = null;
                    p = left;
                }
            }
        }
    }
}
