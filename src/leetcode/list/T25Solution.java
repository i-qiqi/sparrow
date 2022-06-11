package leetcode.list;

public class T25Solution {

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) return null;
            ListNode first = head, second = head;

            int cnt = 0;
            while (second != null) {
                cnt++;
                if (cnt == k) break;
                if (second.next == null) return head; // 小于k个节点
                second = second.next;
            }
            ListNode t = second.next;
            second.next = null;
            second = t;

            ListNode newHead = reverse(first);
            first.next = reverseKGroup(second, k);
            return newHead;
        }


        private ListNode reverseAll(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return head;
            ListNode p = head.next;
            ListNode newHead = reverseAll(p);
            p.next = head;
            head.next = null;
            return newHead;
        }

        private ListNode reverse(ListNode head) {
            if (head == null) return null;
            ListNode dummy = new ListNode(-1);
            ListNode p = head, q;
            while (p != null) {
                ListNode t = dummy.next;
                dummy.next = p;
                q = p.next; // 暂存翻转的后半部分
                p.next = t;
                p = q;
            }
            return dummy.next;
        }
    }

    /**
     * super star
     * // 找前驱和后继
     */
    class Solution1 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode prev = dummy;
            ListNode p = prev;
            int cnt = 0;
            ListNode t = null;
            while (p.next != null) {
                cnt++;
                if (cnt == k) {
                    t = prev.next; //TODO: attention
                    reverse(prev, p.next.next);
                    prev = t;
                    cnt = 0;
                    p = prev; //TODO: attention
                    continue;
                }
                p = p.next;
            }
            return dummy.next;
        }

        public void reverse(ListNode prev, ListNode succ) {
            ListNode q = prev.next, t = null;
            prev.next = succ;
            while (q != succ) {
                t = q.next;
                q.next = prev.next;
                prev.next = q;
                q = t;
            }
        }
    }
}
