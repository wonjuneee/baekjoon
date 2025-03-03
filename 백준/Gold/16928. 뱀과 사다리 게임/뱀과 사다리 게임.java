import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] map = new int[101];
    static int[] dist = new int[101];

    public static int solution() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (map[cur] == 0){
                for (int i = cur + 1; i <= cur + 6; i++) {
                    if (i > 100)
                        break;
                    dist[i] = dist[i] == 0 ? dist[cur] + 1 : Math.min(dist[i], dist[cur] + 1);
                    if (!q.contains(i))
                        q.add(i);
                }
            }
            else {
                int nextPos = map[cur];
                if (dist[nextPos] == 0 || dist[nextPos] > dist[cur]) {
                    q.add(nextPos);
                    dist[nextPos] = dist[cur];
                }
            }
        }

        return dist[100];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            map[from] = to;
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            map[from] = to;
        }

        int result = solution();
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
