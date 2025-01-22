import java.util.*;
import java.io.*;

class Solution{
    public int[] solution(int[][] arr, int n){
        int[] sum = {0, 0};
        if(n == 1){
            sum[arr[0][0]] = 1;
            return sum;
        }
        int init = arr[0][0];
        for(int[] ar: arr){
            for(int a: ar){
                if(init != a){
                    for(int i = 0; i < 2; i++){
                        int[][] copy1 = new int[n/2][n/2], copy2 = new int[n/2][n/2];
                        for(int j = 0; j < n/2; j++){
                            copy1[j] = Arrays.copyOfRange(arr[j + i * n/2], 0, n/2);
                            copy2[j] = Arrays.copyOfRange(arr[j + i * n/2], n/2, n);
                        }
                        int[] tmp;
                        tmp = solution(copy1, n/2);
                        sum[0] += tmp[0]; sum[1] += tmp[1];
                        tmp = solution(copy2, n/2);
                        sum[0] += tmp[0]; sum[1] += tmp[1];
                    }
                    return sum;
                }
            }
        }
        sum[init] += 1;
        return sum;
    }
}

public class Main {
    static int[][] arr = new int[128][128];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int[][] copy = new int[n][n];
        for(int i = 0; i < n; i++)
            copy[i] = Arrays.copyOf(arr[i], n);

        Solution sol = new Solution();
        int[] result = sol.solution(copy, n);
        bw.write(String.valueOf(result[0]));
        bw.newLine();
        bw.write(String.valueOf(result[1]));

        bw.flush();
        bw.close();
    }
}
