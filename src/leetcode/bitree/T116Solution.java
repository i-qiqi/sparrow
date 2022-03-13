package leetcode.bitree;

import java.util.LinkedList;
import java.util.Queue;

public class T116Solution {
    class Solution {
        public Node connect(Node root) {
            if (root == null)
                return root;
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            int size = 1;
            Node dummyNode = new Node(-1);
            while (size > 0) {
                Node p = dummyNode;
                for (int i = 0; i < size; i++) {
                    Node cur = q.poll();
                    if (cur.left != null) {
                        q.offer(cur.left);
                    }
                    if (cur.right != null) {
                        q.offer(cur.right);
                    }
                    p.next = cur;
                    p = cur;
                }
                p.next = null;
                size = q.size();
            }
            dummyNode = null;
            return root;
        }
    }
}
