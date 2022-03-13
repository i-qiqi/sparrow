package leetcode.bitree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T113Solution {
    class Solution {
        LinkedList<List<Integer>> res;
        int target;

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            res = new LinkedList<>();
            target = targetSum;
            dfs(root, 0, new LinkedList<>());
            return res;
        }

        private void dfs(TreeNode root, int sum, LinkedList<Integer> path) {
            if (root == null)
                return;
            if (root.left == null && root.right == null && sum + root.val == target) {
                path.add(root.val);
                res.add(new ArrayList<>(path));
                path.removeLast();
                return;
            }

            if (root.left != null) {
                path.addLast(root.val);
                dfs(root.left, sum + root.val, path);
                path.removeLast();
            }

            if (root.right != null) {
                path.addLast(root.val);
                dfs(root.right, sum + root.val, path);
                path.removeLast();

            }

        }
    }

    class Solution1 {
        LinkedList<List<Integer>> res;
        int target;
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            res = new LinkedList<>();
            target = targetSum;
            dfs(root, 0, new LinkedList<>());
            return res;
        }
    
        private void dfs(TreeNode root, int sum, LinkedList<Integer> path){
            if (root == null) return;
    
            sum += root.val;
            path.add(root.val);
            if(sum == target && root.left == null && root.right == null){
                res.add(new ArrayList<>(path));
            }
    
            dfs(root.left, sum, path);
            dfs(root.right, sum, path);
            path.removeLast();
        }
    }
}
