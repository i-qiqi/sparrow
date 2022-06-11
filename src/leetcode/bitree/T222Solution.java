package leetcode.bitree;

public class T222Solution {
    class Solution {
        public int countNodes(TreeNode root) {
            if (root == null) return 0;

            TreeNode lp = root, rp = root;
            int llen = 0, rlen = 0;
            while (lp != null) {
                lp = lp.left;
                llen++;
            }

            while (rp != null) {
                rp = rp.right;
                rlen++;
            }

            if (llen == rlen) {
                return (int) Math.pow(2, llen) - 1;
            }

            return 1 + countNodes(root.left) + countNodes(root.right);
        }


    }

    /**
     * 不容易写
     * 1 A << nbits
     * 2 (num & mask) == mask
     */
    class Solution1 {
        int depth = 0;

        public int countNodes(TreeNode root) {
            if (root == null) return 0;
            // if(root.left == null) return 1;
            // if(root.right == null) return 2;

            int low = 1, high = 1;
            TreeNode p = root;
            while (p.left != null) {
                depth++;
                p = p.left;
                low = low << 1;
            }

            high = ((low - 1) << 1) + 1;

            return search(low, high, root);
        }

        public int search(int low, int high, TreeNode root) {
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (exists(mid, root)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }

            return low;
        }

        public boolean exists(int code, TreeNode p) {
            int mask = 1 << depth - 1;
            while (mask > 0) {
                if ((code & mask) == mask) {
                    p = p.right;
                } else {
                    p = p.left;
                }

                mask = mask >> 1;
            }

            return p != null;
        }
    }

    public static void main(String[] args) {
        System.out.println(7 << 1);
    }
}
