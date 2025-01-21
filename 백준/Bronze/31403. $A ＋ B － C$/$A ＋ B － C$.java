import java.util.*;
import java.io.*;

public class Main {
    static int[] arr = new int[1000000];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();
        br.close();
        String ab = a + b;

        bw.write(String.valueOf(Integer.parseInt(a) + Integer.parseInt(b) - Integer.parseInt(c)));
        bw.newLine();
        bw.write(String.valueOf(Integer.parseInt(ab) - Integer.parseInt(c)));

        bw.flush();
        bw.close();
    }
}