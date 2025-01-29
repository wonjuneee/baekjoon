import java.util.*;
import java.io.*;

public class Main {

    static char[][] map = new char[100][100];
    static char[][] rgMap = new char[100][100];

    public static int[] solution(int n){
        int[] result = new int[]{0, 0};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] != 'V')
                    result[0] += mapTraverse(n, i, j, map[i][j]);
                if(rgMap[i][j] != 'V')
                    result[1] += rgMapTraverse(n, i, j, rgMap[i][j]);
            }
        }
        return result;
    }

    static int mapTraverse(int n, int pX, int pY, char color){
        map[pX][pY] = 'V';

        if(pX > 0 && map[pX - 1][pY] == color)
            mapTraverse(n, pX - 1, pY, color);
        if(pY > 0 && map[pX][pY - 1] == color)
            mapTraverse(n, pX, pY - 1, color);
        if(pX < n - 1 && map[pX + 1][pY] == color)
            mapTraverse(n, pX + 1, pY, color);
        if(pY < n - 1 && map[pX][pY + 1] == color)
            mapTraverse(n, pX, pY + 1, color);

        return 1;
    }

    static int rgMapTraverse(int n, int pX, int pY, char color){
        rgMap[pX][pY] = 'V';

        if(pX > 0 && rgMap[pX - 1][pY] == color)
            rgMapTraverse(n, pX - 1, pY, color);
        if(pY > 0 && rgMap[pX][pY - 1] == color)
            rgMapTraverse(n, pX, pY - 1, color);
        if(pX < n - 1 && rgMap[pX + 1][pY] == color)
            rgMapTraverse(n, pX + 1, pY, color);
        if(pY < n - 1 && rgMap[pX][pY + 1] == color)
            rgMapTraverse(n, pX, pY + 1, color);

        return 1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            String line = br.readLine();
            for(int j = 0; j < n; j++){
                char grid = line.charAt(j);
                map[i][j] = grid;
                rgMap[i][j] = (grid == 'G') ? 'R' : grid;
            }
        }
        br.close();

        int[] result = solution(n);
        bw.write(result[0] + " " + result[1]);

        bw.flush();
        bw.close();
    }
}
