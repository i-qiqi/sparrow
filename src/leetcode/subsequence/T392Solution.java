package leetcode.subsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T392Solution {
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0) return true;
        if(t.length() == 0) return false;

        int i = 0, j = 0;
        while(i < t.length()){
            if(t.charAt(i) == s.charAt(j)){
                j++;
                if(j == s.length()){
                    return true;
                }
            }
            i++;
        }

        return false;
    }

    class Solution {
        public boolean isSubsequence(String s, String t) {
            Map<Character, List<Integer>> charToIdxMap = new HashMap<>();

            for(int i = 0; i < t.length(); i++){
                char c = t.charAt(i);
                List<Integer> idxs = charToIdxMap.getOrDefault(c, new ArrayList<>());
                idxs.add(i);
                charToIdxMap.put(c,idxs);
            }

            int target = -1;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(charToIdxMap.containsKey(c)){
                    //在数组种找到一个大于pre的数
                    target = findFirstGreaterOne(charToIdxMap.get(c), target);
                    //-1 表示没找到
                    if(target == -1) return false;
                } else {
                    return false;
                }
            }
            return true;
        }

        public int findFirstGreaterOne(List<Integer> nums, int target){
            int low = 0, high = nums.size() - 1;
            System.out.println(nums.size());
            while(low < high){
                int mid = low + (high - low) / 2;
                if(nums.get(mid) >= target){
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            if(nums.get(low) <= target) {
                if(low + 1 > 0 && low + 1 < nums.size() - 1 && nums.get(low + 1) > target) {
                    return nums.get(low + 1);
                }
                return -1;
            }
            return nums.get(low);
        }

        //大量的需要判定的序列，S1....SM, O(N*M)

        //大量的母序列，T1...TN, O(N*N)
    }
}
