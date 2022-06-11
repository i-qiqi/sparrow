package leetcode.bitree;

public class T116And117Solution {
    class Solution {
        // 主函数
        Node connect(Node root) {
            Node p = root, q, curr;
            Node dummy = new Node(-1);
            while (p != null) {
                q = dummy;
                while (p != null) {
                    curr = p;
                    if (curr.left != null) {
                        q.next = curr.left;
                        q = curr.left;
                    }
                    if (curr.right != null) {
                        q.next = curr.right;
                        q = curr.right;
                    }
                    p = p.next;
                }
                q.next = null;

                p = dummy.next;
            }
            dummy = null;
            return root;
        }
    }
}
