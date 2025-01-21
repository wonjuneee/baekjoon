import java.util.*;
import java.io.*;

class Solution{
    public int solution(int[] arr, int m){
        int height = 0;
        for(int a: arr)
            height = Math.max(a, height);

        return partition(arr, height, m);
    }

    public int partition(int[] arr, int height, int m){
        int start = 0, end = height, mid = (start + end) / 2;
        while (start <= end){
            mid = (start + end) / 2;
            long sum = 0;

            for(int a: arr)
                sum = a > mid ? sum + a - mid : sum;

            if(sum == m)
                return mid;
            else if(sum < m)
                end = mid - 1;
            else
                start = mid+  1;
        }
        return end;
    }
}

public class Main {
    static int[] arr = new int[1000000];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // String input = br.readLine();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        br.close();

        Solution sol = new Solution();
        int result = sol.solution(Arrays.copyOfRange(arr, 0, n), m);
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
