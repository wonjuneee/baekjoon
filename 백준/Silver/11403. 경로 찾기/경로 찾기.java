import java.io.*;
import java.util.*;

public class Main {

    static int n;

    static final int[][] map = new int[102][102];

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 다익스트라, 벨만포드, 플로이드-워셜 알고리즘을 활용해 end2end 연결이 존재하는지 확인하면 된다.
     * N <= 100이므로 O(N^3)의 플로이드-워셜 알고리즘도 충분히 사용할 수 있다.
     *
     * 1. 최대 O(N^2)의 시간복잡도를 가지는 다익스트라 알고리즘 역시 사용할 만 하다.
     * 우선순위 큐: O((V+E)log V) , 연결 그래프: O(E log V)
     * 2. 플로이드-워셜 알고리즘은 기본적으로 모든 노드 간의 최단 거리를 구하는 알고리즘이기 때문에 이 문제와 가장 fit하다고 할 수 있다.
     */
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int edge = Integer.parseInt(st.nextToken());
                map[i][j] = edge;
            }
        }
        // dijkstra();
        floydWarshall();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /**
     * 이 문제에는 BFS 형식으로 사용된다.
     * 다익스트라는 한 시작노드에서 다른 노드들까지의 거리를 계산하지만,
     * 문제의 특성 상 모든 노드에 대해 연결 가능성을 계산해야 하므로 노드 개수만큼 곱으로 연산 횟수가 증가한다.
     */
    static void dijkstra() {
        Queue<Integer> q = new LinkedList<>();

        // 각 노드를 시작점으로 하여 연결 가능한 모든 경우의 수 찾기
        for (int i = 1; i <= n; i++) {
            boolean[] isVisited = new boolean[101];

            // 최초 시작점의 인접 노드들을 큐에 삽입한다.
            // 시작 노드 자신으로의 사이클이 존재할 수 있으므로 최초의 인접노드를 찾는 로직을 먼저 수행
            // isVisited[시작노드]는 시작노드에 재방문되었을 때 플래그한다.
            for (int to = 1; to <= n; to++) {
                if (map[i][to] == 1) {
                    q.add(to);
                }
            }

            // 시작노드와 연결이 이루어진 노드들에 대해 방문하지 않은 노드들을 큐에 삽입한다.
            // 시작노드 - 현재노드 - 인접노드 형태로 경로가 형성되므로 map[시작노드][인접노드] = 1 을 진행한다.
            while (!q.isEmpty()) {
                int from = q.poll();
                isVisited[from] = true;

                for (int to = 1; to <= n; to++) {
                    if (map[from][to] == 1 && !isVisited[to]) {
                        q.add(to);
                        map[i][to] = 1;
                    }
                }
            }
        }
    }

    static void floydWarshall() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    map[j][k] = map[j][i] == 1 && map[i][k] == 1 ? 1 : map[j][k];
                }
            }
        }
    }
}