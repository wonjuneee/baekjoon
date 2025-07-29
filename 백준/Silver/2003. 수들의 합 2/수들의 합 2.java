import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                List<Integer> numberList = new ArrayList<>();

                StringTokenizer st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < n; i++) {
                    numberList.add(Integer.parseInt(st.nextToken()));
                }

                int idx1 = 0, idx2 = 0, result = 0;
                int tmp = numberList.get(idx1);
                while (idx1 < n) {
                    if (tmp == m) {
                        result++;
                        tmp -= numberList.get(idx1);
                        idx1++;
                    } else if (tmp < m && idx2 < n - 1) {
                        idx2++;
                        tmp += numberList.get(idx2);
                    } else {
                        tmp -= numberList.get(idx1);
                        idx1++;
                    }
                }

                bw.write(String.valueOf(result));
                bw.newLine();
            } catch (Exception e) {
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}