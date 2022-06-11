package leetcode.cache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class T146LRUCache {
    int capacity;
    Map<Integer, Node> mp;
    Deque<Node> list;

    public T146LRUCache(int capacity) {
        this.capacity = capacity;
        mp = new HashMap<>();
        list = new LinkedList<>();
    }

    public int get(int key) {
        if (mp.containsKey(key)) {
            Node node = mp.get(key);
            // 访问了就要提前 ***
            list.remove(node);
            list.addFirst(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (mp.containsKey(key)) {
            Node node = mp.get(key);
            node.value = value;
            list.remove(node);
            list.addFirst(node);
        } else {
            Node node = new Node(key, value);
            mp.put(key, node);
            list.addFirst(node);
        }
        if (list.size() > capacity) {
            Node last = list.removeLast();
            mp.remove(last.key);
        }
    }

    

    private static class Node {
        int key, value;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */