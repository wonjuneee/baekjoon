import java.util.*;
import java.io.*;

public class Main {

    public static class Pair<L, R>{
        L left;
        R right;

        public Pair(L left, R right){
            this.left = left;
            this.right = right;
        }
    }
    static ArrayList<Pair<Integer, Integer>> dp = new ArrayList<>();
    static int n;

    public static Pair<Integer, Integer> solution(){
        dp.add(new Pair<>(1, 0));
        dp.add(new Pair<>(0, 1));

        for(int i = 2; i <= n; i++){
            dp.add(new Pair<>(dp.get(i - 2).left + dp.get(i - 1).left, dp.get(i - 2).right + dp.get(i - 1).right));
        }

        return dp.get(n);
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            n = Integer.parseInt(br.readLine());
            Pair<Integer, Integer> result = solution();
            bw.write(result.left + " " + result.right);
            bw.newLine();
            dp.clear();
        }

        bw.flush();
        bw.close();
    }
}
