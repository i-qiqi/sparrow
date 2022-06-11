package leetcode.swordoffer;

public class SO46Solution {
    class Solution {
        int ans;
        public int translateNum(int num) {
            backtrace(num);
            return ans;
        }

        void backtrace(int num){
            if(num == 0) {
                ans++;
                return;
            }

            // int oneDigit = num % 10;
            // StringBuilder sb = new StringBuilder('a' + oneDigit);
            backtrace(num/10);

            int twoDigit = num % 100;
            if(twoDigit >= 10 && twoDigit <= 25) {
                // sb = new StringBuilder('a' + twoDigit);
                backtrace(num/100);
            }
        }
    }
}
