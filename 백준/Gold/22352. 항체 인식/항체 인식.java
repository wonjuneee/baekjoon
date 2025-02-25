import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] cells = new int[30][30];
    static int[][] vaccinated = new int[30][30];
    static boolean[][] visited = new boolean[30][30];

    public static boolean solution(){
        boolean result = true;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(cells[i][j] != vaccinated[i][j]){
                    dfs(j, i, cells[i][j], vaccinated[i][j]);
                    return check();
                }
            }
        }

        return result;
    }

    static void dfs(int x, int y, int cellNum, int vaccNum) {
        visited[y][x] = true;
        cells[y][x] = vaccNum;

        if(x > 0 && !visited[y][x - 1] && cells[y][x - 1] == cellNum){
            dfs(x - 1, y, cellNum, vaccNum);
        }
        if(x < m - 1 && !visited[y][x + 1] && cells[y][x + 1] == cellNum){
            dfs(x + 1, y, cellNum, vaccNum);
        }
        if(y > 0 && !visited[y - 1][x] && cells[y - 1][x] == cellNum){
            dfs(x, y - 1, cellNum, vaccNum);
        }
        if(y < n - 1 && !visited[y + 1][x] && cells[y + 1][x] == cellNum){
            dfs(x, y + 1, cellNum, vaccNum);
        }
    }

    static boolean check(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(cells[i][j] != vaccinated[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                cells[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                vaccinated[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(solution()) bw.write("YES");
        else bw.write("NO");

        bw.flush();
        bw.close();
    }
}
