package leetcode.cache;

import java.util.HashMap;
import java.util.Map;

public class T460Solution {
    class LFUCache {
        int minFreq;
        Map<Integer, Node> keyToNode;
        Map<Integer, DLinkedList> freqToNodes;
        int capacity;

        public LFUCache(int capacity) {
            keyToNode = new HashMap<>();
            freqToNodes = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if (keyToNode.containsKey(key)) {
                Node p = keyToNode.get(key);
                //freq Node list -> freq+1 Node List
                transfer(p);
                return p.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {

            if (capacity == 0) return;

            if (keyToNode.containsKey(key)) {
                Node p = keyToNode.get(key);
                //更新
                p.val = value;
                transfer(p);
            } else {
                // System.out.println("key = " + key + " val = " + value);
                evict();
                Node p = new Node(key, value, 1);
                keyToNode.put(key, p);
                DLinkedList list = freqToNodes.getOrDefault(p.freq, new DLinkedList());
                list.addLast(p);
                freqToNodes.putIfAbsent(p.freq, list);
                minFreq = 1;
            }
        }

        public void evict() {
            //判满，淘汰minFreqNode
            if (capacity == keyToNode.size()) {
                DLinkedList minList = freqToNodes.get(minFreq);
                Node minNode = minList.removeFirst();
                if (minList.isEmpty()) {
                    freqToNodes.remove(minFreq);
                }
                // System.out.println(minNode + " " + minFreq);
                keyToNode.remove(minNode.key);
            }
        }

        public void transfer(Node p) {
            DLinkedList list = freqToNodes.get(p.freq);
            list.remove(p);
            if (list.isEmpty()) {
                freqToNodes.remove(p.freq);
                //更新minFreq
                if (minFreq == p.freq) {
                    minFreq = p.freq + 1;
                }
            }
            //更新freq
            p.freq = p.freq + 1;
            DLinkedList list2 = freqToNodes.getOrDefault(p.freq, new DLinkedList());
            list2.addLast(p);
            freqToNodes.putIfAbsent(p.freq, list2);
        }

        class Node {
            int key, val, freq;
            Node prev, next;

            public Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }

        class DLinkedList {
            Node head, tail;

            public DLinkedList() {
                head = new Node(-1, -1, -1);
                tail = new Node(-1, -1, -1);
                head.next = tail;
                tail.prev = head;
            }

            public boolean isEmpty() {
                return head.next == tail && tail.prev == head;
            }

            public Node remove(Node p) {
                //p.prev p p.next
                p.prev.next = p.next;
                p.next.prev = p.prev;
                p.next = null;
                p.prev = null;
                return p;
            }

            public void addLast(Node p) {
                tail.prev.next = p;
                p.prev = tail.prev;

                tail.prev = p;
                p.next = tail;
            }

            public Node removeFirst() {
                if (!isEmpty()) {
                    return remove(head.next);
                }
                return null;
            }
        }


    }
}
