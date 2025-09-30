import java.io.*;
import java.util.*;

public class Main {

    static String isbn;

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     *
     */
    public static void main(String[] args) throws IOException {
        isbn = br.readLine();

        int weightedSum = 0, wrongIdx = 0;
        for (int i = 0; i < isbn.length(); i++) {
            char charNum = isbn.charAt(i);
            if (charNum == '*') {
                wrongIdx = i;
            } else if (i % 2 == 0) {
                weightedSum += charNum - '0';
            } else {
                weightedSum += ((charNum - '0') * 3);
            }
        }

        int targetNumber = 10 - weightedSum % 10;
        if (targetNumber == 10) {
            bw.write(String.valueOf(0));
        } else if (wrongIdx % 2 == 0) {
            bw.write(String.valueOf(targetNumber));
        } else {
            while(targetNumber % 3 != 0) targetNumber += 10;
            bw.write(String.valueOf(targetNumber / 3));
        }
        bw.flush();
        bw.close();
    }
}