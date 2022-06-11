package leetcode.list;

/**
 * T25的特殊情况
 */
public class T24Solution {
    class Solution {
        public ListNode swapPairs(ListNode head) {
            return reverseKGroup(head, 2);
        }

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
