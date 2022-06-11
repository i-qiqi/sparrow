package leetcode.math;

import java.util.LinkedList;

public class T679Solution {
    class Solution {

        private static final double TARGET = 24;

        private static final double EPSILON = 1e-6;

        public boolean judgePoint24(int[] cards) {
            LinkedList<Double> list = new LinkedList<>();
            for (int c : cards) {
                list.addLast(Double.valueOf(c));
            }

            return helper(list);
        }

        public boolean helper(LinkedList<Double> cards) {
            if (cards.size() == 1) {
                if (Math.abs(cards.get(0) - TARGET) < EPSILON) {
                    return true;
                }
            }

            for (int i = 0; i < cards.size(); i++) {
                double a = cards.get(i);
                for (int j = 0; j < cards.size(); j++) {
                    double b = cards.get(j);
                    if (i == j) continue;

                    LinkedList<Double> nextCards = new LinkedList<>();
                    for (int k = 0; k < cards.size(); k++) {
                        if (k != i && k != j) {
                            nextCards.addLast(cards.get(k));
                        }
                    }
                    if (i < j) { // * , +
                        // 选择*
                        nextCards.addLast(a * b);
                        if (helper(nextCards)) {
                            return true;
                        }
                        nextCards.removeLast();

                        // 选择 +
                        nextCards.addLast(a + b);
                        if (helper(nextCards)) {
                            return true;
                        }
                        nextCards.removeLast();
                    }

                    // 选择 /
                    nextCards.addLast(a * 1.0 / b);
                    if (helper(nextCards)) {
                        return true;
                    }
                    nextCards.removeLast();

                    // 选择 -
                    nextCards.addLast(a - b);
                    if (helper(nextCards)) {
                        return true;
                    }
                    nextCards.removeLast();
                }
            }

            return false;
        }


    }

    class Solution1 {

        private static final double TARGET = 24;

        private static final double EPSILON = 1e-6;

        public boolean judgePoint24(int[] cards) {
            LinkedList<Double> list = new LinkedList<>();
            for (int c : cards) {
                list.addLast(Double.valueOf(c));
            }

            return helper(list);
        }

        public boolean helper(LinkedList<Double> cards) {
            if (cards.size() == 1) {
                if (Math.abs(cards.get(0) - TARGET) < EPSILON) {
                    return true;
                }
            }

            for (int i = 0; i < cards.size(); i++) {
                double a = cards.get(i);
                for (int j = 0; j < cards.size(); j++) {
                    double b = cards.get(j);
                    if (i == j) continue;

                    LinkedList<Double> nextCards = new LinkedList<>();
                    for (int k = 0; k < cards.size(); k++) {
                        if (k != i && k != j) {
                            nextCards.addLast(cards.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        boolean isSelect = addCard(nextCards, k, a, b, i, j);
                        if (isSelect && helper(nextCards)) {
                            return true;
                        }
                        if (isSelect) {
                            nextCards.removeLast();
                        }
                    }
                }
            }

            return false;
        }

        private boolean addCard(LinkedList<Double> nextCards, int k, double a, double b, int i, int j) {
            if (k == 0 && i < j) {
                nextCards.addLast(a * b);
                return true;
            }
            if (k == 1 && i < j) {
                nextCards.addLast(a + b);
                return true;

            }
            if (k == 2) {
                nextCards.addLast(a * 1.0 / b);
                return true;
            }
            if (k == 3) {
                nextCards.addLast(a - b);
                return true;
            }
            return false;
        }
    }
}
