package leetcode.scenario.design;

import java.util.*;

public class T355Solution {

    private class Node {
        Set<Integer> followee;
        LinkedList<Integer> tweet;

        Node() {
            followee = new HashSet<>();
            tweet = new LinkedList<>();
        }
    }

    private int recentMax, time;

    private Map<Integer, Integer> tweetTime;

    private Map<Integer, Node> user;

    public T355Solution() {
        time = 0;
        recentMax = 10;
        tweetTime = new HashMap<>();
        user = new HashMap<>();
    }

    public void init(int userId) {
        user.put(userId, new Node());
    }

    public void postTweet(int userId, int tweetId) {
        if (!user.containsKey(userId)) {
            init(userId);
        }

        if (user.get(userId).tweet.size() == recentMax) {
            user.get(userId).tweet.remove(recentMax - 1);
        }

        user.get(userId).tweet.addFirst(tweetId);
        tweetTime.put(tweetId, ++time);
    }

    public List<Integer> getNewsFeed(int userId) {
        LinkedList<Integer> ans = new LinkedList<Integer>();
        for (int it : user.getOrDefault(userId, new Node()).tweet) {
            ans.addLast(it);
        }

        for (int followeeId : user.getOrDefault(userId, new Node()).followee) {
            if (followeeId == userId) {
                continue;
            }
            LinkedList<Integer> res = new LinkedList<>();

            int tweetSize = user.get(followeeId).tweet.size();

            Iterator<Integer> it = user.get(followeeId).tweet.iterator();

            int i = 0, j = 0;

            int curr = -1;

            if (j < tweetSize) {
                curr = it.next();
                while (i < ans.size() && j < tweetSize) {
                    if (tweetTime.get(curr) > tweetTime.get(ans.get(i))) {
                        res.addLast(curr);
                        ++j;
                        if (it.hasNext()) {
                            curr = it.next();
                        }
                    } else {
                        res.addLast(ans.get(i));
                        ++i;
                    }

                    if (res.size() == recentMax) {
                        break;
                    }
                }
            }
            for (; i < ans.size() && res.size() < recentMax; ++i) {
                res.addLast(ans.get(i));
            }

            if (j < tweetSize && res.size() < recentMax) {
                res.addLast(curr);
                for (; it.hasNext() && res.size() < recentMax; ) {
                    res.addLast(it.next());
                }
            }
            ans = new LinkedList<>(res);
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if (!user.containsKey(followerId)) {
            init(followerId);
        }

        if (!user.containsKey(followeeId)) {
            init(followeeId);
        }

        user.get(followerId).followee.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        user.getOrDefault(followerId, new Node()).followee.remove(followeeId);
    }
}
