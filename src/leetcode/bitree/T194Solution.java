package leetcode.bitree;

import java.util.*;

public class T194Solution {
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            if (root == null) return res;
            Queue<TreeNode> q = new ArrayDeque<TreeNode>();
            q.offer(root);
            TreeNode curr;
            int size;
            while (q.size() > 0) {
                size = q.size();
                for (int i = 0; i < size; i++) {
                    curr = q.poll();
                    if (curr.left != null) {
                        q.offer(curr.left);
                    }
                    if (curr.right != null) {
                        q.offer(curr.right);
                    }
                    if (i == size - 1) {
                        res.add(curr.val);
                    }
                }
            }
            return res;
        }
    }

    /**
     * 哪里错了？
     */
    class Solution1 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            if(root == null) return res;
            TreeNode p = root;
            while(p != null){
                res.add(p.val);
                if(p.right == null){
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
            return res;
        }
    }

    /**
     * dfs 做法
     */
    class Solution2 {
        List<Integer> res;
        public List<Integer> rightSideView(TreeNode root) {
            res = new ArrayList<>();
            dfs(root,0);
            return res;
        }

        public void dfs(TreeNode root, int depth){
            if(root == null) return ;
            if(depth == res.size()){
                res.add(root.val);
            }
            dfs(root.right,depth+1);
            dfs(root.left, depth+1);
        }
    }
}
