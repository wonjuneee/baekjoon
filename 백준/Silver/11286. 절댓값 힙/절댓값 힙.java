import java.io.*;
import java.util.*;

public class Main {

    static int n;

    // 우선순위 큐는 이미 정렬된 상태에서 새로운 값을 삽입하는 힙 구조
    static final PriorityQueue<Integer> absPQ = new PriorityQueue<>((i1, i2) -> {
        // 절대값이 같고, 앞의 값이 크므로 내림차순으로 정렬
        if (i1 == -i2 && i1 > 0) {
            return 1;
        // 절대값이 같고, 앞의 값이 작으므로 유지
        } else if (i1 == -i2 && i1 < 0) {
            return -1;
        }
        // 절대값이 다를 경우, 절대값을 기준으로 내림차순 정렬
        // 앞의 값이 큰 경우 결과가 양수이므로 정렬, 앞의 값이 작은 경우 결과가 음수이므로 유지
        return Math.abs(i1) - Math.abs(i2);
    });

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());

            if (number == 0) {
                Integer value = Optional.ofNullable(absPQ.poll()).orElse(0);
                sb.append(value).append('\n');
            } else {
                absPQ.add(number);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}