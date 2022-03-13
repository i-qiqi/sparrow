package leetcode.bitree;

public class T106Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return create(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode create(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {

        TreeNode root = new TreeNode(postOrder[postEnd]);

        int k = -1;

        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == root.val) {
                k = i;
                break;
            }
        }

        int leftLen = k - inStart;
        int rightLen = inEnd - k;

        if (leftLen > 0) {
            root.left = create(inOrder, inStart, k - 1, postOrder, postStart, postStart + leftLen - 1);
        }

        if (rightLen > 0) {
            root.right = create(inOrder, k + 1, inEnd, postOrder, postStart + leftLen, postEnd - 1);
        }

        return root;

    }

}
