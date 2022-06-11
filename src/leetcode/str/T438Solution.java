package leetcode.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T438Solution {
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            Map<Character,Integer> need = new HashMap<>();
            Map<Character, Integer> windows = new HashMap<>();
            for(char c : p.toCharArray()){
                need.put(c, need.getOrDefault(c, 0)+1);
            }

            int i = 0, j = 0;
            int sum = 0;
            List<Integer> ans = new ArrayList<>();
            while(j < s.length()){
                char c = s.charAt(j);
                j++;
                if(need.containsKey(c)){
                    windows.put(c, windows.getOrDefault(c, 0) + 1);
                    if(windows.get(c).equals(need.get(c))){
                        sum++;
                    }
                }


                while(j - i >= p.length()){
                    if(sum == need.size()){
                        ans.add(i);
                    }
                    char d = s.charAt(i);
                    i++;
                    if(need.containsKey(d)){
                        if(windows.get(d).equals(need.get(d))){
                            sum--;
                        }
                        windows.put(d, windows.get(d) - 1);
                    }
                }
            }
            return ans;
        }
    }
}
