import java.util.*;
import java.io.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        List<Integer> operationList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            operationList.add(Integer.parseInt(br.readLine()));
        }

        solution(operationList, bw);

        bw.flush();
        bw.close();
    }

    private static void solution(List<Integer> operationList, BufferedWriter bw) throws IOException {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int operation : operationList) {
            if (operation == 0) {
                if (maxHeap.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(maxHeap.poll() + "\n");
                }
            } else {
                maxHeap.add(operation);
            }
        }
    }
}
