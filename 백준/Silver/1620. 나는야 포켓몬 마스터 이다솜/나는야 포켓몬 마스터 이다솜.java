import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static HashMap<String, String> pocketIdx = new HashMap<>();
    static HashMap<String, String> idxPocket = new HashMap<>();

    public static String solution(String input) {
        if (pocketIdx.containsKey(input)) {
            return pocketIdx.get(input);
        } else {
            return idxPocket.get(input);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            String pokemon = br.readLine();
            pocketIdx.put(String.valueOf(i), pokemon);
            idxPocket.put(pokemon, String.valueOf(i));
        }

        for (int i = 0; i < m; i++) {
            bw.write(String.valueOf(solution(br.readLine())));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
