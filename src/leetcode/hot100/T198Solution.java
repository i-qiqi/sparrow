package leetcode.hot100;

import java.util.HashMap;

public class T198Solution {

    // T198
    class Solution {
        public int rob(int[] nums) {
            int[] dp = new int[nums.length];
            int ans = 0;
            dp[0] = nums[0];
            ans = dp[0];
            if (nums.length <= 1)
                return ans;
            dp[1] = Math.max(nums[0], nums[1]);
            ans = Math.max(ans, dp[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
                ans = Math.max(ans, dp[i]);
            }

            return ans;

        }
    }

    class Solution1 {
        public int rob(int[] nums) {
            // if ( nums.length == 1 ) return nums[0];
            // if (nums.length == 2) return Math.max(nums[0],nums[1]);
            // 如果偷最后1个，nums[1:len-3] + nums[length-1]最大值
            // 如果不偷最后1个，nums[0:len-2]

            return Math.max(rob(nums, 1, nums.length - 3) + nums[nums.length - 1], rob(nums, 0, nums.length - 2));

        }

        private int rob(int[] nums, int i, int j) {
            if (i > j)
                return 0; // 只能偷最后一个
            int[] dp = new int[nums.length];
            dp[i] = nums[i];
            int ans = 0;
            ans = dp[i];
            if (j == i)
                return dp[j];
            dp[i + 1] = Math.max(nums[i], nums[i + 1]);
            for (int k = i + 2; k <= j; k++) {
                dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
            }

            return dp[j];
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution2 {
        HashMap<TreeNode, Integer> mem;

        public int rob(TreeNode root) {
            mem = new HashMap<>();
            return dfs(root);
        }

        private int dfs(TreeNode root) {
            if (root == null)
                return 0;
            if (mem.containsKey(root))
                return mem.get(root);

            int ans1 = root.val, ans2 = 0;

            if (root.left != null) {
                ans1 += dfs(root.left.left) + dfs(root.left.right);
                ans2 += dfs(root.left);
            }

            if (root.right != null) {
                ans1 += dfs(root.right.left) + dfs(root.right.right);
                ans2 += dfs(root.right);
            }

            int ans = Math.max(ans1, ans2);
            mem.put(root, ans);
            return ans;
        }
    }

    // 337
    class SolutionNB {
        public int rob(TreeNode root) {
            int[] ans = dp(root);
            return Math.max(ans[0], ans[1]);
        }

        private int[] dp(TreeNode root) {
            if (root == null)
                return new int[] { 0, 0 };
            int[] left = dp(root.left);
            int[] right = dp(root.right);

            int rob = root.val + left[0] + right[0];
            int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

            return new int[] { notRob, rob };
        }
    }
}
