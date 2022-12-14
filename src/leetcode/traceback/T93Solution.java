package leetcode.traceback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T93Solution {
    class Solution {

        List<String> res;

        public List<String> restoreIpAddresses(String s) {
            res = new LinkedList<>();
            if (s.length() < 4 || s.length() > 12) return res;
            traceback(s, 0, 3, new LinkedList<>());
            return res;
        }

        private void traceback(String s, int st, int k, LinkedList<String> path) {
            if (k == 0) {
                String ipSeg = s.substring(st, s.length());
                path.add(ipSeg);
                if (!isValid(ipSeg)) {
                    path.removeLast();
                    return;
                }

                String tmp = String.join(".", path);
                res.add(tmp);
                path.removeLast();
                return;
            }

            if ((s.length() - st) / (k + 1) >= 4) return;

            for (int i = st; i < s.length() - 1 ; i++) {
                String ipSeg = s.substring(st, i + 1);
                if (!isValid(ipSeg)) break;
                path.add(ipSeg);
                traceback(s, i + 1, k - 1, path);
                path.removeLast();
            }

        }

        public boolean isValid(String seg) {
            if (seg.length() >= 4) return false;
            if (seg.charAt(0) == '0' && seg.length() > 1) return false;
            Integer ipSegNum = Integer.valueOf(seg);
            if (ipSegNum > 255) return false;
            return true;
        }
    }

    class Solution1 {
        static final int SEG_COUNT = 4;
        List<String> ans = new ArrayList<String>();
        int[] segments = new int[SEG_COUNT];

        public List<String> restoreIpAddresses(String s) {
            segments = new int[SEG_COUNT];
            dfs(s, 0, 0);
            return ans;
        }

        public void dfs(String s, int segId, int segStart) {
            // ??????????????? 4 ??? IP ????????????????????????????????????????????????????????????
            if (segId == SEG_COUNT) {
                if (segStart == s.length()) {
                    StringBuffer ipAddr = new StringBuffer();
                    for (int i = 0; i < SEG_COUNT; ++i) {
                        ipAddr.append(segments[i]);
                        if (i != SEG_COUNT - 1) {
                            ipAddr.append('.');
                        }
                    }
                    ans.add(ipAddr.toString());
                }
                return;
            }

            // ????????????????????? 4 ??? IP ?????????????????????????????????????????????????????????
            if (segStart == s.length()) {
                return;
            }

            // ???????????????????????????????????????????????? 0?????????????????? IP ??????????????? 0
            if (s.charAt(segStart) == '0') {
                segments[segId] = 0;
                dfs(s, segId + 1, segStart + 1);
            }

            // ????????????????????????????????????????????????
            int addr = 0;
            for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
                addr = addr * 10 + (s.charAt(segEnd) - '0');
                if (addr > 0 && addr <= 0xFF) {
                    segments[segId] = addr;
                    dfs(s, segId + 1, segEnd + 1);
                } else {
                    break;
                }
            }
        }
    }
}
