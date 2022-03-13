package leetcode.bitree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 没想到序列化，这是一个技巧，需要记住
public class T652Solution {
    class Solution {

        static final String SEP = ",";
        static final String NULL = "NULL";
        Map<String, Integer> cntMap;
        List<TreeNode> res;

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            cntMap = new HashMap<>();
            res = new ArrayList<>();
            traverse(root);
            return res;
        }

        public String traverse(TreeNode root) {
            if (root == null)
                return NULL;

            String left = traverse(root.left);
            String right = traverse(root.right);

            String subtree = left + SEP + right + SEP + root.val;
            if (cntMap.getOrDefault(subtree, 0) == 1) {
                res.add(root);
            }
            cntMap.put(subtree, cntMap.getOrDefault(subtree, 0) + 1);

            return subtree;
        }
    }
}
