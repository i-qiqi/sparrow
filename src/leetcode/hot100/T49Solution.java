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
     *  string sort 后作为key
     */
    class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            //判断是否为空字符串数组
            if (strs == null || strs.length == 0) {
                return new ArrayList();
            }
            //1.创建一个哈希表
            Map<String, List> map = new HashMap<String, List>();
            for (String s : strs) {
                //将字符串转化为字符数组
                char[] chars = s.toCharArray();
                //对字符数组按照字母顺序排序
                Arrays.sort(chars);
                //将排序后的字符串作为哈希表中的key值
                String key = String.valueOf(chars);
                //2.判读哈希表中是否有该key值
                if (!map.containsKey(key)) {
                    //若不存在，则为新的异位词语，在map中创建新的键值对
                    map.put(key, new ArrayList());
                }
                //3.将该字符串放在对应key的list中
                map.get(key).add(s);
            }
            //返回map中所有键值对象构成的list
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
