package leetcode.top200;

/**
 * 加油站
 */
public class T134Solution {

    public int canCompleteCircuit1(int[] gas, int[] cost) {

        for (int i = 0; i < gas.length; i++) {
            int k = i;
            int currentOilSum = 0;
            while (currentOilSum + gas[k] - cost[k] >= 0) {
                currentOilSum += gas[k] - cost[k];
                k = (k + 1) % gas.length;
                if (k == i)
                    return i;
            }

            if (k < i)
                return -1;

            // 去重
            i = k;
        }

        return -1;

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 折线图法
        int remainingOilSum = 0;
        int minRemaingOilSum = Integer.MAX_VALUE;
        int minRemainingOilStationIdx = 0;
        for (int i = 0; i < gas.length; i++) {
            remainingOilSum = remainingOilSum + gas[i] - cost[i];
            if (remainingOilSum < minRemaingOilSum) {
                minRemaingOilSum = remainingOilSum;
                minRemainingOilStationIdx = i;
            }
        }

        return remainingOilSum < 0 ? -1 : (minRemainingOilStationIdx + 1) % gas.length;
    }

}
