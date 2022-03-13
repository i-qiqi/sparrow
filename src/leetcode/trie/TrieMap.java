package leetcode.trie;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.rtf.RTFEditorKit;

public class TrieMap<V> {
    private static final int R = 256;

    private int size = 0;

    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    private TrieNode<V> root = null;

    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c];
        }

        return p;
    }

    public V get(String key) {
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null) {
            return null;
        }

        return x.val;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public boolean hasKeyWithPrefix(String prefix) {
        return getNode(root, prefix) != null;
    }

    // 在所有键种寻找和query匹配的最短前缀
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                return "";
            }

            if (p.val != null) {
                return query.substring(0, i);
            }

            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null && p.val != null) {
            return query;
        }

        return "";
    }

    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;

        int max_len = 0;

        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                break;
            }
            if (p.val == null) {
                max_len = i;
            }

            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null && p.val != null) {
            return query;
        }

        return query.substring(0, max_len);
    }

    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) {
            return res;
        }

        traverse(x, new StringBuilder(), res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {
            return;
        }

        if (node.val != null) {
            res.add(path.toString());
        }

        for (char c = 0; c < R; c++) {
            path.append(c);
            traverse(node.children[c], path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // private void keysWithPattern(String pattern) {
    // }

    public void put(String key, V val) {
        if (!containsKey(key)) {
            size++;
        }

        root = put(root, key, val, 0);
    }

    public TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            node = new TrieNode<>();
        }

        if (i == key.length()) {
            node.val = val;
            return node;
        }

        char c = key.charAt(i);
        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;

    }

    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        remove(root, key, 0);
        size--;

    }

    //比较复杂
    private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
        if (node == null)
            return null;

        if (i == key.length()) {
            // 找到了key对应的TrieNode, 删除val
            node.val = null;
        } else {
            char c = key.charAt(i);
            node.children[c] = remove(node.children[c], key, i + 1);
        }

        // 后序位置递归路径上的节点，可能需要被清理
        if (node.val != null) {
            return node;
        }

        // 检查改TrieNode 是否还有后缀
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                return node;
            }
        }

        // 既没有存储val, 也没后缀树枝，则该节点需要被清理
        return null;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Hashtable table = new Hashtable<>();
        HashMap map = new HashMap<>();
        String s = "";
        s.equals("");
        setup-x86_64 -q -P wget -P gcc-g++ -P make -P diffutils -P libmpfr-devel -P libgmp-devel -P libmpc-devel


    }
}



