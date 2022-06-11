package leetcode.bitree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @二叉树的锯齿形层序遍历
 * 双层循环，更新size
 * while size > 0 :
 *  for i : 0 -> size
 *      poll front node
 *      offer children
 *
 *  size = q.size()
 *  collect current layer nodes
 */
public class T103Solution {

    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if(root == null) return  new LinkedList<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int size = q.size();
            Boolean isReverse = false;
            List<List<Integer>> res = new ArrayList<>();
            while(size > 0){
                LinkedList<Integer> tmpList = new LinkedList<>();
                for(int i = 0; i < size; i++){
                    TreeNode p = q.poll();
                    if(isReverse == false) {
                        tmpList.addLast(p.val);
                    } else {
                        tmpList.addFirst(p.val);
                    }
                    if(p.left != null) {
                        q.offer(p.left);
                    }
                    if(p.right != null) {
                        q.offer(p.right);
                    }
                }
                size = q.size();
                res.add(tmpList);
                isReverse = !isReverse;
            }

            return res;
        }

    }
}
