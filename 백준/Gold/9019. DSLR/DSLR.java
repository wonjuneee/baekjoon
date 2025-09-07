import java.util.*;
import java.io.*;

public class Main {

    static int t, a, b;
    static List<Character> commands = List.of('D', 'S', 'L', 'R');

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int tCase = 0; tCase < t; tCase++) {
            String[] commandList = new String[10000];

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            commandList[a] = "";

            Queue<Integer> nextNumberQueue = new LinkedList<>();
            nextNumberQueue.add(a);
            while (!nextNumberQueue.isEmpty()) {
                int tmpA = nextNumberQueue.poll();
                String prevCommandList = commandList[tmpA];
                for (char command : commands) {
                    int nextNumber = apply(command, tmpA);

                    if (commandList[nextNumber] != null) {
                        continue;
                    }

                    commandList[nextNumber] = prevCommandList + command;

                    nextNumberQueue.add(nextNumber);

                    if (commandList[b] != null) {
                        nextNumberQueue.clear();
                        break;
                    }
                }
            }

            bw.write(commandList[b]);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static int apply(char command, int number) {
        int result;

        switch (command) {
            case 'D':
                result = (number * 2 > 9999) ? (number * 2) % 10000 : (number * 2);
                break;
            case 'S':
                result = (number == 0) ? 9999 : number - 1;
                break;
            case 'L':
                int d1 = number / 1000;
                int d2To4 = (number % 1000) * 10;
                result = d2To4 + d1;
                break;
            case 'R':
                int d4 = number % 10;
                int d1To3 = (number - d4) / 10;
                result = (d4 * 1000) + d1To3;
                break;
            default:
                result = 0;
        }

        return result;
    }
}