package leetcode.str;

public class T273Solution {

    // x < 20
    static String[] num2strSmall = {
            "Zero",
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    // x > 20
    static String[] num2strMedium = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    //x > 100
    static String[] nums2strLarge = {
            "Billion", "Million", "Thousand", ""
    };
    String num2Str(int x){
        String ans = "";
        if(x >= 100){
            ans += num2strSmall[x / 100] + " Hundred ";
            x %= 100;
        }

        if(x >= 20){
            ans += num2strMedium[x / 10] + " ";
            x %= 10;
        }

        if (x != 0) {
            ans += num2strSmall[x] + " ";
        }

        return ans;
    }
    public String numberToWords(int num) {
        if (num == 0) return num2strSmall[0];

        StringBuilder sb = new StringBuilder();
        for (int i = (int)1e9, j = 0; i >= 1; i /= 1000, j++){
            if(num < i) continue;
            sb.append(num2Str(num / i) + nums2strLarge[j] + " ");
            num %= i;
        }
        while (sb.charAt(sb.length() - 1) == ' ') sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        T273Solution so = new T273Solution();

        System.out.println(so.numberToWords(1234567));
    }

}
