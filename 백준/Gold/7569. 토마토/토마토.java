import java.io.*;
import java.util.*;

public class Main {

    static int n, m, h;

    static final int[][] directions = new int[][]{
        {-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0},  {0, 0, -1}, {0, 0, 1}
    };
    static final int[][][] boxes = new int[102][102][102];
    static final boolean[][][] isVisited = new boolean[102][102][102];
    static final Queue<int[]> q = new LinkedList<>();

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * bfs를 3차원 배열에 적용할 수 있다.
     * 이때, 모든 토마토가 익는 날짜를 계산해야 하므로 bfs 순회를 하는 큐를 전파 단계마다 분리해야 한다.
     * 따라서 임시 큐를 프록시로 하여, 같은 날짜에 전파를 시작하는 노드(토마토)를 임시 큐에 넣는다.
     * 이후 메인 큐의 한 사이클을 모두 돌면 메인 큐를 초기화하고 임시 큐를 메인 큐로 밀어넣으며 날짜 + 1 을 진행한다.
     */
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        for (int height = 0; height <= h + 1; height++) {
            if (height == 0 || height == h + 1) {
                for (int row = 0; row <= n + 1; row++) {
                    Arrays.fill(boxes[height][row], -1);
                }
                continue;
            }

            for (int row = 0; row <= n + 1; row++) {
                if (row == 0 || row == n + 1) {
                    Arrays.fill(boxes[height][row], -1);
                    continue;
                }
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col <= m + 1; col++) {
                    if (col == 0 || col == m + 1) {
                        boxes[height][row][col] = -1;
                    } else {
                        int tomato = Integer.parseInt(st.nextToken());
                        boxes[height][row][col] = tomato;
                        if (tomato == 1) {
                            // 듀얼큐를 사용하는 bfs는 마지막 원소를 가지지 않는다.
                            q.add(new int[] { height, row, col, 0 });
                        }
                    }
                }
            }
        }

        // int result = bfsWithDualQueue();
        int result = bfs();

        for (int height = 1; height <= h; height++) {
            for (int row = 1; row <= n; row++) {
                for (int col = 1; col <= m; col++) {
                    if (boxes[height][row][col] == 0) {
                        bw.write(String.valueOf(-1));
                        bw.flush();
                        bw.close();
                        return;
                    }
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    // 몇 번의 사이클이 도는지 확인해야 하므로 메인 큐와 임시 큐를 분리하여,
    // 사이클이 종료할 때마다 메인 큐를 초기화하고 임시 큐를 메인 큐에 밀어넣는다.
    // - 메인 큐에 대해 poll()을 진행하면 동시성 오류가 발생하므로 이러한 로직을 사용한다.
    // - 단일 큐를 사용해 일반적인 bfs 로직을 사용하고자 한다면 int[] 의 마지막 원소로 day 를 추가하여
    //   해당 값에 대한 처리 로직을 추가로 가져갈 수 있을 것이다.
    static int bfsWithDualQueue() {
        int day = 0;
        while (!q.isEmpty()) {
            Queue<int[]> tmpQ = new LinkedList<>();

            for (int[] currPos : q) {
                int height = currPos[0], row = currPos[1], col = currPos[2];
                isVisited[height][row][col] = true;

                for (int[] d : directions) {
                    int nextHeight = height + d[0], nextRow = row + d[1], nextCol = col + d[2];

                    if (boxes[nextHeight][nextRow][nextCol] == 0 && !isVisited[nextHeight][nextRow][nextCol]) {
                        boxes[nextHeight][nextRow][nextCol] = 1;
                        tmpQ.add(new int[]{ nextHeight, nextRow, nextCol });
                    }
                }
            }
            q.clear();
            q.addAll(tmpQ);
            if (!tmpQ.isEmpty()) {
                day++;
            }
        }
        return day;
    }

    static int bfs() {
        int spentTime = 0;
        while (!q.isEmpty()) {
            int[] currPos = q.poll();
            int height = currPos[0], row = currPos[1], col = currPos[2], day = currPos[3];
            isVisited[height][row][col] = true;

            for (int[] d : directions) {
                int nextHeight = height + d[0], nextRow = row + d[1], nextCol = col + d[2];

                if (boxes[nextHeight][nextRow][nextCol] == 0 && !isVisited[nextHeight][nextRow][nextCol]) {
                    boxes[nextHeight][nextRow][nextCol] = 1;
                    q.add(new int[] { nextHeight, nextRow, nextCol, day + 1 });
                }
            }
            spentTime = day;
        }

        return spentTime;
    }
}