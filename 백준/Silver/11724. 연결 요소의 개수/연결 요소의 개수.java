import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] map = new int[1001];
    static HashMap<Integer, Boolean> root = new HashMap<>();

    public static int solution() {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            getParent(i);
            if (!root.containsKey(map[i])) {
                result++;
                root.put(map[i], true);
            }
        }

        return result;
    }

    static void union(int a, int b) {
        int pA = getParent(a);
        int pB = getParent(b);

        if (a < b) {
            map[pB] = pA;
        } else {
            map[pA] = pB;
        }
    }

    static int getParent(int a) {
        if (map[a] == a)
            return a;
        return map[a] = getParent(map[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            map[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        int result = solution();
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
