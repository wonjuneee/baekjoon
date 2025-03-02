import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] dp = new int[1001];

    public static int solution() {
        dp[1] = 1;
        dp[2] = 3;
/*
        dp[3] = 5;
        dp[4] = 11;
*/

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        int result = solution();
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
