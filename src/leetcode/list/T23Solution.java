package leetcode.list;

public class T23Solution {
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            return mergeList(lists, 0, lists.length - 1);
        }

        public ListNode mergeList(ListNode[] lists, int low, int high) {
            if (low == high) return lists[low];
            //TODO: attention
            if (low > high) return null;
            int mid = low + (high - low) / 2;
            ListNode list1 = mergeList(lists, low, mid);
            ListNode list2 = mergeList(lists, mid + 1, high);
            ListNode head = merge(list1, list2);
            return head;
        }

        public ListNode merge(ListNode list1, ListNode list2) {
            ListNode p = list1, q = list2;
            ListNode tempHead = new ListNode(-1);
            ListNode t = tempHead;
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

            return tempHead.next;
        }
    }
}
