package leetcode.hot100;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class T128Solution {

    public int longestConsecutive(int[] nums) {
        Integer[] arr  =  Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Set<Integer> set = Arrays.stream(arr).collect(Collectors.toSet());

        int ans = 0;
        for ( Integer num : arr ) {
            if (!set.contains(num - 1)) {
                int tempLen = 0;
                while ( set.contains(num)) {
                    tempLen++;
                    num++;
                }
                ans = Math.max(ans, tempLen);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        T128Solution solution = new T128Solution();
        // solution.longestConsecutive(nums);
        Integer a = 127;
        Integer b = 127;
        System.out.println(a.hashCode()+","+b.hashCode());
       
        String s1 = "127";
        String s2 = "127";
        System.out.println(s1.hashCode()+","+s2.hashCode());
        System.out.println(s1.equals(s2));

    }
    
}
