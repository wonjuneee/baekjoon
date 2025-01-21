import java.util.*;
import java.io.*;

class Solution{
    public int[] solution(int[] arr, int n, int t, int p){
        int tCount = 0, pCount = 0;
        for(int a: arr){
            if(a % t == 0)
                tCount += (a / t);
            else
                tCount += ((a / t) + 1);
        }
        pCount = n / p;
        int[] result = {tCount, pCount, n % p};

        return result;
    }
}

public class Main {
    static int[] arr = new int[6];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        br.close();

        Solution sol = new Solution();
        int[] result = sol.solution(arr, n, t, p);

        bw.write(String.valueOf(result[0]));
        bw.newLine();
        bw.write(String.valueOf(result[1]) + " " + String.valueOf(result[2]));

        bw.flush();
        bw.close();
    }
}
