package leetcode.bitree;

import java.util.ArrayList;
import java.util.List;

public class T144Solution {
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            TreeNode p = root;
            TreeNode q;
            List<Integer> res = new ArrayList<>();
            while (p != null) {
                q = p.left;
                if (q == null) {
                    //访问节点
                    res.add(p.val);
                    p = p.right;
                } else {
                    while (q.right != null && q.right != p) {
                        q = q.right;
                    }
                    //建立回溯指针
                    if (q.right == null) {
                        q.right = p;
                        //访问节点
                        res.add(p.val);
                        p = p.left;
                    }
                    //检测到环, 断开回溯指针
                    if (q.right == p) {
                        q.right = null;
                        p = p.right;
                    }
                }
            }
            return res;
        }
    }
}
