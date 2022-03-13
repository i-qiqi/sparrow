package leetcode.hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T17Solution {

    String[] telephone = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList();
        return combine(digits, 0);
    }

    private List<String> combine(String digits, int i) {
        if (digits.length() == i)
            return Stream.of(new String[] {""}).collect(Collectors.toList());

        String letters = telephone[digits.charAt(i) - 48];
        List<String> ans = new ArrayList<>();
        for (int k = 0; k < letters.length(); k++) {
            List<String> tmpRes = combine(digits, i + 1);
            for (String s : tmpRes) {
                StringBuilder sb = new StringBuilder(letters.substring(k, k + 1));
                ans.add(sb.append(s).toString());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("hello");
        T17Solution solution = new T17Solution();
        System.out.println(solution.letterCombinations("").toString());
    }

}
