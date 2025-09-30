import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;

    static final int[] items = new int[101];
    static final int[][] map = new int[101][101];
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 한 노드에서 연결 가능한 지점까지의 최단거리를 계산하고,
     * 그 중 m보다 작은 거리를 가지는 노드의 items를 모두 더한다.
     * 이를 n개의 노드에 대해 반복하면 최대로 얻을 수 있는 item의 개수를 구할 수 있다.
     * - n개의 노드에 대해 r개의 간선이 존재하므로 우선순위 큐를 사용한 다익스트라 알고리즘을 적용하면 O(N^2 R) 의 시간복잡도를 가진다.
     * 단, 인접리스트가 아닌 행렬을 사용해 구현했으므로 O(N^3)과 유사하다.
     * - 모든 노드에 대해 각 노드까지의 최단거리를 구해야하므로 플로이드-워셜 알고리즘을 적용할 수 있다.
     */
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()), node2 = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
            map[node1][node2] = weight;
            map[node2][node1] = weight;
        }

        // int result = dijkstra();
        int result = floydWarshall();

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    // 각 낙하산 지점으로부터의 최단거리를 계산하는 다익스트라 (및 BFS)
    static int dijkstra() {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int[] minWeight = new int[101];
            Arrays.fill(minWeight, Integer.MAX_VALUE);

            PriorityQueue<Node> q = new PriorityQueue<>();
            minWeight[i] = 0;

            for (int to = 1; to <= n; to++) {
                if (map[i][to] != 0) {
                    q.add(new Node(i, to, map[i][to]));
                }
            }

            while (!q.isEmpty()) {
                Node node = q.poll();
                for (int j = 1; j <= n; j++) {
                    // 가중치가 기존 계산한 값보다 작아 갱신 가능하다면 이미 방문한 노드에 다시 방문할 수 있다.
                    if (map[node.to][j] != 0 && minWeight[j] > minWeight[node.to] + map[node.to][j]) {
                        q.add(new Node(node.to, j, map[node.to][j]));
                    }
                }
                minWeight[node.to] = Math.min(minWeight[node.to], minWeight[node.from] + node.weight);
            }
            int numberOfItems = 0;
            for (int node = 1; node <= n; node++) {
                if (minWeight[node] <= m) {
                    numberOfItems += items[node];
                }
            }
            result = Math.max(result, numberOfItems);
        }
        return result;
    }

    static class Node implements Comparable<Node> {
        Integer from;
        Integer to;
        Integer weight;

        public Node (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // 가중치 기준 오름차순 정렬
        @Override
        public int compareTo(Node node) {
            return weight.compareTo(node.weight);
        }
    }

    // 모든 노드에 대한 최단거리를 구하므로 플로이드-워셜은 좋은 선택지이다.
    static int floydWarshall() {
        // 노드 자신을 가리키지 않고, 연결이 되어 있지 않으면 최대값으로 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j && map[i][j] == 0) {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int stop = 1; stop <= n; stop++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][stop] != Integer.MAX_VALUE && map[stop][j] != Integer.MAX_VALUE) {
                        map[i][j] = Math.min(map[i][j], map[i][stop] + map[stop][j]);
                    }
                }
            }
        }

        int result = 0;
        for (int node = 1; node <= n; node++) {
            int numberOfItems = 0;
            for (int to = 1; to <= n; to++) {
                if (map[node][to] <= m) {
                    numberOfItems += items[to];
                }
            }
            result = Math.max(result, numberOfItems);
        }
        return result;
    }
}