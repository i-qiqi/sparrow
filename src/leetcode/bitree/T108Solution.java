package leetcode.bitree;

/**
 * 类似T109
 */
public class T108Solution {
    class Solution {
        int curr = 0;

        public TreeNode sortedArrayToBST(int[] nums) {
            return buildTree(0, nums.length - 1, nums);
        }

        public TreeNode buildTree(int low, int high, int[] nums) {
            if (low > high) return null;
            int mid = low + (high - low + 1) / 2;
            TreeNode left = buildTree(low, mid - 1, nums);
            TreeNode root = new TreeNode(nums[curr]);
            root.left = left;
            curr++;
            root.right = buildTree(mid + 1, high, nums);
            return root;
        }
    }
}
