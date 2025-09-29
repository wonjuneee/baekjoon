import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] laboratory = new int[10][10];

    static final List<int[]> virusPosition = new LinkedList<>();
    static final int[][] directions = new int[][]{ {0, -1}, {0, 1}, {-1, 0}, {1, 0} };

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * n, m <= 8 이므로 64C3 = 41664
     * 바이러스가 퍼질 수 있는 칸을 순회 : 64칸
     * 따라서 벽이 세워지는 경우의 수 각각에 대해 바이러스 전파 연산을 수행하면
     * 총 2,666,496 회의 연산이 이루어지므로 시간제한 2초 내에 동작 가능하다.
     */
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int row = 0; row <= n + 1; row++) {
            if (row == 0 || row == n + 1) {
                Arrays.fill(laboratory[row], 1);
                continue;
            }
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col <= m + 1; col++) {
                if (col == 0 || col == m + 1) {
                    laboratory[row][col] = 1;
                } else {
                    int component = Integer.parseInt(st.nextToken());
                    laboratory[row][col] = component;
                    if (component == 2) {
                        // 바이러스 전파 시작 지점을 기록하기 위함
                        virusPosition.add(new int[]{row, col});
                    }
                }
            }
        }

        // (n*m)C3 수행
        int space = n * m;
        int result = 0;
        for (int i = 1; i <= space - 2; i++) {
            int aRow = (i - 1) / m + 1, aCol = (i - 1) % m + 1;
            if (laboratory[aRow][aCol] != 0) {
                continue;
            }
            laboratory[aRow][aCol] = 1;

            for (int j = i + 1; j <= space - 1; j++) {
                int bRow = (j - 1) / m + 1, bCol = (j - 1) % m + 1;
                if (laboratory[bRow][bCol] != 0) {
                    continue;
                }
                laboratory[bRow][bCol] = 1;

                for (int k = j + 1; k <= space; k++) {
                    int cRow = (k - 1) / m + 1, cCol = (k - 1) % m + 1;
                    if (laboratory[cRow][cCol] != 0) {
                        continue;
                    }
                    laboratory[cRow][cCol] = 1;
                    result = Math.max(result, bfsAndCount());
                    laboratory[cRow][cCol] = 0;
                }
                laboratory[bRow][bCol] = 0;
            }
            laboratory[aRow][aCol] = 0;
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    // 매 경우의 수마다 virusPosition에서부터 bfs 순회
    static int bfsAndCount() {
        Queue<int[]> q = new LinkedList<>(virusPosition);
        boolean[][] isVisited = new boolean[10][10];

        while (!q.isEmpty()) {
            int[] currPos = q.poll();
            int currRow = currPos[0], currCol = currPos[1];
            isVisited[currRow][currCol] = true;

            for (int[] d : directions) {
                int nextRow = currRow + d[0], nextCol = currCol + d[1];
                if (laboratory[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                    q.add(new int[]{ nextRow, nextCol });
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (laboratory[i][j] == 0 && !isVisited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}