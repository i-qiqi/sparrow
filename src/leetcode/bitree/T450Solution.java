package leetcode.bitree;

/**
 * @删除二叉搜索树中的节点
 */
public class T450Solution {
    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {

            if (root == null) return null; // 不存在的情况

            if (root.val == key) {
                //找到root的右子树，最左节点
                TreeNode right = root.right;
                if (right == null) return root.left;
                TreeNode p = right;
                while (p.left != null) {
                    p = p.left;
                }
                p.left = root.left;
                root = null;
                return right;
            } else if (root.val > key) {
                TreeNode node = deleteNode(root.left, key);
                root.left = node;

            } else {
                TreeNode node = deleteNode(root.right, key);
                root.right = node;
            }

            return root;
        }
    }


}
