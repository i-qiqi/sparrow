package leetcode.trie;

public class T208Solution {
    class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) {
                    p.children[c - 'a'] = new TrieNode();
                }

                if (i == word.length() - 1 && p.children[c - 'a'] != null && p.children[c - 'a'].val == null) {
                    p.children[c - 'a'].val = word;
                }


                p = p.children[c - 'a'];
            }
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) {
                    return false;
                }

                if (i == word.length() - 1 && p.children[c - 'a'] != null && !word.equals(p.children[c - 'a'].val)) {
                    return false;
                }

                p = p.children[c - 'a'];
            }

            return true;
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (p.children[c - 'a'] == null) {
                    return false;
                }

                p = p.children[c - 'a'];
            }

            return true;
        }

        public class TrieNode {
            String val;
            TrieNode[] children;

            public TrieNode() {
                this.children = new TrieNode[26];
            }

            public TrieNode(String val) {
                this.val = val;
                this.children = new TrieNode[26];
            }
        }
    }

    class Trie1 {

        TrieNode root;

        public Trie1() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) {
                    p.children[c - 'a'] = new TrieNode();
                }

                if (i == word.length() - 1 && p.children[c - 'a'] != null && p.children[c - 'a'].isEnd == false) {
                    p.children[c - 'a'].isEnd = true;
                }


                p = p.children[c - 'a'];
            }
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) {
                    return false;
                }

                if (i == word.length() - 1 && p.children[c - 'a'] != null && p.children[c - 'a'].isEnd == false) {
                    return false;
                }

                p = p.children[c - 'a'];
            }

            return true;
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (p.children[c - 'a'] == null) {
                    return false;
                }

                p = p.children[c - 'a'];
            }

            return true;
        }

        public class TrieNode {
            boolean isEnd;
            TrieNode[] children;

            public TrieNode() {
                this.children = new TrieNode[26];
            }

            public TrieNode(boolean isEnd) {
                this.isEnd = isEnd;
                this.children = new TrieNode[26];
            }
        }
    }
}
