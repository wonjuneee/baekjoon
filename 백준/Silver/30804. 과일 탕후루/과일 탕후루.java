import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] stick = new int[200001];
    static int[] numberOfFruits = new int[200001];
    static Set<Integer> fruits = new HashSet<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     *  제한시간 2초, n <= 200,000 이므로 최대 O(n.log(n)) 의 시간복잡도가 최대.
     *  따라서 lastChangedIdx 를 유지하며 i 를 늘려나가는 O(n)의 투 포인터 알고리즘을 사용할 수 있다. <br>
     *  | 앞에서부터 과일을 꽂아넣으면서 만들 수 있는 가장 긴 탕후루 를 계산한다. <br>
     *  | 교차된 위치 lastChangedIdx를 기록하여, 기존 2개의 과일과 다른 새로운 과일이 등장했을 경우,
     *  lastChangedIdx부터 다시 탕후루의 과일 개수를 계산한다. <br>
     *  | n까지 위 과정을 반복하면 각 idx까지 과일을 꽂았을 때, 만들 수 있는 최대 길이의 탕후루를 기록하게 된다. <br>
     *  | result를 매 반복마다 최대값으로 갱신하면 과일이 가장 많은 탕후루의 과일 개수를 찾을 수 있다.
     */
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        numberOfFruits[0] = stick[0] = 0;
        int lastChangedIdx = 1, result = 0;
        for (int i = 1; i <= n; i++) {
            int fruit = Integer.parseInt(st.nextToken());
            stick[i] = fruit;

            /*
              fruit이 현재 이어지는 탕후루의 과일 셋에 포함되는 경우
              - 과일 개수를 1 늘린다
              - 순서가 교차되었는지 검증하여 마지막으로 교차된 idx를 기록한다.
             */
            if (fruits.contains(fruit)) {
                numberOfFruits[i] = numberOfFruits[i - 1] + 1;
                lastChangedIdx = stick[i] == stick[i - 1] ? lastChangedIdx : i;
            }
            /*
              fruit이 현재 이어지는 탕후루 과일 셋에 포함되지 않으며, 기존 과일 셋의 크기가 2일 경우
              - 기존 과일 셋에서 fruit과 이어질 수 없는 과일을 셋에서 제거하고, fruit을 셋에 추가한다.
              - 이전 과일과 fruit이 서로 교차되었으므로 lastChangedIdx를 갱신한다.
             */
            else if (fruits.size() == 2) {
                numberOfFruits[i] = i - lastChangedIdx + 1;
                fruits.add(fruit);
                fruits.remove(stick[lastChangedIdx - 1]);

                lastChangedIdx = i;
            }
            /*
              fruit이 현재 이어지는 탕후루 과일 셋에 포함되지 않지만, 기존 과일 셋의 크기가 1인 경우
              - 과일 셋에 fruit을 추가하고, 과일 개수를 1 늘린다.
              - 이전 과일과 fruit이 서로 교차되었으므로 lastChangedIdx를 갱신한다.
             */
            else {
                numberOfFruits[i] = numberOfFruits[i - 1] + 1;

                fruits.add(fruit);
                lastChangedIdx = i;
            }

            result = Math.max(result, numberOfFruits[i]);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}