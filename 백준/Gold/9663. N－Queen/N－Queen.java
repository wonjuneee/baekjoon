import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static final List<Set<Integer>> impossibleGrid = new ArrayList<>(); // 체스말이 놓여진 Y좌표
    public static int[][] board = new int[15][15];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                n = Integer.parseInt(br.readLine());

                int result = solution(1);

                bw.write(String.valueOf(result));
                bw.newLine();
            } catch (Exception e) {
                break;
            }
        }
        bw.flush();
        bw.close();
    }

    private static int solution(int gridY) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (board[gridY][i] == 0) {
                if (gridY == n) {
                    return 1;
                }

                for (int j = 1; gridY + j <= n; j++) {
                    if (i + j <= n && board[gridY + j][i + j] == 0) {
                        board[gridY + j][i + j] = gridY;
                    }
                    if (i - j >= 1 && board[gridY + j][i - j] == 0) {
                        board[gridY + j][i - j] = gridY;
                    }
                    board[gridY + j][i] = board[gridY + j][i] == 0 ? gridY : board[gridY + j][i];
                }
                result += solution(gridY + 1);
                for (int j = 1; gridY + j <= n; j++) {
                    if (i + j <= n && board[gridY + j][i + j] == gridY) {
                        board[gridY + j][i + j] = 0;
                    }
                    if (i - j >= 1 && board[gridY + j][i - j] == gridY) {
                        board[gridY + j][i - j] = 0;
                    }
                    board[gridY + j][i] = board[gridY + j][i] == gridY ? 0 : board[gridY + j][i];
                }
            }
        }

        return result;
    }
}