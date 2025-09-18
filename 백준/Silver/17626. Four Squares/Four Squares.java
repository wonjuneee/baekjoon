import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] lagrangeValues = new int[50001];

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        // 1^2 을 i번 더한 것을 기본값으로 대입
        for (int i = 1; i <= n; i++) {
            lagrangeValues[i] = i;
            getLagrange(i);
        }
        bw.write(String.valueOf(lagrangeValues[n]));
        bw.flush();
        bw.close();
    }

    /**
     * 1. 제곱수에 대한 합으로 나타나므로 주어진 수의 제곱근보다 작은 수로만 나타낼 수 있다.
     * 2. 1부터 n의 제곱근까지 반복하여 가장 적은 개수의 수로 나타낼 수 있는 케이스를 찾는다.
     * 2-1. n에서 i의 제곱근을 뺀 값은 이전 루프에서 구한 다른 수의 라그랑주 값이다.
     *      이전 수의 라그랑주 값에 제곱수 하나가 추가되었으므로
     *      lagrangeValue[number] = lagrangeValue[number - (i*i)] + 1 로 나타낼 수 있다.
     */
    private static void getLagrange(int number) {
        int sqrt = (int) Math.sqrt(number);

        for (int i = sqrt; i > 0; i--) {
            lagrangeValues[number] = Math.min(lagrangeValues[number - (i * i)] + 1, lagrangeValues[number]);
        }
    }
}