import java.io.*;
import java.util.*;

public class Main {
    static int n, sharkSize = 2, sharkRow, sharkCol, count = 0;

    static final int[][] ocean = new int[22][22];
    static final Set<Integer> fishSet = new LinkedHashSet<>();
    
    // 상 - 좌 - 우 - 하 우선순위로 같은 거리의 물고기를 먹으므로 directions의 순서를 정렬
    static final int[][] directions = new int[][] { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * 음의 가중치를 가진 간선이 없으므로 다익스트라 알고리즘 사용 가능.
     * 물고기 한 마리를 잡아먹을 때마다 각 노드까지의 최단거리를 계산해야 하므로
     * 더 이상 잡아먹을 물고기가 없을 때까지 다익스트라 알고리즘을 반복할 수 있다.
     * 이때 거리가 같을 경우 좌표를 기준으로 우선순위가 변경되므로, 이를 기준으로 힙 (우선순위 큐)를 정렬할 수 있다.
     * - 격자 형태, 인접행렬 형태의 그래프이므로 다익스트라 알고리즘의 시간복잡도: O(n^2)
     * - 다익스트라 알고리즘을 수행하며 우선순위 큐에 노드 삽입 : O(logN)
     * 따라서 O(n^2 logN)의 시간 복잡도를 가진다.
     */
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        for (int row = 0; row <= n + 1; row++) {
            if (row == 0 || row == n + 1) {
                Arrays.fill(ocean[row], Integer.MAX_VALUE);
                continue;
            }
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col <= n + 1; col++) {
                if (col == 0 || col == n + 1) {
                    ocean[row][col] = Integer.MAX_VALUE;
                } else {
                    int something = Integer.parseInt(st.nextToken());
                    ocean[row][col] = something;
                    if (something == 9) {
                        sharkRow = row;
                        sharkCol = col;
                    } else if (something != 0) {
                        fishSet.add((row - 1) * n + col);
                    }
                }
            }
        }

        int result = 0, tmp;
        do {
            ocean[sharkRow][sharkCol] = 0;
            tmp = dijkstra(sharkRow, sharkCol);
            result += tmp;
            count++;
            if (count == sharkSize) {
                count = 0;
                sharkSize++;
            }
        } while (tmp != 0);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    // 인접배열 형태의 그래프를 다익스트라 알고리즘을 활용해 해결하므로 O(N^2)
    // 만일 같은 노드를 중복으로 방문하더라도 최종적으로는 가장 거리값이 우선순위 큐 상단에 올라가므로
    // q 내부에서 반복적으로 삽입해도 결과에는 영향을 끼치지 않는다.
    // 따라서 O(N^2 logN)의 시간복잡도를 가진다.
    static int dijkstra(int startRow, int startCol) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] distances = new int[22][22];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        distances[startRow][startCol] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol, 0});

        while (!q.isEmpty()) {
            int[] currPos = q.poll();
            int currRow = currPos[0], currCol = currPos[1], currDist = currPos[2];

            for (int[] d : directions) {
                int nextRow = currRow + d[0], nextCol = currCol + d[1], nextDist = currDist + 1;

                if (ocean[nextRow][nextCol] <= sharkSize && distances[nextRow][nextCol] > nextDist) {
                    distances[nextRow][nextCol] = nextDist;
                    q.add(new int[]{nextRow, nextCol, nextDist});
                }
            }
            if (0 < ocean[currRow][currCol] && ocean[currRow][currCol] < sharkSize) {
                pq.add(new Node(currRow, currCol, distances[currRow][currCol]));
            }
        }
        if (pq.isEmpty()) {
            return 0;
        }
        Node closestNode = pq.peek();
        sharkRow = closestNode.row; sharkCol = closestNode.col;

        return closestNode.distance;
    }

    static class Node implements Comparable<Node>{
        int row;
        int col;
        int distance;

        public Node (int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        // 거리 - 위쪽 - 왼쪽 순서로 정렬
        @Override
        public int compareTo(Node node) {
            if (this.distance == node.distance) {
                if (this.row == node.row) {
                    return this.col - node.col;
                }
                return this.row - node.row;
            }
            return this.distance - node.distance;
        }
    }
}