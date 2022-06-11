package leetcode.str;

public class T43Solution {
    class Solution {
        public String multiply(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            if (num1.equals("0") || num2.equals("0")) return "0";
            int y = 0, temp = 0, carry = 0;
            String curAns = "0";
            String zeros = "";
            for (int i = m - 1; i >= 0; i--) {
                y = num1.charAt(i) - '0';
                carry = 0;
                StringBuilder sb = new StringBuilder();
                temp = 0;
                for (int j = n - 1; j >= 0; j--) {
                    temp = (num2.charAt(j) - '0') * y + carry;
                    sb.append(temp % 10);
                    carry = temp / 10;
                }
                if (carry > 0) {
                    sb.append(carry);
                }
                sb.reverse();
                sb.append(zeros);
                zeros += "0";
                // System.out.println(sb.toString());
                curAns = addStrings(curAns, sb.toString());
            }

            return curAns;
        }

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


    class Solution1 {
        public String multiply(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            int[] res = new int[m + n];

            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    int p = i + j;
                    int sum = res[p + 1] + mul;
                    res[p + 1] = sum % 10;
                    res[p] += sum / 10;
                }
            }

            int i = 0;
            while (i < res.length && res[i] == 0) i++;
            if (i == res.length) return "0";
            StringBuilder sb = new StringBuilder();
            for (; i < res.length; i++) {
                sb.append(res[i]);
            }

            return sb.toString();
        }
    }
}
