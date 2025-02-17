import java.util.*;
import java.io.*;

public class Main {
    static int[][] map = new int[1001][1001];
    static int n, m;
    static int minus, zero, plus;
    static Queue<Integer> queue = new LinkedList<>();

    public static int solution(){
        Queue<Integer> tmpQ = new LinkedList<>();
        int result = 0;
        do {
            tmpQ.clear();
            while(!queue.isEmpty()) {
                int element = queue.poll();
                int x = element % n, y = element / n;
                if (x > 0 && map[y][x - 1] == 0) {
                    zero--;
                    plus++;
                    tmpQ.add(n * y + (x - 1));
                    map[y][x - 1] = 1;
                }
                if (y > 0 && map[y - 1][x] == 0) {
                    zero--;
                    plus++;
                    tmpQ.add(n * (y - 1) + x);
                    map[y - 1][x] = 1;
                }
                if (x < n - 1 && map[y][x + 1] == 0) {
                    zero--;
                    plus++;
                    tmpQ.add(n * y + (x + 1));
                    map[y][x + 1] = 1;
                }
                if (y < m - 1 && map[y + 1][x] == 0) {
                    zero--;
                    plus++;
                    tmpQ.add(n * (y + 1) + x);
                    map[y + 1][x] = 1;
                }
            }
            result++;
            queue.addAll(tmpQ);
        }while(!tmpQ.isEmpty());

        return result - 1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                switch(tmp){
                    case 1:
                        plus++;
                        queue.add(n * i + j);
                        break;
                    case 0:
                        zero++;
                        break;
                    case -1:
                        minus++;
                        break;
                }
            }
        }

        if(zero == 0)
            bw.write("0");
        else{
            int result = solution();
            if(zero != 0)
                bw.write("-1");
            else
                bw.write(String.valueOf(result));
        }

        bw.flush();
        bw.close();
    }
}
