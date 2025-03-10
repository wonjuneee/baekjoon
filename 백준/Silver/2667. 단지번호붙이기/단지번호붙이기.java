import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static char[][] map = new char[25][25];
    static boolean[][] visited = new boolean[25][25];

    public static Stack<Integer> solution() {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '1' && !visited[i][j]) {
                    stack.add(dfs(j, i));
                    result++;
                }
            }
        }

        stack.add(result);
        return stack;
    }

    static int dfs(int x, int y) {
        int sum = 1;
        visited[y][x] = true;

        if (x > 0 && !visited[y][x - 1] && map[y][x - 1] == '1') {
            sum += dfs(x - 1, y);
        }
        if (x < n - 1 && !visited[y][x + 1] && map[y][x + 1] == '1') {
            sum += dfs(x + 1, y);
        }
        if (y > 0 && !visited[y - 1][x] && map[y - 1][x] == '1') {
            sum += dfs(x, y - 1);
        }
        if (y < n - 1 && !visited[y + 1][x] && map[y + 1][x] == '1') {
            sum += dfs(x, y + 1);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        Stack<Integer> result = solution();
        bw.write(String.valueOf(result.pop()));
        bw.newLine();

        result.sort(Comparator.naturalOrder());
        for (int element : result) {
            bw.write(String.valueOf(element));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
