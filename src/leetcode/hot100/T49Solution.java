package leetcode.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void main(String[] args) {
        String[] strs = { "bdddddddddd", "bbbbbbbbbbc" };

        T49Solution solution = new T49Solution();

        // solution.groupAnagrams(strs);
        Map<Character, Integer> cntMap = new HashMap<>();
        cntMap.put('a', 10);
        cntMap.put('b', 3);
        System.out.println(cntMap.toString());

    }

}
