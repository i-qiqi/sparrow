package leetcode.list;

public class T206Solution {

    class Solution {
        //头插法
        public ListNode reverseList(ListNode head) {
            ListNode dummy = new ListNode(-1);

            ListNode p = head;
            ListNode q;
            while (p != null) {
                q = p.next;
                p.next = dummy.next;
                dummy.next = p;
                p = q;
            }

            return dummy.next;
        }
    }
}
