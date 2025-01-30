import java.util.*;
import java.io.*;

public class Main {
    public static int solution(int n, int r, int c){
        int number = (int) Math.pow(2, n);

        return split(number, r, c);
    }

    static int split(int number, int row, int column){
        if(number == 1)
            return 1;
        int countCellsAtPrevZs = 0;
        if(row > number / 2 && column > number / 2){
            countCellsAtPrevZs = (int) Math.pow(number / 2, 2) * 3;
        }
        else if(row > number / 2 && column <= number / 2){
            countCellsAtPrevZs = (int) Math.pow(number / 2, 2) * 2;
        }
        else if(row <= number / 2 && column > number / 2){
            countCellsAtPrevZs = (int) Math.pow(number / 2, 2) * 1;
        }
        else if(row <= number / 2 && column <= number / 2){
            countCellsAtPrevZs = (int) Math.pow(number / 2, 2) * 0;
        }
        int splitZ = split(number / 2, (row - 1) % (number / 2) + 1, (column - 1) % (number / 2) + 1);
        return countCellsAtPrevZs + splitZ;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        br.close();

        int result = solution(n, r + 1, c + 1) - 1;

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
