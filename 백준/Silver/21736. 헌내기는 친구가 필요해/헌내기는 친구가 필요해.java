import java.util.*;
import java.io.*;

public class Main {

    static char[][] map = new char[601][601];
    static int n, m;

    public static int solution(int posX, int posY){
        int sum = 0;
        if(map[posY][posX] == 'P'){
            sum += 1;
        }
        map[posY][posX] = 'X';

        if(posX > 0 && map[posY][posX - 1] != 'X') {
            sum += solution(posX - 1, posY);
        }
        if(posY > 0 && map[posY - 1][posX] != 'X') {
            sum += solution(posX, posY - 1);
        }
        if(posX < m - 1 && map[posY][posX + 1] != 'X') {
            sum += solution(posX + 1, posY);
        }
        if(posY < n - 1 && map[posY + 1][posX] != 'X') {
            sum += solution(posX, posY + 1);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int posX = 0, posY = 0;

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;
                if (ch == 'I') {
                    posX = j;
                    posY = i;
                }

            }
        }
        br.close();

        int result = solution(posX, posY);
        if(result == 0)
            bw.write("TT");
        else
            bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
