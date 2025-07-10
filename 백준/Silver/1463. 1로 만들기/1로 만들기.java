import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static final int[] dp = new int[1000001];

    private static int solution() {
        dp[1] = 0;
        dp[2] = dp[3] = 1;

        for (int i = 4; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i/3] + 1, dp[i]);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i/2] + 1, dp[i]);
            }
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                n = Integer.parseInt(br.readLine());

                int result = solution();
                bw.write(String.valueOf(result));
                bw.newLine();
            } catch (NumberFormatException e) {
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
