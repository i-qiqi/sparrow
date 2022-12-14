package leetcode.hot100;

import java.util.*;

public class T49Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> mp = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            int[] cnts = new int[26];

            for (int j = 0; j < s.length(); j++) {
                cnts[s.charAt(j) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < 26; k++) {
                if (cnts[k] != 0) {
                    sb.append(k + 'a');
                    sb.append(cnts[k]);
                }
            }
            List<String> group = mp.getOrDefault(sb.toString(), new ArrayList<>());
            group.add(s);
            mp.putIfAbsent(sb.toString(), group);
        }

        List<List<String>> ans = new ArrayList<>();
        mp.values().forEach(e -> {
            ans.add(e);
        });
        return ans;
    }

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            LinkedList<List<String>> res = new LinkedList<>();
            boolean isHaveGroup = false;
            for (String s : strs) {
                isHaveGroup = false;
                for (List<String> group : res) {
                    if (isAnaGram(group.get(0), s)) {
                        isHaveGroup = true;
                        group.add(s);
                    }
                }
                if (!isHaveGroup) {
                    List<String> g = new ArrayList<>();
                    g.add(s);
                    res.addLast(g);
                }
            }
            return res;
        }

        public boolean isAnaGram(String a, String b) {
            int[] charCnt = new int[26];

            if (a.length() != b.length()) return false;
            int cnt = 0;
            for (int i = 0; i < a.length(); i++) {
                charCnt[a.charAt(i) - 'a']++;
                if (charCnt[a.charAt(i) - 'a'] == 1) {
                    cnt++;
                }
            }

            for (int j = 0; j < b.length(); j++) {
                charCnt[b.charAt(j) - 'a']--;
                if (charCnt[b.charAt(j) - 'a'] == 0) {
                    cnt--;
                }
            }

            return cnt == 0;
        }


    }

    /**
     *  string sort ?????????key
     */
    class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            //?????????????????????????????????
            if (strs == null || strs.length == 0) {
                return new ArrayList();
            }
            //1.?????????????????????
            Map<String, List> map = new HashMap<String, List>();
            for (String s : strs) {
                //?????????????????????????????????
                char[] chars = s.toCharArray();
                //???????????????????????????????????????
                Arrays.sort(chars);
                //?????????????????????????????????????????????key???
                String key = String.valueOf(chars);
                //2.??????????????????????????????key???
                if (!map.containsKey(key)) {
                    //?????????????????????????????????????????????map????????????????????????
                    map.put(key, new ArrayList());
                }
                //3.???????????????????????????key???list???
                map.get(key).add(s);
            }
            //??????map??????????????????????????????list
            return new ArrayList(map.values());
        }
    }

    public static void main(String[] args) {
        String[] strs = {"bdddddddddd", "bbbbbbbbbbc"};

        T49Solution solution = new T49Solution();

        // solution.groupAnagrams(strs);
        Map<Character, Integer> cntMap = new HashMap<>();
        cntMap.put('a', 10);
        cntMap.put('b', 3);
        System.out.println(cntMap.toString());

    }

}
