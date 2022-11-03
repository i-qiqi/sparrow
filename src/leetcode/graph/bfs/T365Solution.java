package leetcode.graph.bfs;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class T365Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{0,0});
        Set<Long> seen = new HashSet<>();

        while(!stack.isEmpty()){
            if(seen.contains(hash(stack.peek()))){
                stack.pop();
                continue;
            }

            seen.add(hash(stack.peek()));
            int[] state = stack.pop();

            int remainX = state[0], remainY = state[1];
            if(remainX == targetCapacity || remainY == targetCapacity || remainX + remainY == targetCapacity){
                return true;
            }

            stack.push(new int[]{jug1Capacity, remainY});
            stack.push(new int[]{remainX, jug2Capacity});
            stack.push(new int[]{0, remainY});
            stack.push(new int[]{remainX, 0});
            int tempDelta = Math.min(remainX, jug2Capacity - remainY);
            stack.push(new int[]{remainX - tempDelta, remainY + tempDelta});
            tempDelta = Math.min(remainY, jug1Capacity - remainX);
            stack.push(new int[]{remainX + tempDelta, remainY - tempDelta});
        }
        return false;
    }

    long hash(int[] state){
        return (long) state[0] * 1000001 + state[1];
    }
}
