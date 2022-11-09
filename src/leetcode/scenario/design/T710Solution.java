package leetcode.scenario.design;

import java.util.*;

public class T710Solution {
    int n, m, idx;
    Random random = new Random();
    Set<Integer> s1 = new HashSet<>();
    Set<Integer> s2 = new HashSet<>();
    Map<Integer, Integer> map = new HashMap<>();

    public T710Solution(int _n, int[] bs) {
        n = _n;
        m = bs.length;
        int max = n - m;
        for (int x : bs) {
            if (x < max) {
                s1.add(x);
            } else {
                s2.add(x);
            }
        }
        idx = n - m;
    }

    public int pick() {
        int val = random.nextInt(n - m);
        if (!s1.contains(val)) {
            return val;
        }

        if (!map.containsKey(val)) {
            while (s2.contains(idx)) {
                idx++;
            }
            map.put(val, idx++);
        }

        return map.get(val);
    }
}
