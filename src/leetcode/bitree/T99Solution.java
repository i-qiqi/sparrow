package leetcode.bitree;

public class T99Solution {
    /**
     * O(log(n))
     */
    class Solution {
        TreeNode prev = null;
        TreeNode p, q;

        public void recoverTree(TreeNode root) {
            dfs(root);
            swap(p, q);
        }

        public void dfs(TreeNode root) {
            if (root == null) {
                return;
            }

            dfs(root.left);
            if (prev != null) {
                if (p == null && q == null && prev.val > root.val) {
                    p = prev;
                    q = root;
                }
                if (p != null && prev.val > root.val) {
                    q = root;
                }

            }
            prev = root;
            dfs(root.right);
        }

        public void swap(TreeNode p1, TreeNode p2) {
            int temp = p1.val;
            p1.val = p2.val;
            p2.val = temp;
        }
    }

    /**
     * morris遍历
     * O(1) 空间复杂度
     */
    class Solution2 {
        TreeNode prev = null;
        TreeNode first = null, second = null;

        public void recoverTree(TreeNode root) {

            TreeNode p = root;
            TreeNode q;
            while (p != null) {
                q = p.left;
                if (q == null) {
                    visit(p);
                    p = p.right;
                } else {
                    while (q.right != null && q.right != p) {
                        q = q.right;
                    }
                    //建立回溯指针
                    if (q.right == null) {
                        q.right = p;
                        p = p.left;
                    }
                    //检测到环, 断开回溯指针
                    if (q.right == p) {
                        visit(p);
                        q.right = null;
                        p = p.right;
                    }
                }
            }

            swap(first, second);
        }

        public void visit(TreeNode p) {
            //访问节点：
            // System.out.println("p = " + p.val);
            if (first == null && prev != null && prev.val > p.val) {
                first = prev;
            }
            if (first != null && prev.val > p.val) {
                second = p;
                // System.out.println("second = " + second.val);
            }
            prev = p;
        }

        public void swap(TreeNode p1, TreeNode p2) {
            int temp = p1.val;
            p1.val = p2.val;
            p2.val = temp;
        }
    }
}
