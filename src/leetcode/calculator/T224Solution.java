package leetcode.calculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class T224Solution {
    class Solution {
        public int calculate(String s) {
            Deque<Integer> operands = new LinkedList<>();
            Deque<Character> operators = new LinkedList<>();
            s = "(" + s + ")";
            char pre = '(';
            operators.addLast('(');
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDigit(c)) {
                    int top = isDigit(pre) ? operands.removeLast() : 0;
                    operands.addLast((c - '0') + top * 10);
                } else {
                    switch (c) {
                        case '+':
                        case '-':
                            char op = operators.getLast();
                            if (c == '-' && pre == '(') {
                                operands.addLast(0);
                            }
                            if (op == '+' || op == '-') {
                                leftOpRight(operands, op);
                                operators.removeLast();
                            }
                            operators.addLast(c);
                            break;
                        case '(':
                            operators.addLast(c);
                            break;
                        case ')':
                            while ('(' != (op = operators.removeLast())) {
                                // System.out.println("( : " + operands + " : " + operators + " : " + op);
                                leftOpRight(operands, op);
                            }
                            break;
                        default:
                            break;
                    }
                }
                pre = c;
            }
            //  System.out.println("operators " + operators);
            return operands.removeLast();
        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        private void leftOpRight(Deque<Integer> operands, char op) {
            int rightNum = operands.removeLast();
            int leftNum = operands.removeLast();
            if (op == '+') {
                operands.addLast(leftNum + rightNum);
            }
            if (op == '-') {
                operands.addLast(leftNum - rightNum);
            }
        }


    }

    class Solution1 {
        int st = 0;

        public int calculate(String s) {
            //判断结尾
            return helper(s + '+');
        }

        public int helper(String s) {
            LinkedList<Integer> stack = new LinkedList<>();
            int num = 0;
            char sign = '+';
            for (int i = st; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDigit(c)) {
                    num = num * 10 + (c - '0');
                }

                if (c == '(') {
                    //递归
                    st = i + 1;
                    num = helper(s);
                    // System.out.println(num + " " + st);
                    i = st;
                }

                if (!isDigit(c) && c != ' ') {
                    if (sign == '+') {
                        stack.addLast(num);
                    }
                    if (sign == '-') {
                        stack.addLast(-num);
                    }
                    if (sign == '*') {
                        int top = stack.removeLast();
                        stack.addLast(top * num);
                    }

                    if (sign == '/') {
                        int top = stack.removeLast();
                        stack.addLast(top / num);
                    }
                    sign = c;
                    num = 0;
                }

                if (c == ')') {
                    //退出递归
                    stack.addLast(num * sign);
                    st = i;
                    break;
                }
            }

            int ret = 0;
            while (stack.size() > 0) {
                int x = stack.removeLast();
                ret += x;
            }

            return ret;
        }

        public boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }
    }


    public static void main(String[] args) {
        Deque<Integer> operands = new LinkedList<>();
        operands.addLast(1);
//        System.out.println(operands.removeLast());
//        System.out.println(operands.removeLast());
//        System.out.println(operands.getLast());

        //15位小数
        System.out.println(99.999_999_999_999_999 < 100);
        System.out.println(99.999_999_999_999_997 < 100);
        System.out.println(99.99_999_999_999_999 < 100);
    }
}
