package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T78Solution {

    class Solution0 {
        List<List<Integer>> res;

        public List<List<Integer>> subsets(int[] nums) {
            res = new ArrayList<>();
            dfs(nums, 0, new ArrayList<>());
            return res;
        }

        void dfs(int[] nums, int i, List<Integer> path) {
            if (i == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            // 做选择
            // 不选i
            dfs(nums, i + 1, path);
            // 选i
            path.add(nums[i]);
            dfs(nums, i + 1, path);
            path.remove(path.size() - 1);
        }

    }

    // 树高度低些，快些
    class Solution1 {

        LinkedList<List<Integer>> res;

        public List<List<Integer>> subsets(int[] nums) {
            res = new LinkedList<>();
            dfs(nums, 0, new LinkedList<>());
            return res;
        }

        private void dfs(int[] nums, int start, LinkedList<Integer> path) {
            res.add(new LinkedList<>(path));
            for (int i = start; i < nums.length; i++) {
                path.addLast(nums[i]);
                // 不是传start
                dfs(nums, i + 1, path);
                path.removeLast();
            }
        }

    }

    // 用ArrayList效率高点
    class Solution2 {
        List<List<Integer>> res;

        public List<List<Integer>> subsets(int[] nums) {
            res = new ArrayList<>();
            dfs(nums, 0, new ArrayList<>());
            return res;
        }

        private void dfs(int[] nums, int start, ArrayList<Integer> path) {
            res.add(new ArrayList<>(path));
            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                // 不是传start
                dfs(nums, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }

    // T90 可重复
    class Solution3 {
        List<List<Integer>> res;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            res = new ArrayList<>();
            dfs(nums, 0, new ArrayList<>());
            return res;
        }

        private void dfs(int[] nums, int start, ArrayList<Integer> path) {
            res.add(new ArrayList<>(path));
            // int preNum = Integer.MIN_VALUE; 没必要多定义一个变量
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1])
                    continue;
                // if(preNum == nums[i]) continue;
                path.add(nums[i]);
                // 不是传start
                dfs(nums, i + 1, path);
                path.remove(path.size() - 1);
                // preNum = nums[i];
            }
        }
    }

    // T77
    class Solution4 {
        LinkedList<List<Integer>> res;
        int len;

        public List<List<Integer>> combine(int n, int k) {
            res = new LinkedList<>();
            len = k;
            dfs(n, 1, new LinkedList<>());
            return res;
        }

        void dfs(int n, int start, LinkedList<Integer> path) {
            if (path.size() == len) {
                res.add(new LinkedList<>(path));
            }

            for (int i = start; i <= n; i++) {
                path.addLast(i);
                dfs(n, i + 1, path);
                path.removeLast();
            }
        }
    }

}
