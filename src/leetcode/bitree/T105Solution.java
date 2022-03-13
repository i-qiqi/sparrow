package leetcode.bitree;

public class T105Solution {

    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return create(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode create(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {

            int rootVal = preOrder[preStart];

            int k = -1;
            for (int i = inStart; i <= inEnd; i++) {
                if (inOrder[i] == rootVal) {
                    k = i;
                    break;
                }
            }

            TreeNode root = new TreeNode(rootVal);

            int leftLen = k - inStart;
            int rightLen = inEnd - k;
            if (leftLen > 0) {
                root.left = create(preOrder, preStart + 1, preStart + leftLen, inOrder, inStart, k - 1);
            }

            if (rightLen > 0) {
                root.right = create(preOrder, preStart + leftLen + 1, preEnd, inOrder, k + 1, inEnd);
            }

            return root;
        }
    }

}
