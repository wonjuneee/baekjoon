import java.util.*;
import java.io.*;

public class Main {

    static int[] number = new int[11];

    public static int solution(int n){
        number[0] = 0;number[1] = 1;number[2] = 2;number[3] = 4;

        for(int i = 4; i <= n; i++){
            number[i] = number[i - 3] + number[i - 2] + number[i - 1];
        }

        return number[n];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            int result = solution(n);

            bw.write(String.valueOf(result));
            bw.newLine();
        }
        br.close();

        bw.flush();
        bw.close();
    }
}
