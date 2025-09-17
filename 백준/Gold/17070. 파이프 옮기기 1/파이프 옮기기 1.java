import java.io.*;
import java.util.*;

public class Main {

    public static int r, c, n; // [r][c]면 r번 째 행, c번 째 열
    public static int[][] house = new int[18][18];

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[][] directions = new int[][]{{0, 1}, {1, 0}, {1, 1}}; // 가로, 세로, 대각
    private static final PipeType[] types = new PipeType[]{PipeType.HORIZONTAL, PipeType.VERTICAL, PipeType.DIAGONAL};

    private enum PipeType {
        HORIZONTAL(directions[0], List.of(0, 2), List.of(directions[0])),
        VERTICAL(directions[1], List.of(1, 2), List.of(directions[1])),
        DIAGONAL(directions[2], List.of(0, 1, 2), List.of(directions));

        private final int[] direction;
        private final List<Integer> nextTypeList;
        private final List<int[]> checkList;

        PipeType(int[] direction, List<Integer> nextTypeList, List<int[]> checkList) {
            this.direction = direction; // 파이프 방향
            this.nextTypeList = nextTypeList; // 이어서 연결 가능한 파이프 타입
            this.checkList = checkList; // 파이프 연결을 위해 확인해야 하는 타일 방향
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n + 1; i++) {
            if (i == 0 || i == n + 1) {
                Arrays.fill(house[i], 1);
                continue;
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= n + 1; j++) {
                if (j == 0 || j == n + 1) {
                    house[i][j] = 1;
                    continue;
                }
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 어차피 (n, n)에 도달하지 못하므로 0 출력
        if (house[n][n] == 1) {
            bw.write(sb.append(0).toString());
        } else {
            int result = dfs(1, 1, PipeType.HORIZONTAL);
            bw.write(sb.append(result).toString());
        }
        bw.flush();
        bw.close();
    }

    /**
     * 시작 지점의 row, col과 파이프 타입 type을 입력으로 받음
     * 1. checkList로 시작지점에서 주어진 타입의 파이프를 연결할 수 있는지 확인
     * 2. 연결 가능하면 다음 지점이 도착 지점인지 확인
     * 3. 도착지점이 아니면 이어서 연결 가능한 파이프 타입에 따라 각각 dfs 진행
     */
    private static int dfs(int row, int col, PipeType type) {
        for (int[] checkPoint : type.checkList) {
            if (house[row + checkPoint[0]][col + checkPoint[1]] == 1) {
                return 0;
            }
        }

        int[] direction = type.direction;
        List<Integer> nextPipeTypeList = type.nextTypeList;
        int currX = row + direction[0], currY = col + direction[1];
        int result = 0;

        if (currX == n && currY == n && house[row][col] == 0) {
            return 1;
        }
        for (Integer pipeTypeIdx : nextPipeTypeList) {
            result += dfs(currX, currY, types[pipeTypeIdx]);
        }

        return result;
    }
}