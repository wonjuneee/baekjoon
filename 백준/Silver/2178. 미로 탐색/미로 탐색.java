import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] map = new int[101][101];

    public static int solution(){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int result = 0;

        while(!q.isEmpty()){
            int size= q.size();
            result++;

            for(int i = 0; i < size; i++){
                int coordinate = q.poll();
                int x = coordinate % m, y = coordinate / m;

                if(x == m - 1 && y == n - 1) {
                    return result;
                }

                if(x > 0 && map[y][x - 1] == 1){
                    map[y][x - 1] = 0;
                    q.add(y * m + (x - 1));
                }
                if(x < m - 1 && map[y][x + 1] == 1){
                    map[y][x + 1] = 0;
                    q.add(y * m + (x + 1));
                }
                if(y > 0 && map[y - 1][x] == 1){
                    map[y - 1][x] = 0;
                    q.add((y - 1) * m + x);
                }
                if(y < n - 1 && map[y + 1][x] == 1){
                    map[y + 1][x] = 0;
                    q.add((y + 1) * m + x);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            String str = new StringTokenizer(br.readLine()).nextToken();
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        int result = solution();
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
