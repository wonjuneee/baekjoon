import java.util.*;

class Solution {
    
    /**
     * 가장 먼 집부터 배달과 수거를 진행하면 이동거리를 최소화할 수 있을 것이다.
     * 이때 배달 & 수거를 할 필요가 없는 집은 건너뛴다.
     * - cap 혹은 배달해야 하는 박스만큼을 싣고 먼 집으로 가면서 박스를 배달하고, 빈 차로 돌아오면서 cap 혹은 수거해야 하는 박스만큼을 싣고 차고지로 돌아온다.
     * - 각각의 while 루프를 돌며 다음 배달 / 수거를 진행할 idx를 저장하고, 이 중 가장 큰 idx부터 다음 루프를 다시 돈다.
     */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int deliveryIdx = n - 1, pickupIdx = n - 1;
        for (int i = n - 1; i > -1;) {
            if (deliveries[i] == 0 && pickups[i] == 0) {
                i--; deliveryIdx--; pickupIdx--;
                continue;
            }
            
            int boxCount = 0;
            while (boxCount <= cap && deliveryIdx > -1) {
                if (boxCount + deliveries[deliveryIdx] <= cap) {
                    boxCount += deliveries[deliveryIdx];
                    deliveries[deliveryIdx] = 0;
                    deliveryIdx--;
                } else {
                    deliveries[deliveryIdx] -= cap - boxCount;
                    boxCount = cap;
                    break;
                }
            }

            boxCount = 0;
            while (boxCount <= cap && pickupIdx > -1) {
                if (boxCount + pickups[pickupIdx] <= cap) {
                    boxCount += pickups[pickupIdx];
                    pickups[pickupIdx] = 0;
                    pickupIdx--;
                } else {
                    pickups[pickupIdx] -= cap - boxCount;
                    boxCount = cap;
                    break;
                }
            }
            answer += (2 * (i + 1));
            i = Math.max(deliveryIdx, pickupIdx);
        }
        
        return answer;
    }
}