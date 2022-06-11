package leetcode.list;

/**
 * @环形链表
 * 1 找相遇的节点x
 * 2 分别从x和head出发，直到再次相遇
 *
 * 思考：
 * 1 为什么slow和fast相遇时，slow没有跑满一圈
 * 2 快慢指针时间复杂度是多少
 * 3 为什么速度是2倍，不能选3、4、5么？
 */
public class T142Solution {
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head, fast = head;

            //TODO attention: 结束条件容易出错
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    break;
                }
            }

            if (fast == null || fast.next == null) return null;

            //slow == fast
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }
    }
}
