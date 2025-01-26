import java.sql.Array;
import java.util.*;
import java.io.*;

public class Main {

    static boolean[][] graph= new boolean[1001][1001];
    static boolean[] visited1 = new boolean[1001];
    static boolean[] visited2 = new boolean[1001];

    public static void solution(int n, int v){
        dfs(n, v);
        System.out.println();
        bfs(n, v);
    }

    static void dfs(int n, int v){
        visited1[v] = true;
        System.out.print(v + " ");
        for(int i = 1; i <= n; i++){
            if(graph[v][i] && !visited1[i]){
                dfs(n, i);
            }
        }
    }

    static void bfs(int n, int v){
        Queue<Integer> order = new LinkedList<>();
        order.offer(v);
        visited2[v] = true;

        while (!order.isEmpty()) {
            int nextNode = order.poll();
            System.out.print(nextNode + " ");
            for(int i = 1; i <= n; i++){
                if(graph[nextNode][i] && !visited2[i]){
                    order.offer(i);
                    visited2[i] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken()), tmp2 = Integer.parseInt(st.nextToken());
            graph[tmp1][tmp2] = true;
            graph[tmp2][tmp1] = true;
        }
        br.close();

        solution(n, v);

//        bw.flush();
//        bw.close();
    }
}
