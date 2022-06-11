package leetcode.random;

import leetcode.list.ListNode;

import java.util.Random;

public class T382Solution {
    class Solution {
        ListNode head;
        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            ListNode p = head;
            Random rand = new Random();
            int i = 0, k = 1, x = 0;
            int ret = Integer.MIN_VALUE;
            while(p != null){
                //1/i 保留， 1-1/i丢弃
                i++;
                x = rand.nextInt(i - 1 + 1) + 1; //[1, i]
                if(x <= k){
                    ret = p.val;
                }
                p = p.next;
            }
            return ret;
        }
    }
}
