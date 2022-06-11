package leetcode.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T472Solution {
    class Solution {
        TrieNode trie;

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            //排序
            Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
            List<String> res = new ArrayList<>();
            trie = new TrieNode();
            for (int i = 0; i < words.length; i++) {
                // boolean[] visited = new boolean[words[i].length()];

                boolean isConcatenated = dfs(words[i], 0);
                if (words[i].length() == 0) continue;
                if (isConcatenated) {
                    res.add(words[i]);
                } else {
                    insert(words[i]);
                }
            }

            return res;
        }


        //dfs字典树
        public boolean dfs(String w, int st) {
            if (st == w.length()) {
                return true;
            }

            // if(visited[st]) return false;
            // visited[st] = true;
            TrieNode p = trie;
            for (int i = st; i < w.length(); i++) {
                char c = w.charAt(i);
                p = p.children[c - 'a'];
                if (p == null) {
                    return false;
                }

                if (p.isEnd && dfs(w, i + 1)) return true;
            }

            return false;
        }

        public void insert(String w) {
            TrieNode p = trie;

            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (p.children[c - 'a'] == null) {
                    p.children[c - 'a'] = new TrieNode();
                }

                if (i == w.length() - 1) {
                    p.children[c - 'a'].isEnd = true;
                }

                p = p.children[c - 'a'];
            }
        }

        class TrieNode {
            boolean isEnd;
            TrieNode[] children;

            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }
    }
}
