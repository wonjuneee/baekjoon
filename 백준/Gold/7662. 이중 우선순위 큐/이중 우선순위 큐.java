import java.io.*;
import java.util.*;

public class Main {

    static int t, k;

    static final PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
    static final PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static final Map<Integer, Integer> numberMap = new HashMap<>();

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 2개의 우선순위 큐를 사용해 각각 오름차순, 내림차순으로 정렬해야 한다.
     * 이때 한 큐에서 값을 삭제할 경우, 다른 큐와 싱크가 맞지 않는 현상이 발생한다.
     * 이를 해결하기 위해 O(n)의 시간복잡도를 가지는 pq.remove(number)를 사용하면 O(n^2)의 연산이 수행되어 시간초과가 발생한다. (n=k <= 1_000_000)
     * 따라서 맵을 사용해 특정 값이 큐에 몇 개가 존재하는지 추적하며, 맵에는 없고 큐에만 있는 가짜값을 쳐내야 한다.
     * - 예를 들어 최대힙에서 poll이 수행되면, 최소힙의 말단 노드의 값은 큐에는 존재하지만 맵에는 존재 하지 않는다.
     * - 따라서 단순히 poll을 진행하면 잘못된 값을 가져오게 되므로, 맵에 존재하지 않는 값은 큐에서 반복적으로 지워야 한다.
     * - number = pq.poll(); numberMap.getOrDefault(number, 0);의 값이 0일 경우 해당 number는 큐에만 존재하므로 다음 값을 다시 poll해야 한다.
     * - 이를 반복하여 위의 값이 0이 아닌 값이 나올 경우 더 이상 루프를 돌지 않고 numberMap만 갱신하고 해당 값을 반환한다.
     * 즉, delete는 맵에서 이미 지워진 값과 실제 지워야 하는 값을 모두 지움과 동시에 numberMap을 갱신하는 로직을 가져야 한다.
     */
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        for (int tCase = 0; tCase < t; tCase++) {
            k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String operation = st.nextToken();
                int number = Integer.parseInt(st.nextToken());
                if (operation.equals("I")) {
                    maxPQ.add(number);
                    minPQ.add(number);
                    numberMap.put(number, numberMap.getOrDefault(number, 0) + 1);
                } else {
                    if (!numberMap.isEmpty()) {
                        delete(number == 1 ? maxPQ : minPQ);
                    }
                }
            }

            if (numberMap.isEmpty()) {
                sb.append("EMPTY").append('\n');
            } else {
                // 출력 시에도 이미 맵에서 지워진 값을 큐에서 꺼내올 수 있기 때문에 delete 함수를 사용해 실제 존재하는 값을 큐에서 가져올 수 있도록 한다.
                // 값이 하나만 남을 경우 한 번 값을 꺼내어 맵이 비게 되며, 이 경우 max 값을 그대로 사용. 그렇지 않을 경우 delete를 사용해 잘못된 값을 가져오지 못하도록 한다.
                int max = delete(maxPQ);
                int min = numberMap.isEmpty() ? max : delete(minPQ);

                sb.append(max).append(' ').append(min).append('\n');
            }
            maxPQ.clear();
            minPQ.clear();
            numberMap.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int delete(PriorityQueue<Integer> pq) {
        int num;
        while (true) {
            num = pq.poll();

            // 이미 맵에 없는 값일 경우 단순히 큐에서 지워주고, 다음 값으로 넘어간다.
            // 맵에 있는 값은 맵에 기록된 count에 대해 -1 을 더해준다.
            int cnt = numberMap.getOrDefault(num, 0);
            if (cnt == 0) {
                continue;
            } else {
                numberMap.compute(num, (key, value) -> {
                    if (value == null || value == 1) {
                        return null;
                    }
                    return value - 1;
                });
            }

            // 맵에 존재하는 값을 poll 했다면 싱크가 맞는 상태이므로 그대로 루프를 탈출한다.
            break;
        }
        return num;
    }
}