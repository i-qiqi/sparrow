package leetcode.str;

public class T415Solution {
    class Solution {
        public String addStrings(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            int a = 0, b = 0;
            for (int i = m - 1, j = n - 1; i >= 0 || j >= 0; i--, j--) {
                a = i < 0 ? 0 : num1.charAt(i) - '0';
                b = j < 0 ? 0 : num2.charAt(j) - '0';
                int sum = a + b + carry;
                sb.append(sum % 10);
                carry = sum / 10;
            }

            if (carry == 1) sb.append(1);

            return sb.reverse().toString();
        }
    }
}
