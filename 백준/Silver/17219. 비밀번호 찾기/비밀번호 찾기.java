import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static final Map<String, String> sitePasswordMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer line = new StringTokenizer(br.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());

        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            sitePasswordMap.put(token.nextToken(), token.nextToken());
        }

        for (int i = 0; i < m; i++) {
            bw.write(sitePasswordMap.get(br.readLine()));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
