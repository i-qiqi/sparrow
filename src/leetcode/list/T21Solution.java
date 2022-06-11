package leetcode.list;

public class T21Solution {
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode newHead = new ListNode(-1);

            ListNode p = list1, q = list2, t = newHead;
            while (p != null && q != null) {
                if (p.val <= q.val) {
                    t.next = p;
                    p = p.next;
                } else {
                    t.next = q;
                    q = q.next;
                }
                t = t.next;
            }

            if (p != null) t.next = p;
            if (q != null) t.next = q;

            return newHead.next;
        }


    }
}
