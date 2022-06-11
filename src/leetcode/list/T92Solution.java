package leetcode.list;

/**
 * 1 定位前驱prev, 后继succ
 * 2 头插法: head, q, t,  卸货暂存：t = q.next; 接后再接前：q.next = head.next; head.next = q; 下一位：q = t;
 */
public class T92Solution {
    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode p = dummy;
            int cnt = 0;
            ListNode prev = null, succ = null;
            while (p.next != null) {
                cnt++;
                if (cnt == left) {
                    prev = p;
                }

                if (cnt == right) {
                    succ = p.next.next;
                    break;
                }
                p = p.next;
            }


            //reverse
            ListNode q = prev.next;
            prev.next = succ; //TODO attention: 切断接上后面的
            while (q != succ) {
                ListNode t = q.next;
                q.next = prev.next;
                prev.next = q;
                q = t;
            }
            return dummy.next;
        }
    }
}
