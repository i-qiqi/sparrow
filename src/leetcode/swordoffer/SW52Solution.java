package leetcode.swordoffer;

import leetcode.list.ListNode;

public class SW52Solution {
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode p1 = headA, p2 = headB;
            if (p1 == null || p2 == null) return null; //boring。。。
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;

                if (p1 == null && p2 == null) break; // 不相交，你为什么要求相交
                if (p1 == null) p1 = headB;
                if (p2 == null) p2 = headA;
            }

            return p1;
        }
    }

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(s + (char)('a' + 1));
    }
}
