package leetcode.top200;

import java.util.LinkedList;

public class T297Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        // 先序遍历
        public String serialize(TreeNode root) {

            String[] s = new String[] { "" };
            preOrder(root, s);
            // 去掉最后，
            return s[0];

        }

        void preOrder(TreeNode root, String[] s) {
            if (root == null) {
                s[0] += "#,";
                return;
            }

            s[0] += root.val + ",";
            preOrder(root.left, s);
            preOrder(root.right, s);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            return create(new StringBuilder(data));

        }

        TreeNode create(StringBuilder sb) {
            // 解析val
            String currentVal = "";
            int i = 0;
            // System.out.println("sb : "+sb.toString());
            // 保留最后一个逗号，可以不判断边界
            while (sb.charAt(i) != ',') {
                currentVal += sb.charAt(i);
                i++;
            }

            // 删除前缀
            sb.delete(0, i + 1);
            // System.out.println("val : "+currentVal);

            if (currentVal.equals("#")) {
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(currentVal));

            root.left = create(sb);
            root.right = create(sb);
            return root;
        }

    }

    public class Codec1 {

        String SEP = ",";
        String NULL = "#";

        // Encodes a tree to a single string.
        // 先序遍历
        public String serialize(TreeNode root) {

            StringBuilder sb = new StringBuilder();
            preOrder(root, sb);
            // 去掉最后，
            return sb.toString();

        }

        void preOrder(TreeNode root, StringBuilder s) {
            if (root == null) {
                s.append(NULL).append(SEP);
                return;
            }

            s.append(root.val).append(SEP);
            preOrder(root.left, s);
            preOrder(root.right, s);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            String[] values = data.split(SEP);
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : values) {
                nodes.addLast(s);
            }

            return create(nodes);

        }

        TreeNode create(LinkedList<String> nodes) {
            // 解析val
            String first = nodes.removeFirst();

            if (first.equals(NULL)) {
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = create(nodes);
            root.right = create(nodes);
            return root;
        }

    }

    public static void main(String[] args) {
        String s = "-7";
        int x = Integer.parseInt(s);
        System.out.println(x);
    }

}
