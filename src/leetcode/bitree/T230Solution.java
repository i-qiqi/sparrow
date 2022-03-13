package leetcode.bitree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class T230Solution {

    // 非递归查找kth num
    class Solution1 {
        public int kthSmallest(TreeNode root, int k) {
            LinkedList<TreeNode> stack = new LinkedList<>();

            TreeNode p = root;

            while (!stack.isEmpty() || p != null) {
                while (p != null) {
                    stack.addFirst(p);
                    p = p.left;
                }

                p = stack.removeFirst();
                if (--k == 0) {
                    return p.val;
                }
                p = p.right;
            }

            return 0;
        }
    }

    class MyTreeNode {
        TreeNode node;
        int size;
        MyTreeNode left;
        MyTreeNode right;

        MyTreeNode(TreeNode node) {
            this.node = node;
        }
    }

    // 如果频繁查询第k小值 , size 存节点数
    class Solution2 {
        public int kthSmallest(TreeNode root, int k) {
            MyTreeNode myRoot = new MyTreeNode(root);
            cntNodes(myRoot);
            MyTreeNode p = myRoot;
            // System.out.println(p.size);
            // System.out.println(p.left.size);
            while (p != null) {
                if (p.left.size == k - 1) {
                    return p.node.val;
                } else if (p.left.size > k - 1) {
                    p = p.left;
                } else {
                    k = k - (p.left.size + 1);
                    p = p.right;
                }
            }

            return 0;
        }

        private int cntNodes(MyTreeNode root) {
            if (root.node == null)
                return 0;

            MyTreeNode leftNode = new MyTreeNode(root.node.left);
            MyTreeNode rightNode = new MyTreeNode(root.node.right);
            int leftCnt = cntNodes(leftNode);
            int rightCnt = cntNodes(rightNode);
            root.size = leftCnt + rightCnt + 1;
            root.left = leftNode;
            root.right = rightNode;
            return root.size;
        }
    }

    // size 存节点
    class Solution3 {
        public int kthSmallest(TreeNode root, int k) {
            MyTreeNode myRoot = new MyTreeNode(root);
            recordRanking(myRoot, new int[1]);
            MyTreeNode p = myRoot;

            while (p != null) {
                if (p.size == k) {
                    return p.node.val;
                } else if (p.size > k) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }

            return 0;
        }

        private void recordRanking(MyTreeNode root, int[] rank) {
            if (root.node == null)
                return;
            MyTreeNode leftNode = new MyTreeNode(root.node.left);
            recordRanking(leftNode, rank);
            rank[0]++;
            root.size = rank[0];
            MyTreeNode rightNode = new MyTreeNode(root.node.right);
            recordRanking(rightNode, rank);

            root.left = leftNode;
            root.right = rightNode;
        }
    }

    // Best answer
    class Solution4 {

        Map<TreeNode, Integer> rankMap;

        public int kthSmallest(TreeNode root, int k) {
            rankMap = new HashMap<>();
            recordRanking(root, new int[1]);
            TreeNode p = root;

            while (p != null) {
                int rank = rankMap.getOrDefault(p, 0);
                if (rank == k) {
                    return p.val;
                } else if (rank > k) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }

            return 0;
        }

        private void recordRanking(TreeNode root, int[] rank) {
            if (root == null)
                return;
            recordRanking(root.left, rank);
            rank[0]++;
            rankMap.put(root, rank[0]);
            recordRanking(root.right, rank);
        }
    }

}
