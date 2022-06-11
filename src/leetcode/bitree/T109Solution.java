package leetcode.bitree;

import leetcode.list.ListNode;

public class T109Solution {
    /**
     * O(nlog(n))的时间复杂度
     * 如何优化到O(n)
     */
    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return new TreeNode(head.val);

            ListNode prev = head, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;
            TreeNode root = new TreeNode(slow.val);
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(slow.next);
            return root;
        }

    }

    /**
     * 官方写法，没有用prev
     */
    class Solution2 {
        public TreeNode sortedListToBST(ListNode head) {
            return buildTree(head, null);
        }

        public TreeNode buildTree(ListNode left, ListNode right) {
            if (left == right) {
                return null;
            }
            ListNode mid = getMedian(left, right);
            TreeNode root = new TreeNode(mid.val);
            root.left = buildTree(left, mid);
            root.right = buildTree(mid.next, right);
            return root;
        }

        public ListNode getMedian(ListNode left, ListNode right) {
            ListNode fast = left;
            ListNode slow = left;
            while (fast != right && fast.next != right) {
                fast = fast.next;
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
    }

    /**
     * 借用中序遍历，去掉取中位数的逻辑 => O(n)
     */
    class Solution3 {
        ListNode curr;

        public TreeNode sortedListToBST(ListNode head) {
            curr = head;
            int len = getLength(head);
            return traverse(0, len - 1);
        }

        public TreeNode traverse(int low, int high) {
            if (low > high) return null;
            // int mid = low + (high - low) / 2; // 左倾
            int mid = low + (high - low + 1) / 2; //右倾
            TreeNode left = traverse(low, mid - 1);
            TreeNode root = new TreeNode(curr.val);
            root.left = left;
            curr = curr.next;
            root.right = traverse(mid + 1, high);
            return root;
        }

        public int getLength(ListNode head) {
            int len = 0;
            ListNode p = head;
            while (p != null) {
                p = p.next;
                len++;
            }

            return len;
        }

    }
}
