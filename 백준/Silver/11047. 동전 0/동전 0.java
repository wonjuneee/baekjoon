import java.util.*;
import java.io.*;

public class Main {

    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());
                List<Integer> coinList = new ArrayList<>(n);

                for (int i = 0; i < n; i++) {
                    int coin = Integer.parseInt(br.readLine());
                    coinList.add(coin);
                }

                coinList.sort(Collections.reverseOrder());
                int result = solution(coinList);
                bw.write(String.valueOf(result));
                bw.newLine();

            } catch (Exception e) {
                break;
            }
        }
        bw.flush();
        bw.close();
    }

    private static int solution(List<Integer> coinList) {
        int sum = 0, result = 0;
        for (int coin : coinList) {
            while (sum + coin <= k) {
                sum += coin;
                result++;
            }
        }
        return result;
    }
}
