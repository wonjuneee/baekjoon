import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static HashMap<String, Integer> neverHeardSeen = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        LinkedList<String> result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            neverHeardSeen.put(br.readLine(), 1);
        }

        for (int i = 0; i < m; i++) {
            String tmp = br.readLine();
            neverHeardSeen.computeIfPresent(tmp, (person, number) ->{
                result.add(person);
                return null;
            });
        }

        result.sort(Comparator.naturalOrder());
        bw.write(String.valueOf(result.size()) + '\n');
        for(String person : result) {
            bw.write(person);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
