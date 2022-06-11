package leetcode.random;

import leetcode.bitree.TreeNode;

import java.util.*;

public class T662Solution {
    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            int levelSize = 1;
            Map<TreeNode, Integer> memo = new HashMap<>();
            memo.put(root, 1);
            int levelMinCode = 0;
            int ans = 0;
            while (q.size() > 0) {
                for (int i = 0; i < levelSize; i++) {
                    TreeNode curr = q.poll();
                    int currCode = memo.get(curr);
                    if (curr.left != null) {
                        q.offer(curr.left);
                        memo.put(curr.left, 2 * currCode);
                    }

                    if (curr.right != null) {
                        q.offer(curr.right);
                        memo.put(curr.right, 2 * currCode + 1);
                    }

                    if (i == 0) {
                        levelMinCode = currCode;
                    }

                    if (i == levelSize - 1) {
                        ans = Math.max(currCode - levelMinCode + 1, ans);
                    }

                    memo.remove(curr);
                }

                levelSize = q.size();
            }

            return ans;
        }
    }

    /**
     * 前序遍历的好例子
     */
    class Solution2 {

        List<Integer> leftCodes;
        int maxWidth = 1;
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.w, o1.w));

        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            leftCodes = new ArrayList<>();
            traverse(root, 1, 1);
            return maxWidth;
        }

        public void traverse(TreeNode root, int code, int depth) {
            if (root == null) return;
            // do something
            if (depth <= leftCodes.size()) {
                maxWidth = Math.max(maxWidth, code - leftCodes.get(depth - 1) + 1);
            } else {
                leftCodes.add(code);
            }
            traverse(root.left, 2 * code, depth + 1);
            traverse(root.right, 2 * code + 1, depth + 1);
        }

    }
    class Pair{
        int u, v, w;
        public Pair(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
