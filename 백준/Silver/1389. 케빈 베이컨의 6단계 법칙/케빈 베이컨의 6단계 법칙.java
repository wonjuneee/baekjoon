import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] relation = new int[101][101];
    static boolean[] visited = new boolean[101];

    public static int solution(){
        int kevinBacon = -1, result = 0;
        for(int i = 1; i <= n; i++){
            visited[i] = true;
            int tmp = kevinBacon(i);
            if(tmp < kevinBacon || kevinBacon == -1) {
                result = i;
                kevinBacon = tmp;
            }
            visited = new boolean[101];
        }

        return result;
    }

    static int kevinBacon(int number){
        int sum = 0;
        Queue<Integer> neighbor = new LinkedList<>();
        neighbor.add(number);

        while(!neighbor.isEmpty()) {
            int vertex = neighbor.poll();
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && relation[vertex][i] == 1) {
                    visited[i] = true;
                    neighbor.add(i);
                    relation[number][i] = relation[number][vertex] + 1;
                }
            }
        }

        for(int i = 1; i <= n; i++){
            sum += relation[number][i];
        }

        return sum;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            relation[a][b] = 1;
            relation[b][a] = 1;
        }

        int result = solution();
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
