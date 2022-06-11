package leetcode.list;

public class T148Solution {

    //写的不够好，请看标准答案
    class Solution {

        public ListNode sortList(ListNode head) {
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            mergeSort(dummyNode, null);
            return dummyNode.next;
        }

        public void mergeSort(ListNode low, ListNode high){
            if(low.next == high || low.next.next == high) return;
            //find mid
            ListNode slow = low, fast = low;
            while(fast != high && fast.next != high){
                slow = slow.next;
                fast = fast.next.next;
            }
            mergeSort(low, slow.next);
            mergeSort(slow, high);
            merge(low, slow, high);
        }

        public void merge(ListNode low, ListNode mid, ListNode high){
            ListNode p = low.next;
            ListNode q = mid.next;
            mid.next = null;
            ListNode t = low;
            while(p != null && q != high){
                if(p.val <= q.val){
                    t.next = p;
                    p = p.next;
                } else {
                    t.next = q;
                    q = q.next;
                }
                t = t.next;
            }

            //TODO attention: 数组需要逐个复制，但是链表只需链接就行
            if(p != null){
                t.next = p;
                mid.next = high;
            }
            if(q != high){
                t.next = q;
            }
        }
    }
}
