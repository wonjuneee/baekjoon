import java.io.*;
import java.util.*;

public class Main {

    static int n;

    static final List<Node>[] nodes = new ArrayList[10001];
    static final int[] distances = new int[10001];
    static final boolean[] isVisited = new boolean[10001];

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 각 노드를 루트로 간주한 상태에서 인접리스트 기반의 DFS 순회를 할 경우 O(N + (N-1)), 즉 O(N)의 시간복잡도를 가지게 된다.
     * 이때 두 개의 노드는 트리의 지름을 구성하는 두 점이 될 수 있다.
     * 따라서 N번의 DFS 순회를 통해 한 노드에서 다른 노드까지의 최장거리를 계산하면 그것이 트리의 지름이 되며,
     * 최종적으로 O(N^2)의 시간복잡도를 가지게 된다.
     * --- 추가 인사이트 ---
     * 매 노드를 DFS 순회할 경우, N이 커질수록 시간이 오래 걸릴 수 밖에 없다.
     * 그렇기 때문에 트리의 지름을 구성하는 두 점을 찾아야 한다.
     * 1. 루트 노드에서 가장 멀리 있는 노드 찾기
     * 2. 해당 노드에서 다시 가장 멀리 있는 노드 찾기
     * 2번의 DFS/BFS를 통해 최장거리를 구했을 때 나오는 두 점이 트리의 지름을 구성한다.
     */
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes[parent].add(new Node(child, weight));
            nodes[child].add(new Node(parent, weight));
        }

//        int result = 0;
//        for (int i = 1; i <= n; i++) {
//            result = Math.max(result, dfs(i));
//            Arrays.fill(isVisited, false);
//        }
        dfsAndSaveDistance(1, 0);
        int node = 1;
        // 루트 노드에서 가장 먼 노드 구하기
        for (int i = 1; i <= n; i++) {
            if (distances[node] < distances[i]) {
                node = i ;
            }
        }

        Arrays.fill(isVisited, false);
        Arrays.fill(distances, 0);
        dfsAndSaveDistance(node, 0);
        // 결과로 나온 노드에서 구할 수 있는 최장거리
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, distances[i]);
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static int dfs(int node) {
        List<Node> children = nodes[node];
        isVisited[node] = true;
        if (children.isEmpty()) {
            return 0;
        }

        int maxAcc = 0;
        for (Node child : children) {
            int childIdx = child.idx;
            if (isVisited[childIdx]) {
                continue;
            }
            int weight = child.weight;
            int acc = dfs(childIdx) + weight;

            maxAcc = Math.max(maxAcc, acc);
        }
        return maxAcc;
    }

    static void dfsAndSaveDistance(int node, int distance) {
        List<Node> children = nodes[node];
        distances[node] = distance;
        isVisited[node] = true;
        if (children.isEmpty()) {
            return;
        }

        for (Node child : children) {
            if (isVisited[child.idx]) {
                continue;
            }
            dfsAndSaveDistance(child.idx, distance + child.weight);
        }
    }

    static class Node {
        Integer idx;
        Integer weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
