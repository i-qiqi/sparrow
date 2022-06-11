package leetcode.hot100;

public class T4Solution {

    /**
     * 折半删除法
     */
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int len = nums1.length + nums2.length;
            if ((len & 1) == 1) {//odd 奇数
                return findKth(nums1, 0, nums2, 0, len / 2 + 1);
            } else { //even 偶数
                int a = findKth(nums1, 0, nums2, 0, len / 2);
                int b = findKth(nums1, 0, nums2, 0, len / 2 + 1);
                return (a + b) * 1.0 / 2;
            }

        }

        public int findKth(int[] nums1, int st1, int[] nums2, int st2, int k) {
            int len1 = nums1.length - st1;
            int len2 = nums2.length - st2;
            if (len2 == 0) return nums1[st1 + k - 1];
            if (len1 < len2) {
                return findKth(nums2, st2, nums1, st1, k);
            }

            if (k == 1) {
                return Math.min(nums1[st1], nums2[st2]);
            }

            // nums1 长 一些
            int halfIdx1 = st1 + k / 2 - 1;
            int halfIdx2 = Math.min(st2 + k / 2 - 1, nums2.length - 1);
            if (nums1[halfIdx1] <= nums2[halfIdx2]) {
                return findKth(nums1, halfIdx1 + 1, nums2, st2, k - k / 2);
            } else {
                return findKth(nums1, st1, nums2, halfIdx2 + 1, k - (halfIdx2 + 1 - st2));
            }
        }
    }
}
