package leetcode.bitree;

public class T654Solution {
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return create(nums, 0, nums.length - 1);
        }

        private TreeNode create(int[] nums, int i, int j) {
            if (i > j)
                return null;
            int k = findMax(nums, i, j);

            TreeNode root = new TreeNode(nums[k]);
            TreeNode left = create(nums, i, k - 1);
            TreeNode right = create(nums, k + 1, j);

            root.left = left;
            root.right = right;
            return root;
        }

        private int findMax(int[] nums, int i, int j) {
            int maxIdx = i;
            int maxNum = nums[i];
            for (int k = i; k <= j; k++) {
                if (nums[k] > maxNum) {
                    maxIdx = k;
                    maxNum = nums[k];
                }
            }

            return maxIdx;
        }
    }
}
