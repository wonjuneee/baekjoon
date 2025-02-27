import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int targetX, targetY;
    static int[][] map = new int[1001][1001];
    static int[][] result = new int[1001][1001];

    public static void solution(){
        Queue<Integer> q = new LinkedList<>();

        q.add(targetY * m + targetX);

        while(!q.isEmpty()){
            int tmp = q.size();
            for(int i = 0; i < tmp; i++) {
                int coordinate = q.poll();
                int x = coordinate % m, y = coordinate / m;

                if(x > 0 && result[y][x - 1] == -1 && map[y][x - 1] == 1){
                    q.add(y * m + (x - 1));
                    result[y][x - 1] = result[y][x] + 1;
                }
                if(x < m - 1 && result[y][x + 1] == -1 && map[y][x + 1] == 1){
                    q.add(y * m + (x + 1));
                    result[y][x + 1] = result[y][x] + 1;
                }
                if(y > 0 && result[y - 1][x] == -1 && map[y - 1][x] == 1){
                    q.add((y - 1) * m + x);
                    result[y - 1][x] = result[y][x] + 1;
                }
                if(y < n - 1 && result[y + 1][x] == -1 && map[y + 1][x] == 1){
                    q.add((y + 1) * m + x);
                    result[y + 1][x] = result[y][x] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0)
                    result[i][j] = 0;
                else if (tmp == 2) {
                    result[i][j] = 0;
                    targetX = j;
                    targetY = i;
                }
                else{
                    result[i][j] = -1;
                }
                map[i][j] = tmp;
            }
        }

        solution();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                bw.write(result[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
