import java.io.*;
import java.util.*;

public class Main {

    static int t, n;
    static long[] lengthList = new long[101];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());

        lengthList[1] = lengthList[2] = lengthList[3] = 1;
        lengthList[4] = lengthList[5] = 2;

        for (int i = 5; i <= 100; i++) {
            lengthList[i] = lengthList[i - 1] + lengthList[i - 5];
        }

        for (int tCase = 0; tCase < t; tCase++) {
            n = Integer.parseInt(br.readLine());
            sb.append(lengthList[n]).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}