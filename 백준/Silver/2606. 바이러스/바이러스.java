import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Integer>> network = new ArrayList<>();
    static boolean[] visited = new boolean[101];

    public static int solution(){
        visited[0] = true;

        return dfs(0) - 1;
    }

    static int dfs(int number){
        int count = 1;
        for (int nextNum : network.get(number)) {
            if (!visited[nextNum]) {
                visited[nextNum] = true;
                count += dfs(nextNum);
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numberOfVertices = Integer.parseInt(br.readLine());
        int numberOfEdges = Integer.parseInt(br.readLine());

        for(int i = 0; i < numberOfVertices; i++){
            network.add(new ArrayList<>());
        }

        for(int i = 0; i < numberOfEdges; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int com1 = Integer.parseInt(st.nextToken()) - 1, com2 = Integer.parseInt(st.nextToken()) - 1;
            network.get(com1).add(com2);
            network.get(com2).add(com1);
        }

        int result = solution();
        br.close();

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
