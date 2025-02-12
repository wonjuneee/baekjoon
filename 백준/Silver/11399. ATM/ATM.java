import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer> waiting = new ArrayList<>(1001);

    public static int solution(int n){
        waiting.sort(Integer::compareTo);
        int result = 0, tmp = 0;
        for(int i = 0; i < n; i++){
            tmp += waiting.get(i);
            result += tmp;
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            waiting.add(i, Integer.parseInt(st.nextToken()));
        }
        br.close();

        int result = solution(n);
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
