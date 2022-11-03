package leetcode.binaryindexedtree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class T493Solution {

    class Solution {
        public int reversePairs(int[] nums) {
            Set<Long> allNumbers = new TreeSet<>();

            for (long x : nums) {
                allNumbers.add(x);
                allNumbers.add(2 * x);
            }

            Map<Long, Integer> values = new HashMap<>();

            int idx = 0;
            for (long x : allNumbers) {
                values.put(x, idx);
                idx++;
            }

            int ret = 0;
            BinaryIndexedTree bit = new BinaryIndexedTree(values.size());
            for (int i = 0; i < nums.length; i++) {
                int left = values.get((long) nums[i] * 2), right = values.size() - 1;
                ret += bit.query(right + 1) - bit.query(left + 1);
                bit.update(values.get((long) nums[i]) + 1, 1);
            }

            return ret;
        }


    }

    public static class BinaryIndexedTree {
        int[] tree;
        int n;

        public BinaryIndexedTree(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public static int lowbit(int x) {
            return x & (-x);
        }

        public void update(int x, int d) {
            while (x <= n) {
                tree[x] += d;
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= lowbit(x);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>((x, y) -> y - x);

        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(12);

        for (int x : treeSet) {
            System.out.println("x = " + x);
        }
    }
}
