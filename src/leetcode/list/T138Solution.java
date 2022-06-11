package leetcode.list;

public class T138Solution {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            Node p = head;
            Node q = null;
            //copy
            while (p != null) {
                q = new Node(p.val);
                q.next = p.next;
                p.next = q;
                p = q.next;
            }

            // update random pointer
            p = head;
            while (p != null) {
                q = p.next;
                if (p.random != null) {
                    q.random = p.random.next;
                } else {
                    q.random = null;
                }
                p = q.next;
            }

            //separate into two list
            p = head;
            Node randomHead = new Node(-1);
            q = randomHead;
            while (p != null) {
                q.next = p.next;
                q = q.next;
                p.next = q.next;
                p = p.next;
            }

            q.next = null;
            return randomHead.next;
        }
    }
}
