package leetcode.parentheses;

import java.util.ArrayList;
import java.util.List;

public class T385Solution {
    public NestedInteger deserialize(String s) {
        //[1,2,[3,4,[5]],-6]
        NestedInteger ans = new NestedInteger();
        int leftBracketCount = 0, rightBracketCount = 0;
        if (s.charAt(0) == '[') {
            //找到下一个,
            int i = 1;
            if (s.charAt(1) == ']') {
                return new NestedInteger();
            }

            while (!(leftBracketCount == rightBracketCount && s.charAt(i) == ',') && i < s.length() - 1) {
                if (s.charAt(i) == '[') {
                    leftBracketCount++;
                }
                if (s.charAt(i) == ']') {
                    rightBracketCount++;
                }
                i++;
            }

            NestedInteger ni;
            String firstNiStr = s.substring(1, i);
            if (leftBracketCount > 0) {
                ni = deserialize(firstNiStr);
            } else {
                ni = firstNiStr.length() == 0 ? new NestedInteger() : new NestedInteger(Integer.parseInt(firstNiStr));
            }
            ans.add(ni);
            if (i < s.length() - 1) {
                NestedInteger niRight = deserialize("[" + s.substring(i + 1, s.length()));
                for (NestedInteger x : niRight.getList()) {
                    ans.add(x);
                }
            }
        } else {
            ans.setInteger(Integer.parseInt(s));
        }
        return ans;
    }
    //    [-1,2,[13,4],[15,[]]]
    //    [              ]
    //    /   |   \     \
    //    -1  2  [  ]   [  ]
    //           /  \   /  \
    //          13   4  15 []
    int index = 0;

    public NestedInteger deserialize1(String s) {
        if (s.charAt(index) == '[') {
            index++;
            NestedInteger ni = new NestedInteger();
            while (s.charAt(index) != ']') {
                ni.add(deserialize1(s));
                if (s.charAt(index) == ',') {
                    index++;
                }
            }
            index++;
            return ni;
        } else {
            boolean negative = false;
            if (s.charAt(index) == '-') {
                negative = true;
                index++;
            }
            int num = 0;
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }

            if (negative) {
                num *= -1;
            }

            return new NestedInteger(num);
        }
    }


    public static class NestedInteger {
        List<NestedInteger> list;
        Integer value = null;

        public NestedInteger() {
            this.list = null;
            this.value = null;
        }

        public NestedInteger(int value) {
            this.value = value;
        }

        public boolean isInteger() {
            return value == null;
        }

        public Integer getInteger() {
            return value;
        }

        public void setInteger(int value) {
            this.value = value;
        }

        public void add(NestedInteger ni) {
            value = null;
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(ni);
        }


        public List<NestedInteger> getList() {
            return list == null ? new ArrayList<>() : list;
        }
    }


}
