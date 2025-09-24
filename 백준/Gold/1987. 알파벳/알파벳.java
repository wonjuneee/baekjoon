import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] board = new char[22][22];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final Set<Character> alphabetSet = new HashSet<>();
    static final int[][] direction = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int row = 0; row <= r + 1; row++) {
            if (row == 0 || row == r + 1) {
                Arrays.fill(board[row], '!');
                continue;
            }
            String str = br.readLine();

            // 외곽은 !로 감싸고 그 내부를 board로 활용
            for (int col = 0; col <= c + 1; col++) {
                if (col == 0 || col == c + 1) {
                    board[row][col] = '!';
                } else {
                    board[row][col] = str.charAt(col - 1);
                }
            }
        }
        int result = dfs(1, 1);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    /**
     * 제한시간 2초
     * 알파벳의 개수는 26개로, 26칸을 탐색할 수 있는 모든 경우의 수를 연산하더라도
     * 시간 제한인 2초를 넘지 않을 것이므로 dfs 알고리즘을 사용해도 무방하다.
     */
    static int dfs(int row, int col) {
        int result = 0;
        char alphabet = board[row][col];

        // 새로운 알파벳일 경우 alphabetSet에 저장 후 result + 1
        if (alphabet != '!' && !alphabetSet.contains(alphabet)) {
            alphabetSet.add(alphabet);
            result++;
        }
        // 현재 위치가 외곽이거나, 이미 지나온 루트에서 마주친 알파벳일 경우 0 반환
        else {
            return result;
        }

        // 현재 칸에서부터 이동 가능한 모든 루트에 대해 max값 저장
        int nextStep = 0;
        for (int[] d : direction) {
            nextStep = Math.max(dfs(row + d[0], col + d[1]), nextStep);
        }

        // 백트래킹을 위해 이전 칸으로 돌아가며 현재 칸의 알파벳을 다른 루트에서 다시 접근할 수 있도록 set에서 제거
        // 현재 칸까지의 step 수와 현재 칸으로부터 최대로 갈 수 있는 nextStep을 합쳐 전파
        alphabetSet.remove(alphabet);
        return result + nextStep;
    }
}