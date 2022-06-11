package leetcode.swordoffer;

public class SO36Solution {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**
     * 愚蠢的递归
     */
    class Solution {
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;

            Node head = root;
            Node left = root.left, right = root.right;
            Node leftHead = treeToDoublyList(left);

            if (leftHead == null) {
                head.left = head;
                head.right = head;
            } else {
                insert(leftHead, root);
                head = leftHead;
            }

            Node rightHead = treeToDoublyList(right);
            if (rightHead != null) {
                //合并head 和 rightHead
                Node tail = head.left, rightTail = rightHead.left;
                head.left = rightTail;
                rightTail.right = head;

                rightHead.left = tail;
                tail.right = rightHead;
                rightHead = null;
            }
            return head;
        }

        public void insert(Node prev, Node p) {
            prev.left.right = p;
            p.left = prev.left;
            prev.left = p;
            p.right = prev;
        }
    }

    /**
     * inorder
     */
    class Solution1 {
        Node head;
        Node prev;
        public Node treeToDoublyList(Node root) {
            if(root == null) return null;
            traverse(root);
            prev.right = head;
            head.left = prev;
            return head;
        }

        public void traverse(Node root){
            if(root == null) return ;
            traverse(root.left);
            if(prev == null){
                head = root;
            } else {
                prev.right = root;
                root.left = prev;
            }
            prev = root;

            traverse(root.right);
        }
    }
}
