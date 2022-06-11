package leetcode.trie;

public class T211Solution {
    class WordDictionary {

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) {
                    p.children[c - 'a'] = new TrieNode();
                }

                if (i == word.length() - 1 && p.children[c - 'a'].isEnd == false) {
                    p.children[c - 'a'].isEnd = true;
                }

                p = p.children[c - 'a']; // 不要漏
            }
        }

        public boolean search(String word) {
            return search(root, word, 0);
        }

        public boolean search(TrieNode root, String word, int st) {
            if (st == word.length()) {
                return root.isEnd;
            }
            char c = word.charAt(st);
            TrieNode p;
            if (c == '.') {
                for (int k = 0; k < 26; k++) {
                    p = root.children[k];
                    if (p != null && search(p, word, st + 1)) {
                        return true;
                    }
                }
            } else {
                p = root.children[c - 'a'];
                if (p != null) {
                    return search(p, word, st + 1);
                }
            }

            return false;
        }
//        笨方法
//        public boolean search(TrieNode root, String word, int st){
//            if(st == word.length()) return true;
//            char c = word.charAt(st);
//            boolean ret;
//            TrieNode p;
//            if(c == '.'){
//                ret = false;
//                for(int k = 0; k < 26; k++){
//                    p = root.children[k];
//                    if(p == null) continue;
//                    if(st == word.length() - 1 && p.isEnd == false) continue;
//                    ret = ret || search(p, word, st+1);
//                }
//            } else {
//                p = root.children[c - 'a'];
//                if(st == word.length() - 1){
//                    ret = p != null && p.isEnd == true;
//                } else {
//                    ret = p != null;
//                }
//                // if(ret == false) return false; 没必要, p == null 的时候，ret = false
//                ret = ret && search(p, word, st+1); // 这里如果ret 是false, 不会继续递归
//            }
//
//            return ret;
//        }

        public class TrieNode {
            boolean isEnd;
            TrieNode[] children;

            public TrieNode() {
                this.isEnd = false;
                this.children = new TrieNode[26];
            }
        }
    }
}
